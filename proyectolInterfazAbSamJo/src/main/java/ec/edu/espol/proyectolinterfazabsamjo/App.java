package ec.edu.espol.proyectolinterfazabsamjo;

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

    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Menu.fxml"));
        Parent root = fxmlLoader.load();
        scene = new Scene(root, 600, 430);
        stage.setScene(scene);
        stage.show();
//        scene = new Scene(loadFXML("miembrojuradografo").load(), 640, 480);
//        stage.setScene(scene);
//        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}