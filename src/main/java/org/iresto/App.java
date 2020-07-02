package org.iresto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private  Scene scene;
    private  FXMLLoader  fxmlPrimary;
    public static String pathFXML="org.iresto.bundle";


    @Override
    public void start(Stage primarystage) throws IOException {
       /* fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(App.class.getResource( "primary.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("org.iresto.bundle"));*/
/*
        Parent fxmlPrimary=fxmlLoader.load();*/

        InitFXMLLoaderWindow initFXMLLoaderWindow =new InitFXMLLoaderWindow();
        fxmlPrimary =initFXMLLoaderWindow.getFXMLLoader("/primary", pathFXML);
        //initFXMLLoaderWindow.getFXMLLoader("/primary", pathFXML);
        /*PrimaryController primaryController=fxmlPrimary.getController();
        primaryController.setMainStage(primarystage);*/


        scene = new Scene(fxmlPrimary.load());
        //scene.setRoot(fxmlPrimary.load());
        primarystage.setScene(scene);
        primarystage.show();
    }

   /*  void setRoot(String fxml) throws IOException {
        scene.setRoot(fxmlPrimary);
    }*/

   /*private static FXMLLoader getFxmlLoader(String fxml){
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("org.iresto.bundle"));
        return fxmlLoader;
    }

    private  Parent loadFXML(String fxml) throws IOException {
        fxmlLoader = new FXMLLoader();
        //App.class.getResource(fxml + ".fxml")
        fxmlLoader.setLocation(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("org.iresto.bundle"));
        return fxmlLoader.load();
    }*//**//*
*/


    public static void main(String[] args) {
        launch();
    }

}