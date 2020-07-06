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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import org.iresto.WorkWithBD.DAO.Client_DAO;
import org.iresto.WorkWithBD.DAO.ConnectData_DAO;
import org.iresto.WorkWithBD.DAO.WebResource_DAO;
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
    public Button btnCloseProgram;
    public Button btnShowAllClient;
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
    private ClientIiko selectedClientIiko;

    String nameFXMLWindowOfConnectData="/org/iresto/secondary.fxml";

    String nameFXMLWindowAddOrEditClient="/org/iresto/AddOrEditClient.fxml";




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        setupClearButtonField(txtSearch);
        tableClientBook.setEditable(true);
    }

    public void showClient(){
        clnBrand.setCellValueFactory(new PropertyValueFactory<ClientIiko, String>("brand"));
        clnLegalName.setCellValueFactory(new PropertyValueFactory<ClientIiko, String>("legalEntity"));
        clnAddress.setCellValueFactory(new PropertyValueFactory<ClientIiko, String>("address"));
        clnLicense.setCellValueFactory(new PropertyValueFactory<ClientIiko, String>("kindOfLicense"));
        clnStatusOfSupport.setCellValueFactory(new PropertyValueFactory<ClientIiko, String>("statusOfSupport"));

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
        selectedClientIiko = (ClientIiko) tableClientBook.getSelectionModel().getSelectedItem();
        if (!(source instanceof Button)) {
            return;
        }



        Button clickedButton = (Button) source;

        switch (clickedButton.getId()) {
            /*При выборе кнопки btnConnectedData
             * 1)запускается метод initFXMLLoaderWindow(nameFXMLWindowOfConnectData,pathFXMLWindowOfConnectData)
             * Он загружает FXML файл
             * 2) Вызывается метод setClientIiko(...), слушает какой клиент(строчка) в таблице
             * 3) showWindowConnectData(selectedClientIiko) формирует сцену с подключениями */
            case "btnConnectedData":
                if(!clientIsSelected(selectedClientIiko)){
                    return;
                }
                //initFXMLLoaderWindow(nameFXMLWindowOfConnectData,pathFXMLWindowOfConnectData);
                showWindowConnectData(selectedClientIiko);
                windowOfConnectData=null;
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
                clientBookImpl.add(addClientFormController.getClient());
                windowOfConnectData=null;
                break;
            case "btnDelete":
                if(!clientIsSelected(selectedClientIiko)){
                    return;
                }
                if(checkoutBeforeDeleteClient(selectedClientIiko.getClientId())) {
                    client_dao.deleteClient(selectedClientIiko);
                    clientBookImpl.remove(selectedClientIiko);
                }
                break;
            case "btnCloseProgram":
                System.exit(0);
            break;
            case "btnShowAllClient":
                showClient();
            break;
        }
    }





    @FXML
    private void actionDoubleClickInTable(MouseEvent mouseButton){

    if(mouseButton.getClickCount()==2){
        windowOfConnectData=null;
        selectedClientIiko = (ClientIiko) tableClientBook.getSelectionModel().getSelectedItem();
            if (!clientIsSelected(selectedClientIiko)) {
                return;
            }
            //initFXMLLoaderWindow(nameFXMLWindowOfConnectData,pathFXMLWindowOfConnectData);

            showWindowConnectData(selectedClientIiko);
        }
    }

    private boolean checkoutBeforeDeleteClient(int id_client){
        WebResource_DAO webResource_dao=new WebResource_DAO();
        ConnectData_DAO connectData_dao=new ConnectData_DAO();
        if(webResource_dao.findEntityByClientId(id_client).size()!=0|
        connectData_dao.findConnectDataByClientId(id_client).size()!=0){
            DialogManager.showInfoDialog(ResourceBundle.getBundle(App.pathBundle).getString("Error"),
                    ResourceBundle.getBundle(App.pathBundle).getString("ForbiddenToDelete"));
            return false;
        }
        return true;
    }


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
            secondaryController.showConnectedDataClientIiko(selectedClientIiko, windowOfConnectData);
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
               // windowOfConnectData.initModality(Modality.WINDOW_MODAL);
                windowOfConnectData.initModality(Modality.APPLICATION_MODAL);
                // windowOfConnectData.initOwner();

            }
            windowOfConnectData.showAndWait();
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
            windowAddOrEditClient.initModality(Modality.APPLICATION_MODAL);
            //windowAddOrEditClient.initOwner(mainStage);
            windowAddOrEditClient.showAndWait();
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
