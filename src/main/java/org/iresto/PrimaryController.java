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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    private Client_DAO client_dao = new Client_DAO();// создаем экземпляр для подключения к БД
    private ObservableList<ClientIiko> clientBookImpl = FXCollections.observableArrayList();

    private ResourceBundle resourceBundle;

    private FXMLLoader fxmlLoader = new FXMLLoader();
    private Parent fxmlSupport;
    private SecondaryController secondaryController;

    private Stage mainStage;
    private Stage windowOfConnectData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        clnBrand.setCellValueFactory(new PropertyValueFactory<ClientIiko, String>("brand"));
        clnLegalName.setCellValueFactory(new PropertyValueFactory<ClientIiko, String>("legalEntity"));
        clnAddress.setCellValueFactory(new PropertyValueFactory<ClientIiko, String>("address"));
        clnLicense.setCellValueFactory(new PropertyValueFactory<ClientIiko, String>("kindOfLicense"));
        clnStatusOfSupport.setCellValueFactory(new PropertyValueFactory<ClientIiko, String>("statusOfSupport"));
        setupClearButtonField(txtSearch);
        fillData();
        initLoaderWindowSupport();

    }

    /* метод для заполнения таблицы данными из БД*/
    private void fillData() {

        clientBookImpl = client_dao.findAllClient();// выкачиваем данные и раззрываем
        tableClientBook.setItems(clientBookImpl);//заполняем таблицу интерфейса
    }


    /*Создает крестик очистке в строке ввода поиска*/
    private void setupClearButtonField(CustomTextField customTextField) {
        try {
            Method m = TextFields.class.getDeclaredMethod("setupClearButtonField", TextField.class, ObjectProperty.class);
            m.setAccessible(true);
            m.invoke(null, customTextField, customTextField.rightProperty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Метод поиска по таблице*/
    public void actionSearch(ActionEvent actionEvent) {
        clientBookImpl.clear();
        /* Здесь два варианта или выполнять поиск выкачивая данные из БД
         * т.е for (ClientIiko clientIiko:client_dao.findAllClient())
         * или сделать Backuplist и выполнять поиск в нем в В памяти*/
        String str = txtSearch.getText().toLowerCase();
        for (ClientIiko clientIiko : client_dao.findAllClient()) {
            if (clientIiko.getBrand().toLowerCase().contains(str) ||
                        clientIiko.getLegalEntity().toLowerCase().contains(str) ||
                        clientIiko.getAddress().toLowerCase().contains(str)) {
                clientBookImpl.add(clientIiko);
            }
        }
    }


    /*Инициализация второго fxml для данных о подключении*/
    private void initLoaderWindowSupport() {
        try {
            fxmlLoader.setLocation(getClass().getResource("secondary.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("org.iresto.bundle"));
            fxmlSupport = fxmlLoader.load();
            secondaryController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*По нажатию кнопки Подключения вызывается инициализация хагрузки secondary.fxml  и открывается
     * окно с подключениям*/
    public void showWindowConnectData() {
        if (windowOfConnectData == null) {
            windowOfConnectData = new Stage();
            windowOfConnectData.setScene(new Scene(fxmlSupport));
            windowOfConnectData.setTitle(resourceBundle.getString("windowOfConnecnionData"));
            windowOfConnectData.setMinHeight(200);
            windowOfConnectData.setMinWidth(300);
            windowOfConnectData.initModality(Modality.WINDOW_MODAL);
            // windowOfConnectData.initOwner();

        }
        windowOfConnectData.show();
    }


    public void actionButtonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        ClientIiko selectedClientIiko = (ClientIiko) tableClientBook.getSelectionModel().getSelectedItem();

        Button clickedButton = (Button) source;

        switch (clickedButton.getId()) {
            case "btnConnectedData":
                secondaryController.setClientIiko((ClientIiko) tableClientBook.getSelectionModel().getSelectedItem());
                showWindowConnectData();
                break;

        }
    }


private boolean clientIsSelected(AbstractClient selectedClient){
        if (selectedClient==null){

            return false;
        }
        return  true;
}

}
