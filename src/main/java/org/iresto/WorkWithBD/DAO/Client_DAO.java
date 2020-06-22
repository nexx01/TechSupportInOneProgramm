package org.iresto.WorkWithBD.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.iresto.WorkWithBD.ConnectorDB;
import org.iresto.object.impl.clientIiko.ClientIiko;

import java.sql.*;

public class Client_DAO {
    public ObservableList<ClientIiko>  findAllClient(){
        ObservableList<ClientIiko> clientsList=FXCollections.observableArrayList();
        ClientIiko clientIiko=null;
        String SELECT_QUERY="SELECT * FROM clientiiko";
        try(Connection connection= ConnectorDB.getConnecton();
            Statement statement=connection.createStatement()){
            ResultSet resultSet=statement.executeQuery(SELECT_QUERY);
            while(resultSet.next()){
                int clientId =resultSet.getInt(1);
                String brand=resultSet.getString(2);
                String legalEntity=resultSet.getString(3);
                String address = resultSet.getString(4);
                String kindOfLicense = resultSet.getString(5);
                String statusOfSupport = resultSet.getString(6);

                clientsList.add(new ClientIiko(clientId,brand,legalEntity,address,kindOfLicense,statusOfSupport));
            }
            return clientsList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean AddClient(String brand,String legalyName,String address,String kindOfLicense, String statusOfSupport){
        String INSERT_QUERY="INSERT INTO clientiiko (brand,legalyName,address,kindOfLicense,statusOfSupport)"+
                " VALUES ('?','?','?','?','?')";
        try(Connection connection=ConnectorDB.getConnecton();
            PreparedStatement preparedStatement=connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1,brand);
            preparedStatement.setString(2,legalyName);
            preparedStatement.setString(3,address);
            preparedStatement.setString(4,kindOfLicense);
            preparedStatement.setString(5,statusOfSupport);
            preparedStatement.executeQuery();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return false;
    }


}
