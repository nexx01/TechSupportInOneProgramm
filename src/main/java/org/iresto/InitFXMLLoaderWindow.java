package org.iresto;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InitFXMLLoaderWindow {
    private FXMLLoader fxmlLoader;
 public FXMLLoader getFXMLLoader(String nameFXML, String pathBundle) throws IOException {
     fxmlLoader = new FXMLLoader();
     URL xmurl=getClass().getResource("/org/iresto/primary.fxml");
     fxmlLoader.setLocation(xmurl);
     fxmlLoader.setResources(ResourceBundle.getBundle(pathBundle));
     return fxmlLoader;
 }


}
