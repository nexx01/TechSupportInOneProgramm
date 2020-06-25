package org.iresto.WorkWithBD.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.iresto.WorkWithBD.ConnectorDB;
import org.iresto.object.AbstractClient;
import org.iresto.object.AbstractClientIIKO;
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

    public boolean addClient(AbstractClientIIKO clientIiko){
        String INSERT_QUERY="INSERT INTO clientiiko (brand,legalyName,address,kindOfLicense,statusOfSupport) VALUES ('?','?','?','?','?');";
        return updateOrInsertClient(INSERT_QUERY, clientIiko);
    }

    public boolean updateClient(AbstractClientIIKO clientIiko){
        String UPDATE_QUERY ="UPDATE clientiiko SET brand=?, legalyName=?, address=?, kindOfLicense=?, statusOfSupport=? WHERE id =?;";
        return updateOrInsertClient(UPDATE_QUERY, clientIiko);
    }

    private boolean updateOrInsertClient(String QUERY,AbstractClientIIKO clientIiko){
        QUERY ="UPDATE clientiiko SET brand=?, legalyName=?, address=?, kindOfLicense=?, statusOfSupport=? WHERE id =?;";
        try(Connection connection=ConnectorDB.getConnecton();
            PreparedStatement preparedStatement=connection.prepareStatement(QUERY)) {
            System.out.println(clientIiko.getBrand());
            preparedStatement.setString(1,clientIiko.getBrand());
            preparedStatement.setString(2,clientIiko.getLegalEntity());
            preparedStatement.setString(3,clientIiko.getAddress());
            preparedStatement.setString(4,clientIiko.kindOfLicense);
            preparedStatement.setString(5,clientIiko.statusOfSupport);
            /*Если  ID clientIiko не равен 0 - это значит, что этот клиент уже был в БД, значит выполняем
            * 1)Присваивание шестого аргумента в SQL запрос
            * 2) выполняем Update в БД
            * иначе выполняем executeQuery(), т.е. создаем новую строчку в таблице clientiiko
            * ID в этом случае присоится самой БД
          */
         // if (clientIiko.getClientId()!=0) {
                preparedStatement.setInt(6,clientIiko.getClientId());
                preparedStatement.executeUpdate();
         //   } else {
               // preparedStatement.executeQuery();
           //// }
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


}
