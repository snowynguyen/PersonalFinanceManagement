package src.Controller;

import src.Constants;
import src.Models.Transaction;
import src.Models.TransactionCategory;
import src.Models.Account;
import src.Controller.Controller;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle.Control;
import java.time.LocalDateTime;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import src.Models.GenericTable;

public class ExcelInteractor {

    public ExcelInteractor() {}

    public GenericTable<Transaction> readTransactionExcel(String file) {
        GenericTable<Transaction> table = new GenericTable<>();
        
    
        // Get file
        InputStream inputStream = new FileInputStream(new File(file));
    
        // Get workbook
        Workbook workbook = getWorkbook(inputStream, file);
    
        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);
    
        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
    
            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();
    
            // Read cells and set value for book object
            Transaction transaction = new Transaction();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case Constants.COLUMN_INDEX_ID:
                        transaction.setId(new BigDecimal((double) cellValue).intValue());
                        break;
                    case Constants.COLUMN_INDEX_TIME:
                        transaction.setTime(LocalDateTime.parse(getCellValue(cell).toString()));
                        break;
                    case Constants.COLUMN_INDEX_ACCOUNT:
                        String account_name = cellValue.toString();
                        ResultSet rs = Controller.executeSQLquery("SELECT id FROM ACCOUNTS WHERE name = " + account_name + ";");
                        if (rs.isAfterLast()) {
                            System.err.println(String.format("Account named '%s' was not found, creating new account to DB.", account_name));
                            Account new_account = new Account();
                            new_account.setName(account_name);
                            Controller.saveNewAccount(new_account);
                        }
                        rs = Controller.executeSQLquery("SELECT id FROM ACCOUNTS WHERE name = " + account_name + ";");
                        if (!rs.first()) {
                            System.err.println("Invalid ResultSet state ExcelInteractor/readTransactionExcel/caseAccount.");
                        }
                        long account_id = rs.getLong("id");
                        transaction.setAccount_id(account_id);
                        break;
                    case Constants.COLUMN_INDEX_DETAIL:
                        transaction.setDetail(cellValue.toString());
                        break;
                    case Constants.COLUMN_INDEX_CATEGORY:
                        String category_name = cellValue.toString();
                        ResultSet rsc = Controller.executeSQLquery("SELECT id FROM CATEGORIES WHERE name = " + category_name + ";");
                        if (rsc.isAfterLast()) {
                            System.err.println(String.format("Account named '%s' was not found, creating new account to DB.", account_name));
                            TransactionCategory category = new TransactionCategory();
                            category.setName(category_name);
                            Controller.saveNewTransactionCategory(category);
                        }
                        rsc = Controller.executeSQLquery("SELECT id FROM CATEGORIES WHERE name = " + category_name + ";");
                        if (!rs.first()) {
                            System.err.println("Invalid ResultSet state ExcelInteractor/readTransactionExcel/caseCategory.");
                        } 
                        long category_id = rs.getLong("id");
                        transaction.setCategory_id(category_id);
                        break;
                    case Constants.COLUMN_INDEX_AMOUNT:
                        transaction.setAmount(new BigDecimal((double) cellValue).doubleValue());
                    default:
                        break;
                }
    
            }
            listBooks.add(book);
        }
    
        workbook.close();
        inputStream.close();
    
        return table;
    }

    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
 
        return workbook;
    }
 
    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
        case BOOLEAN:
            cellValue = cell.getBooleanCellValue();
            break;
        case FORMULA:
            Workbook workbook = cell.getSheet().getWorkbook();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            cellValue = evaluator.evaluate(cell).getNumberValue();
            break;
        case NUMERIC:
            cellValue = cell.getNumericCellValue();
            break;
        case STRING:
            cellValue = cell.getStringCellValue();
            break;
        case _NONE:
        case BLANK:
        case ERROR:
            break;
        default:
            break;
        }
 
        return cellValue;
    }
}
