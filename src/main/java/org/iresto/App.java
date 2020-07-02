package org.iresto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.iresto.utils.InitFXMLLoader;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private  Scene scene;
    private  FXMLLoader  fxmlPrimary;
    //public static String pathBundle="org.iresto.bundle";
    private String nameFXML= "/org/iresto/primary.fxml";

    @Override
    public void start(Stage primarystage) throws IOException {
        InitFXMLLoader initFXMLLoaderWindow =new InitFXMLLoader();
        fxmlPrimary =initFXMLLoaderWindow.getFXMLLoader(nameFXML/*, pathBundle*/);
        scene = new Scene(fxmlPrimary.load());
        primarystage.setScene(scene);
        primarystage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}