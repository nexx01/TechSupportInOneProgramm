package org.iresto.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.iresto.App;
import org.iresto.object.impl.WorkComputer.WorkComputer;
import org.iresto.utils.DialogManager;

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
    public Button btnCancel;
    private ResourceBundle resourceBundle;
    private WorkComputer workComputer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
    }


    public void actionSave(ActionEvent actionEvent) {
        if (checkout()) {
            workComputer = new WorkComputer(
                    txtfldTypePC.getText(), txtfldIdAmmyAdmin.getText(),
                    txtfldPswAmmyadmin.getText(), txtfldIdAnyDesk.getText(),
                    txtfldPswAnyDesk.getText());
            actionClose(actionEvent);
        }
    }

    private boolean checkout() {
        if (txtfldTypePC.getText().trim().length() == 0) {
            DialogManager.showErrorDialog("Error", ResourceBundle.getBundle(App.pathBundle).getString("fillTypePC"));
            return false;
        } else if (txtfldIdAmmyAdmin.getText().trim().length() == 0 &
                           txtfldIdAnyDesk.getText().trim().length() == 0) {
            DialogManager.showErrorDialog(ResourceBundle.getBundle(App.pathBundle).getString("Error"),
                    ResourceBundle.getBundle(App.pathBundle).getString("fillIDAmmyOrAny"));
            return false;
        } else if (txtfldIdAmmyAdmin.getText().trim().length() != 0 &
                           txtfldPswAmmyadmin.getText().trim().length() == 0) {
            DialogManager.showErrorDialog(ResourceBundle.getBundle(App.pathBundle).getString("Error"),
                    ResourceBundle.getBundle(App.pathBundle).getString("fillPasswordAmmy"));
            return false;
        } else if (txtfldIdAnyDesk.getText().trim().length() != 0 &
                           txtfldPswAnyDesk.getText().trim().length() == 0) {
            DialogManager.showErrorDialog(ResourceBundle.getBundle(App.pathBundle).getString("Error"),
                    ResourceBundle.getBundle(App.pathBundle).getString("fillPasswordAny"));
        }
        return true;
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public WorkComputer getWorkComputer() {
        return workComputer;
    }
}
