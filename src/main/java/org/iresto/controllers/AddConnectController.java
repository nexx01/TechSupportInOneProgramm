package org.iresto.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.iresto.object.impl.WorkComputer.WorkComputer;

import java.net.URL;
import java.util.ResourceBundle;

public class AddConnectController implements Initializable {
    public TextField txtfldTypePC;
    public TextField txtfldIdAmmyAdmin;
    public TextField txtfldPswAmmyadmin;
    public TextField txtfldIdAnyDesk;
    public TextField txtfldPswAnyDesk;
    public Label lblTypePC;
    public Label lblAmmyadmin;
    public Label lblPswAmmyAdmin;
    public Label lblIdAnyDesk;
    public Label lblPswAnyDesk;
    public Button btnOK;
    private ResourceBundle resourceBundle;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle=resources;
    }


    public WorkComputer actionSave(ActionEvent actionEvent) {
        WorkComputer workComputer = new WorkComputer(
                txtfldTypePC.getText(), txtfldIdAmmyAdmin.getText(),
                txtfldPswAmmyadmin.getText(), txtfldIdAnyDesk.getText(),
                txtfldPswAnyDesk.getText());
        return workComputer;
    }
}
