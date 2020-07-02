package org.iresto.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.iresto.object.impl.WorkComputer.WebResourceIiko;

public class AddWebResourceFormControllers {
    public Label actionSave;
    public TextField txtfldNameOfWebResource;
    public TextField txtfldAddressOfWebResource;
    public TextField txtfldLoginOfWebResource;

    public WebResourceIiko actionSave(ActionEvent actionEvent) {
        WebResourceIiko webResourceIiko=new WebResourceIiko(
                txtfldNameOfWebResource.getText(),
                txtfldAddressOfWebResource.getText(),
                txtfldLoginOfWebResource.getText());
         return webResourceIiko;
    }
}
