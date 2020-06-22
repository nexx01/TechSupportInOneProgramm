package org.iresto;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iresto.WorkWithBD.DAO.Client_DAO;

import java.net.URL;
import java.util.ResourceBundle;

public class AddClientFormController implements Initializable{
    public TextField txtfieldAddBrand;
    public TextField txtfieldAddLegalyName;
    public TextField txtfieldAddAddres;
    public TextField txtfieldAddLicense;
    public TextField txtfieldAddStatusOfSupport;
    public Button btnSave;
    //private Client_DAO client_dao=new Client_DAO();// создаем экземпляр класса связи с БД
    //Этот уже созданный в MainController заберем оттуда
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }






    private void initLoaderWindowAddOrEditClient(){


    }






}
