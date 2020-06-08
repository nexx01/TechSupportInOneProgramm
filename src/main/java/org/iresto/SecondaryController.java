package org.iresto;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.iresto.WorkWithBD.DAO.ConnectData_DAO;
import org.iresto.object.impl.WorkComputer.WorkComputer;
import org.iresto.object.impl.clientIiko.ClientIiko;


public class SecondaryController implements Initializable {

    public Label lblLegalEntity;
    public Label lblBrand;
    public Label lblAddress;
    public Label lblStatusOfSupport;
    public TableView tableOfConnectData;
    public TableColumn columnTypePC;
    public TableColumn columnAmmyAdmin;
    public TableColumn columnAnydesk;


    private ClientIiko clientIiko;
   private ObservableList<WorkComputer> workComputersImpl=FXCollections.observableArrayList();
   private ConnectData_DAO connectData_dao=new ConnectData_DAO();

    private ResourceBundle resourceBundle;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public void setClientIiko(ClientIiko clientIiko) {
        if (clientIiko==null){
            return;
        }
        this.clientIiko = clientIiko;
        lblBrand.setText(clientIiko.getBrand());
        lblLegalEntity.setText(clientIiko.getLegalEntity());
        lblAddress.setText(clientIiko.getAddress());
        lblStatusOfSupport.setText(clientIiko.getStatusOfSupport());


        columnAmmyAdmin.setCellValueFactory(new PropertyValueFactory<WorkComputer,String>("IDAmmyAdmin"));
        columnTypePC.setCellValueFactory(new PropertyValueFactory<WorkComputer,String>("typePC"));
        columnAnydesk.setCellValueFactory(new PropertyValueFactory<WorkComputer,String>("IDAnyDesk"));
        fillData(clientIiko.getClientId());
    }

    private void fillData(int clientId){
        workComputersImpl=connectData_dao.findConnectDataByClientId(clientId);// выкачиваем данные и раззрываем
        tableOfConnectData.setItems(workComputersImpl);//заполняем таблицу интерфейса
    }

    /*   @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }*/
}