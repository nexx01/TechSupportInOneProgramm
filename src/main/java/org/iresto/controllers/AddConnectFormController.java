package org.iresto.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.iresto.WorkWithBD.DAO.ConnectData_DAO;
import org.iresto.object.impl.WorkComputer.WebResourceIiko;
import org.iresto.object.impl.WorkComputer.WorkComputer;
import org.iresto.object.impl.clientIiko.ClientIiko;

import java.net.URL;
import java.util.ResourceBundle;

public class AddConnectFormController implements Initializable {
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
    private ClientIiko clientIiko;
    private ObservableList<WorkComputer> workComputersImpl;
    private ObservableList<WebResourceIiko> webResourceIikosImpl;
    private ConnectData_DAO connectData_dao= new ConnectData_DAO();


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
        this.workComputersImpl=workComputersImpl;
        this.webResourceIikosImpl=webResourceIikosImpl;

        columnTypePC.setCellValueFactory(new PropertyValueFactory<>("IDAmmyAdmin"));
        columnAmmyAdmin.setCellValueFactory(new PropertyValueFactory<>("typePC"));
        columnPswAmmy.setCellValueFactory(new PropertyValueFactory<>("pswAmmyAdmin"));
        columnAnydesk.setCellValueFactory(new PropertyValueFactory<>("IDAnyDesk"));
        columnPswAnydesk.setCellValueFactory(new PropertyValueFactory<>("pswAnyDesk"));
        fillData(workComputersImpl);
        tableOfConnectData.setEditable(true);
        columnTypePC.setCellFactory(TextFieldTableCell.forTableColumn());


        columnNameWebResource.setCellValueFactory(new PropertyValueFactory<>("nameWebResource"));
        columnAdressWebResource.setCellValueFactory(new PropertyValueFactory<>("webAddress"));
        columnLoginWebResource.setCellValueFactory(new PropertyValueFactory<>("loginWebResource"));
        fillWebResourceData(webResourceIikosImpl);
        tableOfWebResources.setEditable(true);
        //return workComputersImpl;
    }

    private void fillData(ObservableList<WorkComputer> workComputersImpl) {
        tableOfConnectData.setItems(workComputersImpl);//заполняем таблицу интерфейса
    }

    private void fillWebResourceData(ObservableList<WebResourceIiko> webResourceIikosImpl) {
        tableOfWebResources.setItems(webResourceIikosImpl);
    }

    public void onEditChanged(TableColumn.CellEditEvent cellEditEvent) {
        WorkComputer workComputer = (WorkComputer) tableOfConnectData.getSelectionModel().getSelectedItem();
        workComputer.setTypePC((String) cellEditEvent.getNewValue());
        connectData_dao.insertNewConnectData(workComputer,clientIiko.getClientId());

        // setEditConnectDate(workComputersImpl, webResourceIikosImpl, clientIiko);

       // return workComputersImpl;
    }
}
