package org.iresto.WorkWithBD.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.iresto.WorkWithBD.ConnectorDB;
import org.iresto.object.AbstractClient;
import org.iresto.object.AbstractClientIIKO;
import org.iresto.object.impl.clientIiko.ClientIiko;

import java.sql.*;

public class Client_DAO {
    public ObservableList<ClientIiko> findAllClient() {
        ObservableList<ClientIiko> clientsList = FXCollections.observableArrayList();
        ClientIiko clientIiko = null;
        String SELECT_QUERY = "SELECT * FROM clientiiko";
        try (Connection connection = ConnectorDB.getConnecton();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
            while (resultSet.next()) {
                int clientId = resultSet.getInt(1);
                String brand = resultSet.getString(2);
                String legalEntity = resultSet.getString(3);
                String address = resultSet.getString(4);
                String kindOfLicense = resultSet.getString(5);
                String statusOfSupport = resultSet.getString(6);

                clientsList.add(new ClientIiko(clientId, brand, legalEntity, address, kindOfLicense, statusOfSupport));
            }
            return clientsList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        /*Возвращаем List нулевой длины,  хотя не уверен что это хорошая идея*/
        return clientsList;
    }

    public boolean updateClient(AbstractClientIIKO clientIiko) {
        String UPDATE_QUERY = "UPDATE clientiiko SET brand=?, legalyName=?, address=?, kindOfLicense=?, statusOfSupport=? WHERE id =?;";
        try (Connection connection = ConnectorDB.getConnecton();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, clientIiko.getBrand());
            preparedStatement.setString(2, clientIiko.getLegalEntity());
            preparedStatement.setString(3, clientIiko.getAddress());
            preparedStatement.setString(4, clientIiko.kindOfLicense);
            preparedStatement.setString(5, clientIiko.statusOfSupport);
            preparedStatement.setInt(6, clientIiko.getClientId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public  boolean insertClient(AbstractClientIIKO clientIiko){
         //String INSERT_QUERY = "INSERT INTO clientiiko (brand,legalyName,address,kindOfLicense,statusOfSupport) VALUES ('?','?','?','?','?');";
        // String INSERT_QUERY = "INSERT INTO clientiiko (brand,legalyName,address,kindOfLicense,statusOfSupport) VALUES (\'?\',\'?\',\'?\',\'?\',\'?\');";
        // String INSERT_QUERY = "INSERT INTO clientiiko (brand) VALUES (?);";
        String INSERT_QUERY = "INSERT INTO clientiiko (brand,legalyName,address,kindOfLicense,statusOfSupport) VALUES (?,?,?,?,?);";
         //String INSERT_QUERY = "INSERT INTO clientiiko (brand) VALUES ('?');";
        try (Connection connection=ConnectorDB.getConnecton();
            PreparedStatement preparedStatement=connection.prepareStatement(INSERT_QUERY)){
            preparedStatement.setString(1, clientIiko.getBrand());
            preparedStatement.setString(2, clientIiko.getLegalEntity());
            preparedStatement.setString(3, clientIiko.getAddress());
            preparedStatement.setString(4, clientIiko.kindOfLicense);
            preparedStatement.setString(5, clientIiko.statusOfSupport);
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteClient(AbstractClient client){
        String DELETE_QUERY="DELETE FROM clientiiko WHERE id=?;";
        try(Connection connection=ConnectorDB.getConnecton();
        PreparedStatement preparedStatement=connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1,client.getClientId());
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
      return  false;
    }

}
