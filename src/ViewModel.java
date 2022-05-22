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

    static final String MAKE_TRANSACTION_TABLE = "CREATE TABLE IF NOT EXISTS TRANSACTIONS (\n" +
        "ID BIGINT PRIMARY KEY\n" + 
        "time DATETIME\n" + 
        "origin_account_id BIGINT\n" +
        "destination_account_id BIGINT\n" +
        "detail VARCHAR(1024)\n" +
        "note VARCHAR(1024)\n" +
        "amount FLOAT\n" +
        "category_id BIGINT\n" +
        "isFuturePayable TINYINT" +
        "isFutureReceivable TINYINT" +
        ")";
    
    static final String MAKE_CATEGORY_TABLE = "CREATE TABLE IF NOT EXISTS CATEGORIES (\n" +
        "ID BIGINT PRIMARY KEY\n" + 
        "name VARCHAR(1024)\n" +
        ")";

    static final String MAKE_ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS ACOUNTS (\n" +
        "ID BIGINT PRIMARY KEY\n" + 
        "name VARCHAR(1024)\n" +
        "balance FLOAT\n" +
        "created DATETIME\n" +
        "last_update DATETIME\n" +
        "note VARCHAR(1024)\n" +
        ")";
    

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
