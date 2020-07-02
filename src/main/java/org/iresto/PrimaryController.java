package org.iresto;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import org.iresto.WorkWithBD.DAO.Client_DAO;
import org.iresto.controllers.AddClientFormController;
import org.iresto.object.AbstractClientIIKO;
import org.iresto.object.impl.clientIiko.ClientIiko;
import org.iresto.utils.DialogManager;
import org.iresto.utils.InitFXMLLoader;

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
    final private Client_DAO client_dao = new Client_DAO();// создаем экземпляр для подключения к БД
    public Button btnEdit;
    public Button btnAdd;
    public Button btnDelete;
    private ObservableList<ClientIiko> clientBookImpl = FXCollections.observableArrayList();

    public ResourceBundle resourceBundle;

    private FXMLLoader fxmlLoader ;
    private Parent fxmlSupport;
    private Parent fxmlWindowAddOrEditClient;
    private SecondaryController secondaryController;
    public AddClientFormController addClientFormController;

    private Stage mainStage;
    private Stage windowOfConnectData;
    private Stage windowAddOrEditClient;

    String nameFXMLWindowOfConnectData="/org/iresto/secondary.fxml";

    String nameFXMLWindowAddOrEditClient="/org/iresto/AddOrEditClient.fxml";




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
    }

    public void setMainStage(Stage mainStage){
        this.mainStage=mainStage;
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


/*метод определяет на какую кнопку нажали и проводит соответсвующие действия*/
    public void actionButtonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        ClientIiko selectedClientIiko = (ClientIiko) tableClientBook.getSelectionModel().getSelectedItem();

        Button clickedButton = (Button) source;

        switch (clickedButton.getId()) {
            /*При выборе кнопки btnConnectedData
             * 1)запускается метод initFXMLLoaderWindow(nameFXMLWindowOfConnectData,pathFXMLWindowOfConnectData)
             * Он загружает FXML файл
             * 2) Вызывается метод setClientIiko(...), слушает какой клиент(строчка) в таблице
             * 3) showWindowConnectData(selectedClientIiko) формирует сцену с подключениями */
            case "btnConnectedData":
                //initFXMLLoaderWindow(nameFXMLWindowOfConnectData,pathFXMLWindowOfConnectData);
                showWindowConnectData(selectedClientIiko);
                break;
            case "btnEdit":
                if(!clientIsSelected(selectedClientIiko)){
                    return;
                }
                showWindowAddOrEditClient(resourceBundle.getString("Edit")
                                                  + selectedClientIiko.getBrand()+ " "
                                                  +selectedClientIiko.getLegalEntity()+" "
                +selectedClientIiko.getAddress(), mainStage);
                addClientFormController.setClient(selectedClientIiko);
                windowOfConnectData=null;// обнуляем окно , оно почему то не обнуляется

                break;
            case "btnAdd":
                showWindowAddOrEditClient(resourceBundle.getString("Add"), mainStage);
                windowOfConnectData=null;
                break;
            case "btnDelete":
                break;

        }
    }


/*    *//*Инициализатор fxml Универсальный*//*
    private Parent initFXMLLoaderWindow(String nameFXML, String pathFXML ) {
        try {
            fxmlLoader=new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(nameFXML));
            fxmlLoader.setResources(ResourceBundle.getBundle(pathFXML));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/


    /*По нажатию кнопки Подключения вызывается инициализация хагрузки secondary.fxml  и открывается
     * окно с подключениям*/
    public void showWindowConnectData(ClientIiko selectedClientIiko) {
        InitFXMLLoader initFXMLLoader=new InitFXMLLoader();
            try {
                fxmlLoader = initFXMLLoader.getFXMLLoader(nameFXMLWindowOfConnectData);
                fxmlSupport = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            secondaryController = fxmlLoader.getController();
            secondaryController.showConnectedDataClientIiko((ClientIiko) tableClientBook.getSelectionModel().getSelectedItem(), windowOfConnectData);
            //  ссылку на окно передаем, чтобы сделать мэйнстэйдж и следующее окно будет модальным

            if (windowOfConnectData == null) { // условие чтобы открывалось одно второе окно
                windowOfConnectData = new Stage();
                windowOfConnectData.setScene(new Scene(fxmlSupport));
                String titleOfWindow =
                        selectedClientIiko.getBrand() + " " + selectedClientIiko.getLegalEntity()
                                + " " + selectedClientIiko.getAddress();
                windowOfConnectData.setTitle(titleOfWindow);
                windowOfConnectData.setMinHeight(200);
                windowOfConnectData.setMinWidth(300);
                windowOfConnectData.initModality(Modality.WINDOW_MODAL);
                // windowOfConnectData.initOwner();

            }
            windowOfConnectData.show();
        }


    public void showWindowAddOrEditClient(String titleOfWindow, Stage mainStage){
        InitFXMLLoader initFXMLLoader= new InitFXMLLoader();
        try {
            fxmlLoader = initFXMLLoader.getFXMLLoader(nameFXMLWindowAddOrEditClient);
            fxmlWindowAddOrEditClient = fxmlLoader.load();
        } catch(IOException e){
            e.printStackTrace();
        }
        /*т.к. окно модалити , то проверка на NULL не требуется*/
        //fxmlWindowAddOrEditClient=initFXMLLoaderWindow(nameFXMLWindowAddOrEditClient);
        addClientFormController=fxmlLoader.getController();
        if (windowOfConnectData == null) {
            windowAddOrEditClient = new Stage();
            windowAddOrEditClient.setScene(new Scene((fxmlWindowAddOrEditClient)));
            windowAddOrEditClient.setTitle(titleOfWindow);
            windowAddOrEditClient.setMinWidth(150);
            windowAddOrEditClient.setMinHeight(200);
            windowAddOrEditClient.initModality(Modality.WINDOW_MODAL);
            windowAddOrEditClient.initOwner(mainStage);
            windowAddOrEditClient.show();
        }
        }


    private  boolean clientIsSelected(AbstractClientIIKO selectedClient){
        if(selectedClient==null){
            DialogManager.showInfoDialog(resourceBundle.getString("Error"), resourceBundle.getString("SelectedPerson"));
            return false;
        }
        return true;
    }

}
