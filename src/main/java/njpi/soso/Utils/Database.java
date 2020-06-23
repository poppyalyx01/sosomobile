package njpi.soso.Utils;

import java.sql.*;

public class Database {

    public Connection connect() {
        String url = Database.class.getClassLoader().getResource("database.db").toString();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:"+url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}