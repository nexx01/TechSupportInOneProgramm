package org.iresto;



import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.iresto.WorkWithBD.DAO.ConnectData_DAO;
import org.iresto.WorkWithBD.DAO.WebResource_DAO;
import org.iresto.controllers.AddConnectFormController;
import org.iresto.impl.GetConnect;
import org.iresto.object.impl.WorkComputer.WebResourceIiko;
import org.iresto.object.impl.WorkComputer.WorkComputer;
import org.iresto.object.impl.clientIiko.ClientIiko;


public class SecondaryController implements Initializable {

    public Label lblLegalEntity;
    public Label lblBrand;
    public Label lblAddress;
    public Label lblStatusOfSupport;
    public TableView<WorkComputer> tableOfConnectData;
    public TableColumn<WorkComputer, String> columnTypePC;
    public TableColumn<WorkComputer, String> columnAmmyAdmin;
    public TableColumn<WorkComputer, String> columnAnydesk;
    public Button btnConnectByAmmyAdmin;
    public Button btnConnectByAnyDesk;
    public Label lblLicense;
    public TableColumn columnPswWebResource;
    public TableColumn<WebResourceIiko, String> columnAdressWebResource;
    public TableColumn<WebResourceIiko, String> columnNameWebResource;
    public TableView<WebResourceIiko> tableOfWebResources;
    public TableColumn<WebResourceIiko, String> columnLoginWebResource;
    public Button btnGoToWebSite;
    public Button btnEdit;

    private ClientIiko clientIiko;
   private ObservableList<WorkComputer> workComputersImpl=FXCollections.observableArrayList();
   private  ObservableList<WebResourceIiko> webResourceIikosImpl=FXCollections.observableArrayList();
   private ConnectData_DAO connectData_dao=new ConnectData_DAO();
   private WebResource_DAO webResource_dao= new WebResource_DAO();

    private ResourceBundle resourceBundle;
    private FXMLLoader fxmlLoader ; // Не уверен что здесь это нужно и возможно лучше
    //забрать например из ПримариКонтроллера
    private Stage mainstage;
    private Stage windowEditConnectData;
    private Parent fxmlWindowEditConnectData;
    private AddConnectFormController addConnectFormController;

    String nameWindowEditConnectData="AddConnectDataForm.fxml";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }


    //Этот метод фактически заполняет все данные в окне Подключений
    public void showConnectedDataClientIiko(ClientIiko clientIiko, Stage mainstage) {
        if (clientIiko==null){
            return;
        }
        this.mainstage=mainstage;
        this.clientIiko = clientIiko;
        /*
        * заполняем Лэйблы данные о клиенте*/
        lblBrand.setText(clientIiko.getBrand());
        lblLegalEntity.setText(clientIiko.getLegalEntity());
        lblAddress.setText(clientIiko.getAddress());
        lblStatusOfSupport.setText(clientIiko.getStatusOfSupport());
        lblLicense.setText(clientIiko.getKindOfLicense());

        /*определяем колонки таблицы подключения к пк*/
        columnAmmyAdmin.setCellValueFactory(new PropertyValueFactory<WorkComputer,String>("IDAmmyAdmin"));
        columnTypePC.setCellValueFactory(new PropertyValueFactory<WorkComputer,String>("typePC"));
        columnAnydesk.setCellValueFactory(new PropertyValueFactory<WorkComputer,String>("IDAnyDesk"));
        /*заполняем таблицу подключения к пк*/
        fillData(clientIiko.getClientId());

        columnNameWebResource.setCellValueFactory(new  PropertyValueFactory<WebResourceIiko,String>("nameWebResource"));
        columnAdressWebResource.setCellValueFactory(new PropertyValueFactory<WebResourceIiko,String>("webAddress"));
        columnLoginWebResource.setCellValueFactory(new PropertyValueFactory<WebResourceIiko,String>("loginWebResource"));
        fillWebResourceData(clientIiko.getClientId());

    }

    /*заполняем таблицу подключения к пк*/
    private void fillData(int clientId){
        workComputersImpl=connectData_dao.findConnectDataByClientId(clientId);// выкачиваем данные и раззрываем
        tableOfConnectData.setItems(workComputersImpl);//заполняем таблицу интерфейса
    }
    /*заполняем таблицу вебресурсов */
    private void fillWebResourceData(int clienId){
        webResourceIikosImpl=webResource_dao.findEntityByClientId(clienId);// выкачиваем данные и раззрываем
      ;
        tableOfWebResources.setItems(webResourceIikosImpl);//заполняем таблицу интерфейса
    }

public void actionGoToWebLink() throws URISyntaxException {
        WebResourceIiko selectedwebResourceIiko=tableOfWebResources.getSelectionModel().getSelectedItem();
    URI uri = new URI(selectedwebResourceIiko.getWebAddress());
    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
        try {
            desktop.browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    /*Метод определяющий на какую кнопку нажали и проводит соответствующие действия*/
    public void actionButtonPressed(ActionEvent actionEvent) throws URISyntaxException {
        Object source =actionEvent.getSource();

        // если нажата не кнопка - выходим из метода
        if (!(source instanceof Button)) {
            return;
        }

        WorkComputer selectedWorkComputer= tableOfConnectData.getSelectionModel().getSelectedItem();
        Button clickedButton = (Button) source;

        GetConnect getConnect=new GetConnect();

        switch (clickedButton.getId()){
            case "btnConnectByAmmyAdmin":
                if(!workComputerIsSelected(selectedWorkComputer)){
                    return;
                }
                String loginAmmyAdmin= (tableOfConnectData.getSelectionModel().getSelectedItem()).getIDAmmyAdmin();
                String passwordAmmyAdmin=(tableOfConnectData.getSelectionModel().getSelectedItem()).getPswAmmyAdmin();
                getConnect.connectAmmyAdmin(loginAmmyAdmin,passwordAmmyAdmin);
                break;

                case "btnConnectByAnyDesk":
                    String loginAnydesk= (tableOfConnectData.getSelectionModel().getSelectedItem()).getIDAnyDesk();
                    String  passwordAnyDesk= (tableOfConnectData.getSelectionModel().getSelectedItem()).getPswAnyDesk();
                    getConnect.connectAnydesk(loginAnydesk,passwordAnyDesk);
                break;

            case "btnGoToWebSite":
                actionGoToWebLink();
                break;
            case "btnEdit":
               // addConnectFormController.setEditConnectDate(workComputersImpl,webResourceIikosImpl);
                showWindowEditConnectData();

                break;
        }
    }

    /*Определяем был ли выбрана хоть одна строка в таблице*/
private boolean workComputerIsSelected(WorkComputer selectedWorkComputer){
        if (selectedWorkComputer==null){

            return  false;
        }
        return  true;
}

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
    }

private void showWindowEditConnectData(){
    fxmlWindowEditConnectData=initFXMLLoaderWindow(nameWindowEditConnectData,App.pathFXML);
    addConnectFormController=fxmlLoader.getController();
    addConnectFormController.setEditConnectDate(workComputersImpl,webResourceIikosImpl, clientIiko);
    if(windowEditConnectData==null){
        windowEditConnectData=new Stage();
        windowEditConnectData.setScene(new Scene(fxmlWindowEditConnectData));
        windowEditConnectData.setTitle(resourceBundle.getString("Edit"));
        windowEditConnectData.setMinHeight(200);
        windowEditConnectData.setMinWidth(200);
        windowEditConnectData.initModality(Modality.WINDOW_MODAL);
        windowEditConnectData.initOwner(mainstage);
        windowEditConnectData.show();
    }



}
}