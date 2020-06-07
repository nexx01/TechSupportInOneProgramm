package org.iresto.WorkWithBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {
    public static Connection getConnecton() throws SQLException{
        ResourceBundle resourceBundle= ResourceBundle.getBundle("database");
        String url = resourceBundle.getString("db.url");
        String user = resourceBundle.getString("db.user");
        String pass= resourceBundle.getString("db.password");
        String dbName = resourceBundle.getString("db.name");
        String propertiesConnectionDB= resourceBundle.getString("db.connectionProperty");

        return DriverManager.getConnection(url+dbName+propertiesConnectionDB, user,pass);
    }
}
