package org.iresto;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
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
    private Client_DAO clientBookImpl= new Client_DAO();

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
        tableClientBook.setItems(clientBookImpl.findAllClient());
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



}
