package org.iresto.utils;

import javafx.fxml.FXMLLoader;
import org.iresto.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InitFXMLLoader {
    private FXMLLoader fxmlLoader;
 public FXMLLoader getFXMLLoader(String nameFXML) throws IOException {
     fxmlLoader = new FXMLLoader();
     URL xmurl=getClass().getResource(nameFXML);
     fxmlLoader.setLocation(xmurl);
     fxmlLoader.setResources(ResourceBundle.getBundle("org.iresto.bundle"));
     return fxmlLoader;
 }
}
