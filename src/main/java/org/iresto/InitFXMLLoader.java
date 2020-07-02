package org.iresto;

import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InitFXMLLoader {
    private FXMLLoader fxmlLoader;
 public FXMLLoader getFXMLLoader(String nameFXML, String pathBundle) throws IOException {
     fxmlLoader = new FXMLLoader();
     URL xmurl=getClass().getResource(nameFXML);
     fxmlLoader.setLocation(xmurl);
     fxmlLoader.setResources(ResourceBundle.getBundle(pathBundle));
     return fxmlLoader;
 }
}
