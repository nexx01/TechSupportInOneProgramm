package org.iresto.WorkWithBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {
    public static Connection getConnecton() throws SQLException{
        ResourceBundle connectBundle= ResourceBundle.getBundle("org.iresto.database");
        String url = connectBundle.getString("db.url");
        String user = connectBundle.getString("db.user");
        String pass= connectBundle.getString("db.password");
        String dbName = connectBundle.getString("db.name");
        String propertiesConnectionDB= connectBundle.getString("db.connectionProperty");

        return DriverManager.getConnection(url+dbName+propertiesConnectionDB, user,pass);
    }
}
