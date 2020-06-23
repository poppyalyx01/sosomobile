package njpi.soso.Utils;

/**
 * @author poppy
 * @mail poppyalyx1983@gmail.com
 */

import java.sql.*;

public class Database {

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite::resource:database.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}