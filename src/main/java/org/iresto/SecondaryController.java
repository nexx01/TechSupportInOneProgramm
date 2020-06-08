package org.iresto;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.iresto.object.impl.clientIiko.ClientIiko;


public class SecondaryController implements Initializable {

    public Label lblLegalEntity;
    public Label lblBrand;
    public Label lblAddress;
    public Label lblStatusOfSupport;
    private ClientIiko clientIiko;


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
    }

    /*   @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }*/
}