package njpi.soso.Utils;

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