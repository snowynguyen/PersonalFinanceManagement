package src.Controller;

import java.util.ArrayList;

import src.Models.Account;
import src.Models.Transaction;
import src.Models.TransactionCategory;
import src.Models.GenericTable;

import java.sql.Connection;
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.sql.Statement;
import java.time.LocalDateTime;
import java.sql.ResultSet;
import java.io.File;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Controller {
    private static GenericTable<Transaction> __transactions;
    private static GenericTable<TransactionCategory> __transaction_categories;
    private static GenericTable<Account> __accounts;

    public static ArrayList<Transaction> getTransactions() {
        return __transactions.getItems();
    }

    public static ArrayList<TransactionCategory> getTransactionCategories() {
        return __transaction_categories.getItems();
    }

    public static ArrayList<Account> getAccounts() {
        return __accounts.getItems();
    }

    public static GenericTable<Transaction> getTransactionsTable() {
        return __transactions;
    }

    public static GenericTable<TransactionCategory> getTransactionCategoriesTable() {
        return __transaction_categories;
    }

    public static GenericTable<Account> getAccountsTable() {
        return __accounts;
    }
    
    public final static String db_path = ".//data//data.sqlite";
    public final static String db_url = "jdbc:sqlite:" + db_path;

    static final String MAKE_TRANSACTION_TABLE =  "CREATE TABLE IF NOT EXISTS TRANSACTIONS (\n" 
        +" id INTEGER PRIMARY KEY,\n" 
        +" time TEXT,\n" 
        +" account_id INTEGER,\n" 
        +" detail VARCHAR(1024),\n" 
        +" note VARCHAR(1024),\n" 
        +" amount FLOAT,\n" 
        +" category_id INTEGER,\n" 
        +" is_future_reversible INTEGER,\n" 
        +" due_reversal TEXT\n"
        +");";
    

    static final String MAKE_CATEGORY_TABLE = "CREATE TABLE IF NOT EXISTS CATEGORIES (\n" +
        "id INTEGER PRIMARY KEY,\n" + 
        "name VARCHAR(1024)\n" +
        ");";

    static final String MAKE_ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS ACCOUNTS (\n" +
        "id INTEGER PRIMARY KEY,\n" + 
        "name VARCHAR(1024),\n" +
        "created TEXT,\n" +
        "last_update TEXT,\n" +
        "note VARCHAR(1024)\n" +
        ");";
    

    static ResultSet executeSQLquery(String sql) {
        try {  
            Connection conn = DriverManager.getConnection(db_url);  
            Statement stmt = conn.createStatement();  
            return stmt.executeQuery(sql);  
        } catch (SQLException e) {  
            System.err.println(e.getMessage());  
        }  
        return null;
    }

    void initDBfile() {
        File f = new File(db_path);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.err.print("Failure when creating non-existent db file (error message = '" + e.getMessage() + "'");
            }
        }
        executeSQLquery(MAKE_TRANSACTION_TABLE);
        executeSQLquery(MAKE_CATEGORY_TABLE);
        executeSQLquery(MAKE_ACCOUNT_TABLE);
    }

    /**
     * <b>Be mindful that the data is stored inside the DB. The data objects from the models are only for the views, and shall not be treated as real-time data. Any attemp to accessing the data should be made directly using SQL query.</b>
     */
    void readTransactionsFromDB() {
        __transactions = new GenericTable<Transaction>();
        try {
            ResultSet rs = executeSQLquery("SELECT * FROM TRANSACTIONS");
            while (rs.next()) {
                Transaction item = new Transaction();
                item.setId(rs.getLong("id"));
                item.setTime(LocalDateTime.parse(rs.getString("time")));
                item.setAccount_id(rs.getLong("account_id"));
                item.setDetail(rs.getString("detail"));
                item.setNote(rs.getString("note"));
                item.setAmount(rs.getDouble("amount"));
                item.setCategory_id(rs.getLong("category_id"));
                item.setIs_future_reversible(rs.getBoolean("is_future_reversible"));
                item.setDue_reversal(LocalDateTime.parse(rs.getString("due_reversal")));
                __transactions.add(item);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage()); 
        }
    }

    void readCategoriesFromDB() {
        __transaction_categories = new GenericTable<TransactionCategory>();
        try {
            ResultSet rs = executeSQLquery("SELECT * FROM CATEGORIES");
            while (rs.next()) {
                TransactionCategory item = new TransactionCategory();
                item.setId(rs.getLong("id"));
                item.setName(rs.getString("name"));
                __transaction_categories.add(item);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage()); 
        }
    }

    void readAccountsFromDB() {
        __accounts = new GenericTable<Account>();
        try {
            ResultSet rs = executeSQLquery("SELECT * FROM ACCOUNTS");
            while (rs.next()) {
                Account item = new Account();
                item.setId(rs.getLong("id"));
                item.setName(rs.getString("name"));
                item.setCreated(LocalDateTime.parse(rs.getString("created")));
                item.setLast_update(LocalDateTime.parse(rs.getString("last_update")));
                item.setNote(rs.getString("note"));
                __accounts.add(item);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage()); 
        }
    }

    void readFromDBtoModel() {
        readCategoriesFromDB();
        readAccountsFromDB();
        readTransactionsFromDB();
    }

    void saveNewTransaction(Transaction transaction) {
        String sql = "INSERT INTO TRANSACTIONS(id, time, account_id, detail, note, amount, category_id, is_future_reversible, due_reversal) VALUE (%d, %s, %d, %s, %s, %f ,%d, %d, %s)"; 
        sql = String.format(sql, transaction.getId(), transaction.getTime().toString(), transaction.getAccount_id(), transaction.getDetail(), transaction.getNote(), transaction.getAmount(), transaction.getCategory_id(), transaction.getIs_future_reversible(), transaction.getDue_reversal());
        executeSQLquery(sql);
    }

    static void saveNewTransactionCategory(TransactionCategory category) {
        executeSQLquery(String.format("INSERT INTO CATEGORIES(id, name) VALUE %d %s", category.getId(), category.getName()));
    }

    static void saveNewAccount(Account account) {
        String sql = "INSERT INTO ACCOUNTS(id, name, created, last_update, note) VALUE(%d, %s, %s, %s, %s)";
        sql = String.format(sql, account.getId(), account.getName(), account.getCreated().toString(), account.getLast_update().toString(), account.getNote());
        executeSQLquery(sql);
    }

    void renameTransactionCategory(TransactionCategory modified_category) {
        String sql = "UPDATE CATEGORIES SET name = %s WHERE id = %d";
        sql = String.format(sql, modified_category.getName(), modified_category.getId());
        executeSQLquery(sql);
    }

    void updateAccountInfo(Account modified_account) {
        String sql = "UPDATE ACCOUNTS SET name = %s, note = %s WHERE id = %d";
        sql = String.format(sql, modified_account.getName(), modified_account.getNote(), modified_account.getId());
        executeSQLquery(sql);
    }

    void importTransactionExcel(String path) {
        ExcelInteractor interactor = new ExcelInteractor();
        interactor.readTransactionExcel(path);
    }

    // TODO: write update Transaction info
    // TODO: update last_update for account

    public Controller() {
        initDBfile();
        readFromDBtoModel();
    }
}
