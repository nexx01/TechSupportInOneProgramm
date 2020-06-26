package org.iresto.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.iresto.PrimaryController;
import org.iresto.WorkWithBD.DAO.Client_DAO;
import org.iresto.object.AbstractClient;
import org.iresto.object.AbstractClientIIKO;
import org.iresto.object.impl.clientIiko.ClientIiko;
import org.iresto.utils.DialogManager;

import java.net.URL;
import java.util.ResourceBundle;

public class AddClientFormController implements Initializable{
    public TextField txtfieldAddBrand;
    public TextField txtfieldAddLegalyName;
    public TextField txtfieldAddAddres;
    public TextField txtfieldAddLicense;
    public TextField txtfieldAddStatusOfSupport;
    public Button btnSave;
    private ResourceBundle resourceBundle;
    private Client_DAO client_dao;
    private ClientIiko client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resourceBundle=resourceBundle;
    }
  /*Заполняет форму редактирования клиента данными клиента, если НУЛЬ то возвращаем. Даже в случае добавления
  * объект клиента должен инициализирован до вызова этого метода.  */
    public void setClient(ClientIiko client){
        if(client==null){
            return;
        }
        this.client=client;
        txtfieldAddBrand.setText(client.getBrand());
        txtfieldAddLegalyName.setText(client.getLegalEntity());
        txtfieldAddAddres.setText(client.getAddress());
        txtfieldAddLicense.setText(client.getKindOfLicense());
        txtfieldAddStatusOfSupport.setText(client.getStatusOfSupport());
    }

    /*Деествие при нажатии конпки сохранить
    * 1) Провепяем заполнены ли все поля
    * 2) вызываем соответсвующий метод ДАО
    * 3)если ID  клиента =0 - он не пришел из БД, и только создан
    * (ID автоматически инкреминируется при записи туда новых значений) */
      @FXML
      private void actionSave(ActionEvent actionEvent){
        if(!checkValues()){
            return;
        }
          client_dao=new Client_DAO();// Я конечно не знаю но думаю не хорошая тема создавть прямо при нажатии
          //это тормозит работу . С другой стороны зачем лишний раз держать в памяти объъект


        if(client==null) {
            client =new ClientIiko();
            setNewValues();
            client_dao.insertClient(client);
        } else {
            setNewValues();
            client_dao.updateClient(client);
        }
          actionClose(actionEvent);
        }
/*Считываем данные с формы редактирования/добавления клиента и присваиваеим о.client*/
        private void setNewValues(){
            client.setKindOfLicense(txtfieldAddLicense.getText());
            client.setStatusOfSupport(txtfieldAddStatusOfSupport.getText());
            client.setBrand(txtfieldAddBrand.getText());
            client.setLegalEntity(txtfieldAddLegalyName.getText());
            client.setAddress(txtfieldAddAddres.getText());
      }


/*Метод проверяет заполнены ли все поля. Если заполнены не все поля выскочит ошибка с надписью заполните все поля*/
      private boolean checkValues(){
        if(txtfieldAddBrand.getText().trim().length()==0||
        txtfieldAddLegalyName.getText().trim().length()==0||
        txtfieldAddAddres.getText().trim().length()==0||
        txtfieldAddLicense.getText().trim().length()==0||
        txtfieldAddStatusOfSupport.getText().trim().length()==0){
            DialogManager.showInfoDialog(resourceBundle.getString("Error"),resourceBundle.getString("FieldAllField"));
            {
                return false;
            }
        }
        return true;
      }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }








}
