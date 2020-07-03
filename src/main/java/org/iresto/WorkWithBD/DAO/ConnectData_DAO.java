package org.iresto.WorkWithBD.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import org.iresto.WorkWithBD.ConnectorDB;
import org.iresto.object.AbstractWorkComputer;
import org.iresto.object.impl.WorkComputer.WorkComputer;
import org.iresto.object.impl.clientIiko.ClientIiko;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectData_DAO {
    ObservableList<WorkComputer> workComputers = FXCollections.observableArrayList();

    public ObservableList<WorkComputer> findConnectDataByClientId(int client_id) {

        String SELECT_QUERY = "SELECT * FROM connectdata WHERE id_client=?";
        try (Connection connection = ConnectorDB.getConnecton();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setInt(1, client_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                workComputers.add(new WorkComputer(
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getInt(1)   // номер строки в БД, для редактирования данных
                ));

            }
            return workComputers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public boolean insertNewConnectData(WorkComputer workComputer,int id_client){
        if(workComputer.getIdInBaseOfData()>0){
                return updateConnectData(workComputer);
        } else if (workComputer.getIdInBaseOfData()==0){
            return insertConnectData(workComputer,id_client);
        }else {
            return false;
        }
    }

    /*Вставка новой строки в БД*/
    private boolean insertConnectData(WorkComputer workComputer, int id_client) {
        String INSERT_QUERY = "INSERT INTO connectdata (id_client,typePC,loginAmmyAdmin,pswAmmyAdmin,loginAnyDesk,pswAnyDesk) VALUES (?,?,?,?,?,?);";
        try (Connection connection = ConnectorDB.getConnecton();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, id_client);
            preparedStatement.setString(2, workComputer.getTypePC());
            preparedStatement.setString(3, workComputer.getIDAmmyAdmin());
            preparedStatement.setString(4, workComputer.getPswAmmyAdmin());
            preparedStatement.setString(5, workComputer.getIDAnyDesk());
            preparedStatement.setString(6, workComputer.getPswAnyDesk());
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /*Обновление строки по ID строки в БД
     * 1. Возможно можно объединить INSERT и Update, но по моему мнению только внесет путаницу */
    private boolean updateConnectData(WorkComputer workComputer) {
        String UPDATE_QUERY = "UPDATE connectdata SET typePC=?, loginAmmyAdmin=?, pswAmmyAdmin=?, loginAnyDesk=?, pswAnyDesk=? WHERE id =?;";
        try (Connection connection = ConnectorDB.getConnecton();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, workComputer.getTypePC());
            preparedStatement.setString(2, workComputer.getIDAmmyAdmin());
            preparedStatement.setString(3, workComputer.getPswAmmyAdmin());
            preparedStatement.setString(4, workComputer.getIDAnyDesk());
            preparedStatement.setString(5, workComputer.getPswAnyDesk());
            preparedStatement.setInt(6, workComputer.getIdInBaseOfData());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteConnectData(WorkComputer workComputer){
        String DELETE_QUERY="DELETE FROM WHERE id=?";
        try(Connection connection=ConnectorDB.getConnecton();
        PreparedStatement preparedStatement=connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, workComputer.getIdInBaseOfData());
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
