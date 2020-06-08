package org.iresto.WorkWithBD.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.iresto.WorkWithBD.ConnectorDB;
import org.iresto.object.impl.WorkComputer.WebResourceIiko;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WebResource_DAO {
    public ObservableList<WebResourceIiko> findEntityByClientId(int clent_id){
        ObservableList<WebResourceIiko> internetResourceIikos=FXCollections.observableArrayList();
        String SELECT_QUERY="SELECT * FROM webresources WHERE id_client=?";
        try(Connection connection= ConnectorDB.getConnecton();
            PreparedStatement preparedStatement=connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setInt(1,clent_id);

            ResultSet resultSet=   preparedStatement.executeQuery();
            while (resultSet.next()){
                internetResourceIikos.add(new WebResourceIiko(
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }
            return internetResourceIikos;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
return null;
    }
}
