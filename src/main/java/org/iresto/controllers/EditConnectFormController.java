package org.iresto.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.iresto.App;
import org.iresto.WorkWithBD.DAO.ConnectData_DAO;
import org.iresto.WorkWithBD.DAO.WebResource_DAO;
import org.iresto.object.impl.WorkComputer.WebResourceIiko;
import org.iresto.object.impl.WorkComputer.WorkComputer;
import org.iresto.object.impl.clientIiko.ClientIiko;
import org.iresto.utils.InitFXMLLoader;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class EditConnectFormController implements Initializable {
    public TableColumn columnTypePC;
    public TableColumn columnAmmyAdmin;
    public TableColumn columnAnydesk;
    public TableColumn columnPswAnydesk;
    public TableColumn columnNameWebResource;
    public TableColumn columnAdressWebResource;
    public TableColumn columnLoginWebResource;
    public TableColumn columnPswAmmy;
    public TableView tableOfConnectData;
    public TableView tableOfWebResources;
    public Button btnDeleteWebResource;
    public Button btnAddWebResource;
    public Button btnDeleteConnectData;
    public Button btnAddConnectData;
    public Button btnExitFromEdit;
    private ClientIiko clientIiko;
    private ObservableList<WorkComputer> workComputersImpl;
    private ObservableList<WebResourceIiko> webResourceIikosImpl;
    private ConnectData_DAO connectData_dao = new ConnectData_DAO();
    private WebResource_DAO webResource_dao = new WebResource_DAO();

    private Stage windowAddConnectData;
    private Stage windowAddWebResourceForm;

    public FXMLLoader fxmlLoader;
    private String nameFXMLAddConnectForm = "/org/iresto/AddConnectForm.fxml";
    private Parent fxmlWindowAddConnectForm;
    private String nameFXMLAddWebResource = "/org/iresto/AddWebResourceForm.fxml";
    private Parent fxmlWindowAddWebResourceForm;
    private AddConnectController addConnectController;
    private AddWebResourceFormControllers addWebResourceFormControllers;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setEditConnectDate(ObservableList<WorkComputer> workComputersImpl,
                                   ObservableList<WebResourceIiko> webResourceIikosImpl,
                                   ClientIiko clientIiko) {
        if (clientIiko == null) {
            return;
        }
        this.clientIiko = clientIiko;
        this.workComputersImpl = workComputersImpl;
        this.webResourceIikosImpl = webResourceIikosImpl;

        columnTypePC.setCellValueFactory(new PropertyValueFactory<>("typePC"));
        columnAmmyAdmin.setCellValueFactory(new PropertyValueFactory<>("IDAmmyAdmin"));
        //columnPswAmmy.setCellValueFactory(new PropertyValueFactory<>("pswAmmyAdmin"));
        columnPswAmmy.setCellValueFactory(new PropertyValueFactory<>("pswAmmyAdmin"));
        columnAnydesk.setCellValueFactory(new PropertyValueFactory<>("IDAnyDesk"));
        columnPswAnydesk.setCellValueFactory(new PropertyValueFactory<>("pswAnyDesk"));
        fillData(workComputersImpl);
        tableOfConnectData.setEditable(true);

        columnTypePC.setCellFactory(TextFieldTableCell.forTableColumn());
        columnAmmyAdmin.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPswAmmy.setCellFactory(TextFieldTableCell.forTableColumn());
        columnAnydesk.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPswAnydesk.setCellFactory(TextFieldTableCell.forTableColumn());


        columnNameWebResource.setCellValueFactory(new PropertyValueFactory<>("nameWebResource"));
        columnAdressWebResource.setCellValueFactory(new PropertyValueFactory<>("webAddress"));
        columnLoginWebResource.setCellValueFactory(new PropertyValueFactory<>("loginWebResource"));
        fillWebResourceData(webResourceIikosImpl);
        tableOfWebResources.setEditable(true);
        //return workComputersImpl;

        columnNameWebResource.setCellFactory(TextFieldTableCell.forTableColumn());
        columnAdressWebResource.setCellFactory(TextFieldTableCell.forTableColumn());
        columnLoginWebResource.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void fillData(ObservableList<WorkComputer> workComputersImpl) {
        tableOfConnectData.setItems(workComputersImpl);//заполняем таблицу интерфейса
    }

    private void fillWebResourceData(ObservableList<WebResourceIiko> webResourceIikosImpl) {
        tableOfWebResources.setItems(webResourceIikosImpl);
    }

    public void onEditChanged(TableColumn.CellEditEvent cellEditEvent) {
        WorkComputer workComputer = (WorkComputer) tableOfConnectData.getSelectionModel().getSelectedItem();
        switch (cellEditEvent.getTableColumn().getId()) {
            case "columnTypePC":
                workComputer.setTypePC((String) cellEditEvent.getNewValue());
                break;
            case "columnAmmyAdmin":
                workComputer.setIDAmmyAdmin((String) cellEditEvent.getNewValue());
                break;
            case "columnPswAmmy":
                workComputer.setPswAmmyAdmin((String) cellEditEvent.getNewValue());
                break;
            case "columnAnydesk":
                workComputer.setIDAnyDesk((String) cellEditEvent.getNewValue());
                break;
            case "columnPswAnydesk":
                workComputer.setPswAnyDesk((String) cellEditEvent.getNewValue());
                break;

        }
        connectData_dao.insertNewConnectData(workComputer, clientIiko.getClientId());

        // setEditConnectDate(workComputersImpl, webResourceIikosImpl, clientIiko);

        // return workComputersImpl;
    }

    public void onEditCangedTebleWebResources(TableColumn.CellEditEvent cellEditEvent) {
        WebResourceIiko webResourceIiko = (WebResourceIiko) tableOfWebResources.getSelectionModel().getSelectedItem();
        switch (cellEditEvent.getTableColumn().getId()) {
            case "columnNameWebResource":
                webResourceIiko.setNameWebResource((String) cellEditEvent.getNewValue());
                break;
            case "columnAdressWebResource":
                webResourceIiko.setWebAddress((String) cellEditEvent.getNewValue());
                break;
            case "columnLoginWebResource":
                webResourceIiko.setLoginWebResource((String) cellEditEvent.getNewValue());
                break;

        }

        webResource_dao.insertNewConnectData(webResourceIiko, clientIiko.getClientId());
    }

    public void actionCloseWindow(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void actionDeleteWebResource(ActionEvent actionEvent) {
        WebResourceIiko webResourceIiko = (WebResourceIiko) tableOfWebResources.getSelectionModel().getSelectedItem();
        webResource_dao.deleteWebResourceIiko(webResourceIiko);
        webResourceIikosImpl.remove(webResourceIiko);

        //setEditConnectDate(workComputersImpl,webResourceIikosImpl,clientIiko);

    }

    public void actionAddWebResource(ActionEvent actionEvent) {
        showWindowAddWebResource();
if(addWebResourceFormControllers.getWebResourceIiko()!=null) {
    webResource_dao.insertNewConnectData(addWebResourceFormControllers.getWebResourceIiko(),
            clientIiko.getClientId());
    webResourceIikosImpl.add(addWebResourceFormControllers.getWebResourceIiko());
}
    }

    public void actionAddConnectData(ActionEvent actionEvent) {
        showWindowAddConnectData();
        if(addConnectController.getWorkComputer()!=null) {
            connectData_dao.insertNewConnectData(addConnectController.getWorkComputer(), clientIiko.getClientId());
            workComputersImpl.add(addConnectController.getWorkComputer());
            // connectData_dao.insertNewConnectData()
        }
    }

    public void actionDeleteConnectData(ActionEvent actionEvent) {
        WorkComputer workComputer = (WorkComputer) tableOfWebResources.getSelectionModel().getSelectedItem();
        connectData_dao.deleteConnectData(workComputer);
        workComputersImpl.remove(workComputer);
    }

    private void showWindowAddConnectData() {
        InitFXMLLoader initFXMLLoader = new InitFXMLLoader();
        try {
            fxmlLoader = initFXMLLoader.getFXMLLoader(nameFXMLAddConnectForm);
            fxmlWindowAddConnectForm = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addConnectController=fxmlLoader.getController();
        //fxmlLoader.setResources(ResourceBundle.getBundle(App.pathFXML));
        windowAddConnectData = new Stage();
        windowAddConnectData.setScene(new Scene(fxmlWindowAddConnectForm));
        windowAddConnectData.setResizable(false);
       // windowAddConnectData.initModality(Modality.APPLICATION_MODAL);
        windowAddConnectData.showAndWait();

    }

    private void showWindowAddWebResource() {
        InitFXMLLoader initFXMLLoader = new InitFXMLLoader();
        try {
            fxmlLoader = initFXMLLoader.getFXMLLoader(nameFXMLAddWebResource);
            fxmlWindowAddWebResourceForm = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addWebResourceFormControllers=fxmlLoader.getController();
        //AddConnectController addConnectController=fxmlLoader.getController();

        windowAddWebResourceForm = new Stage();
        windowAddWebResourceForm.setScene(new Scene(fxmlWindowAddWebResourceForm));
        windowAddWebResourceForm.setResizable(false);
        windowAddWebResourceForm.initModality(Modality.APPLICATION_MODAL);
        windowAddWebResourceForm.showAndWait();

    }
}
