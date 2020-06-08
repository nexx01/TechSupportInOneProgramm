package org.iresto.WorkWithBD.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import org.iresto.WorkWithBD.ConnectorDB;
import org.iresto.object.AbstractWorkComputer;
import org.iresto.object.impl.WorkComputer.WorkComputer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectData_DAO {
    public  ObservableList<WorkComputer> findConnectDataByClientId(int client_id){
        ObservableList<WorkComputer> workComputers = FXCollections.observableArrayList();
 String SELECT_QUERY="SELECT * FROM connectdata WHERE id_client=?";
 try(Connection connection = ConnectorDB.getConnecton();
     PreparedStatement preparedStatement=connection.prepareStatement(SELECT_QUERY)) {
     preparedStatement.setInt(1,client_id);
     ResultSet resultSet=preparedStatement.executeQuery();
     while (resultSet.next()){
         workComputers.add(new WorkComputer(
                 resultSet.getString(3),
                 resultSet.getString(4),
                 resultSet.getString(6),
                 resultSet.getString(5),
                 resultSet.getString(7)
         ));

     }
     return workComputers;
 } catch (SQLException throwables) {
     throwables.printStackTrace();
 }
return null;
    }
}
