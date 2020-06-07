package org.iresto;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import org.iresto.WorkWithBD.DAO.Client_DAO;
import org.iresto.impl.CollectionClienBook;
import org.iresto.impl.CollectionConnectionBook;
import org.iresto.object.AbstractClient;
import org.iresto.object.impl.clientIiko.ClientIiko;

public class PrimaryController implements Initializable {


    public TableView tableClientBook;
    public TableColumn clnBrand;
    public TableColumn clnLegalName;
    public TableColumn clnAddress;
    public TableColumn clnLicense;
    public TableColumn clnStatusOfSupport;
    public CustomTextField txtSearch;
    public Button btnSearch;
    public Button btnConnectedData;

    //Создаем объект Типа CollectionClienBook с названием clienBookImpl
    /*т.е планируется использовать не только для это типа нужно будет сделать
    как то более универсально.
    Хотя эта таблица заполняется именно этим типом
    * */
   // private CollectionClienBook clienBookImpl = new CollectionClienBook();
    private Client_DAO client_dao= new Client_DAO();// создаем экземпляр для подключения к БД
    private ObservableList<ClientIiko> clientBookImpl= FXCollections.observableArrayList();

    private ResourceBundle resourceBundle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        clnBrand.setCellValueFactory(new PropertyValueFactory<ClientIiko, String>("brand"));
        clnLegalName.setCellValueFactory(new PropertyValueFactory<ClientIiko,String>("legalEntity"));
        clnAddress.setCellValueFactory(new PropertyValueFactory<ClientIiko,String>("address"));
        clnLicense.setCellValueFactory(new PropertyValueFactory<ClientIiko,String>("kindOfLicense"));
        clnStatusOfSupport.setCellValueFactory(new PropertyValueFactory<ClientIiko,String>("statusOfSupport"));
        setupClearButtonField(txtSearch);
        fillData();

    }

    /* метод для заполнения таблицы тест. данными*/
    private void fillData(){

        clientBookImpl=client_dao.findAllClient();// выкачиваем данные и раззрываем
        tableClientBook.setItems(clientBookImpl);//заполняем таблицу интерфейса
    }


    /*Создает крестик очистке в строке ввода поиска*/
    private void setupClearButtonField(CustomTextField customTextField) {
        try {
            Method m = TextFields.class.getDeclaredMethod("setupClearButtonField", TextField.class, ObjectProperty.class);
            m.setAccessible(true);
            m.invoke(null, customTextField, customTextField.rightProperty());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void actionSearch(ActionEvent actionEvent){
        clientBookImpl.clear();
/* Здесь два варианта или выполнять поиск выкачивая данные из БД
* т.е for (ClientIiko clientIiko:client_dao.findAllClient())
* или сделать Backuplist и выполнять поиск в нем в В памяти*/
        String  str= txtSearch.getText().toLowerCase();
        for (ClientIiko clientIiko:client_dao.findAllClient()){
            if (clientIiko.getBrand().toLowerCase().contains(str)||
                        clientIiko.getLegalEntity().toLowerCase().contains(str)||
            clientIiko.getAddress().toLowerCase().contains(str)){
                clientBookImpl.add(clientIiko);
            }
        }
    }



}
