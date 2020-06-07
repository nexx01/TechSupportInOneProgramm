package org.iresto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    //Создаем объект Типа CollectionClienBook с названием clienBookImpl
    /*т.е планируется использовать не только для это типа нужно будет сделать
    как то более универсально.
    Хотя эта таблица заполняется именно этим типом
    * */
    private CollectionClienBook clienBookImpl = new CollectionClienBook();

    private ResourceBundle resourceBundle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        clnBrand.setCellValueFactory(new PropertyValueFactory<ClientIiko, String>("brand"));
        clnLegalName.setCellValueFactory(new PropertyValueFactory<ClientIiko,String>("legalEntity"));
        clnAddress.setCellValueFactory(new PropertyValueFactory<ClientIiko,String>("address"));
        clnLicense.setCellValueFactory(new PropertyValueFactory<ClientIiko,String>("kindOfLicense"));
        clnStatusOfSupport.setCellValueFactory(new PropertyValueFactory<ClientIiko,String>("statusOfSupport"));
        fillData();

    }

    /*Тестовый метод для заполнения таблицы тест. данными*/
    private void fillData(){
        /*DВызываем созданный объект типа CollectionClienBook и его метод с т.данными*/
        clienBookImpl.fillTestDataClientIiko();

        tableClientBook.setItems(clienBookImpl.getClientIikoObservableList());
    }


}
