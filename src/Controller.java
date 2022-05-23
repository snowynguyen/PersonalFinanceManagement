package src;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.sql.Statement;
import java.time.LocalDateTime;
import java.sql.ResultSet;
import java.io.File;
import java.io.IOException;

public class Controller {
    static ArrayList<Transaction> __transactions;
    static ArrayList<TransactionCategory> __transaction_categories;
    static ArrayList<Account> __accounts;
    static String db_path = ".//data//data.db";
    static String db_url = "jdbc:sqlite:" + db_path;

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
        "ID INTEGER PRIMARY KEY,\n" + 
        "name VARCHAR(1024)\n" +
        ");";

    static final String MAKE_ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS ACCOUNTS (\n" +
        "ID INTEGER PRIMARY KEY,\n" + 
        "name VARCHAR(1024),\n" +
        "balance FLOAT,\n" +
        "created TEXT,\n" +
        "last_update TEXT,\n" +
        "note VARCHAR(1024)\n" +
        ");";
    

    ResultSet executeSQLquery(String sql) {
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

    void readTransactionsFromDB() {
        __transactions = new ArrayList<Transaction>();
        try {
            ResultSet rs = executeSQLquery("SELECT * FROM TRANSACTIONS");
            while (rs.next()) {
                Transaction item = new Transaction();
                item.id = rs.getLong("id");
                item.time = LocalDateTime.parse(rs.getString("time"));
                item.account_id = rs.getLong("account_id");
                item.detail = rs.getString("detail");
                item.note = rs.getString("note");
                item.amount = rs.getDouble("amount");
                item.category_id = rs.getLong("category_id");
                item.is_future_reversible = rs.getBoolean("is_future_reversible");
                item.due_reversal = LocalDateTime.parse(rs.getString("due_reversal"));
                __transactions.add(item);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage()); 
        }
    }

    void readCategoriesFromDB() {
        __transaction_categories = new ArrayList<TransactionCategory>();
        try {
            ResultSet rs = executeSQLquery("SELECT * FROM CATEGORIES");
            while (rs.next()) {
                TransactionCategory item = new TransactionCategory();
                item.id = rs.getLong("id");
                item.name = rs.getString("name");
                __transaction_categories.add(item);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage()); 
        }
    }

    void readAccountsFromDB() {
        __accounts = new ArrayList<Account>();
        try {
            ResultSet rs = executeSQLquery("SELECT * FROM ACCOUNTS");
            while (rs.next()) {
                Account item = new Account();
                item.id = rs.getLong("id");
                item.name = rs.getString("name");
                item.created = LocalDateTime.parse(rs.getString("created"));
                item.last_update = LocalDateTime.parse(rs.getString("last_update"));
                item.note = rs.getString("note");
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

    Controller() {
        initDBfile();
        readFromDBtoModel();
    }
}
