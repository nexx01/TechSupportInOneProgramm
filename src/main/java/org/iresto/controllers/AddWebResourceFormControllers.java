package org.iresto.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.iresto.App;
import org.iresto.object.impl.WorkComputer.WebResourceIiko;
import org.iresto.utils.DialogManager;

import java.util.ResourceBundle;

public class AddWebResourceFormControllers {
    public Label actionSave;
    public TextField txtfldNameOfWebResource;
    public TextField txtfldAddressOfWebResource;
    public TextField txtfldLoginOfWebResource;
    private WebResourceIiko webResourceIiko;

    public void actionSave(ActionEvent actionEvent) {
        if (checkout()) {
             webResourceIiko = new WebResourceIiko(
                    txtfldNameOfWebResource.getText(),
                    txtfldAddressOfWebResource.getText(),
                    txtfldLoginOfWebResource.getText());
            actionClose(actionEvent);
        }

    }


    private boolean checkout() {
        if (txtfldAddressOfWebResource.getText().trim().length() == 0) {
                    DialogManager.showErrorDialog(ResourceBundle.getBundle(App.pathBundle).getString("Error"),
                            ResourceBundle.getBundle(App.pathBundle).getString("fillAddressOfWebResource"));
            return false;
        } else if (txtfldNameOfWebResource.getText().trim().length() == 0) {
            DialogManager.showErrorDialog(ResourceBundle.getBundle(App.pathBundle).getString("Error"),
                    ResourceBundle.getBundle(App.pathBundle).getString("fillNameOfWebResource"));
            return false;
        } else if (txtfldLoginOfWebResource.getText().trim().length() == 0) {
            DialogManager.showErrorDialog(ResourceBundle.getBundle(App.pathBundle).getString("Error"),
                    ResourceBundle.getBundle(App.pathBundle).getString("fillPasswordAmmy"));
            return false;
        }
        return true;
    }


    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public WebResourceIiko getWebResourceIiko() {
        return webResourceIiko;
    }
}
