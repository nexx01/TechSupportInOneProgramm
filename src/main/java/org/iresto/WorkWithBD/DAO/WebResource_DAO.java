package org.iresto.WorkWithBD.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.iresto.WorkWithBD.ConnectorDB;
import org.iresto.object.impl.WorkComputer.WebResourceIiko;
import org.iresto.object.impl.WorkComputer.WorkComputer;

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
                        resultSet.getString(5),
                        resultSet.getInt(1)
                ));
            }
            return internetResourceIikos;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
return null;
    }


    public boolean insertNewConnectData(WebResourceIiko webResourceIiko, int id_client){
        if(webResourceIiko.getIdInDataBase()>0){
            return updateWebResourceIiko(webResourceIiko);
        } else if (webResourceIiko.getIdInDataBase()==0){
            return insertwebResourceIiko(webResourceIiko,id_client);
        }else {
            return false;
        }
    }

    /*Вставка новой строки в БД*/
    private boolean insertwebResourceIiko(WebResourceIiko webResourceIiko, int id_client) {
        String INSERT_QUERY = "INSERT INTO webresources (id_client,typePC,loginAmmyAdmin,pswAmmyAdmin,loginAnyDesk,pswAnyDesk) VALUES (?,?,?,?,?,?);";
        try (Connection connection = ConnectorDB.getConnecton();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, id_client);
            preparedStatement.setString(2, webResourceIiko.getNameWebResource());
            preparedStatement.setString(3, webResourceIiko.getWebAddress());
            preparedStatement.setString(4, webResourceIiko.getWebAddress());
            preparedStatement.setString(5, webResourceIiko.getLoginWebResource());
            preparedStatement.setString(6, webResourceIiko.getWebpassword());
            preparedStatement.executeQuery();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /*Обновление строки по ID строки в БД
     * 1. Возможно можно объединить INSERT и Update, но по моему мнению только внесет путаницу */
    private boolean updateWebResourceIiko(WebResourceIiko webResourceIiko) {
        String UPDATE_QUERY = "UPDATE webresources SET nameWebResource=?, webAddress=?, login=? WHERE id =?;";
        try (Connection connection = ConnectorDB.getConnecton();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, webResourceIiko.getNameWebResource());

            preparedStatement.setString(2, webResourceIiko.getWebAddress());
            preparedStatement.setString(3, webResourceIiko.getLoginWebResource());
            preparedStatement.setInt(4, webResourceIiko.getIdInDataBase());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }



}
