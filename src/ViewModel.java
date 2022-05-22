package src;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.sql.Statement;  
import java.io.File;
import java.io.IOException;

public class ViewModel {
    static ArrayList<Transaction> __transaction;
    static String db_path = ".//data//data.db";
    static String db_url = "jdbc:sqlite:" + db_path;

    static final String MAKE_TRANSACTION_TABLE =  "CREATE TABLE IF NOT EXISTS TRANSACTIONS (\n" 
        +" id INTEGER PRIMARY KEY,\n" 
        +" time INTEGER,\n" 
        +" origin_account_id INTEGER,\n" 
        +" destination_account_id INTEGER,\n" 
        +" detail VARCHAR(1024),\n" 
        +" note VARCHAR(1024),\n" 
        +" amount FLOAT,\n" 
        +" category_id INTEGER,\n" 
        +" isFuturePayable INTEGER,\n" 
        +" isFutureReceivable INTEGER\n" 
        +");";
    

    static final String MAKE_CATEGORY_TABLE = "CREATE TABLE IF NOT EXISTS CATEGORIES (\n" +
        "ID INTEGER PRIMARY KEY,\n" + 
        "name VARCHAR(1024)\n" +
        ");";

    static final String MAKE_ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS ACOUNTS (\n" +
        "ID INTEGER PRIMARY KEY,\n" + 
        "name VARCHAR(1024),\n" +
        "balance FLOAT,\n" +
        "created INTEGER,\n" +
        "last_update INTEGER,\n" +
        "note VARCHAR(1024)\n" +
        ");";
    

    void executeSQLquery(String sql) {
        try {  
            Connection conn = DriverManager.getConnection(db_url);  
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);  
        } catch (SQLException e) {  
            System.err.println(e.getMessage());  
        }  
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

    ViewModel() {
        initDBfile();
    }
}
