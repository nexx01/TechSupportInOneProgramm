package org.iresto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SecondaryController implements Initializable {

    private ResourceBundle resourceBundle;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

 /*   @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }*/
}