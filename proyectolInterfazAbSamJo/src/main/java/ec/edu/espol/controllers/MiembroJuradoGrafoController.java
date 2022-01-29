/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.DatoNoCompletadoException;
import ec.edu.espol.model.MiembroJurado;
import ec.edu.espol.proyectolinterfazabsamjo.App;
import ec.edu.espol.utilitario.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abraham
 */
public class MiembroJuradoGrafoController implements Initializable {

    @FXML
    private TextField nameMbJurado;
    @FXML
    private TextField apMbJurado;
    @FXML
    private TextField tlfMbJurado;
    @FXML
    private TextField emailMbJurado;
    @FXML
    private TextField descpMbJurado;
    @FXML
    private BorderPane bpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            Image img = new Image("Imagenes\\mascotas.jpg");
            BackgroundImage bImg = new BackgroundImage(img,
                                                       BackgroundRepeat.REPEAT,
                                                       BackgroundRepeat.REPEAT,
                                                       BackgroundPosition.CENTER,
                                                       BackgroundSize.DEFAULT);

            Background bGround = new Background(bImg);
            bpane.setBackground(bGround);
        //try {
            //        Stage stg = (Stage)nameMbJurado.getScene().getWindow();
//        stg.show();
            //FXMLLoader loader = new FXMLLoader(App.class.getResource("miembroJuradoGrafo"));
            //App.scene.setRoot(loader.load());
            nameMbJurado.setText("");
            apMbJurado.setText("");
            tlfMbJurado.setText("");
            emailMbJurado.setText("");
            descpMbJurado.setText("");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }    

    @FXML
    private void regresarMenu(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Menu.fxml"));
            Parent root;
            root = fxmlLoader.load();
            App.scene.setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void guardarMbJurado(MouseEvent event) {
        try {
            String nombre = nameMbJurado.getText();
            String apellidos = apMbJurado.getText();
            String telefono = tlfMbJurado.getText();
            String email = emailMbJurado.getText();
            String descrip = descpMbJurado.getText();
            MiembroJurado mjb = new MiembroJurado(Util.nextID("miembroJurados.txt"), nombre, apellidos, telefono, email, descrip);
            mjb.saveFile("miembroJurados.txt");
        } catch (DatoNoCompletadoException ex) {
            Alert al = new Alert(AlertType.ERROR, ex.getMessage());
            al.show();
        }
        nameMbJurado.setText("");
        apMbJurado.setText("");
        tlfMbJurado.setText("");
        emailMbJurado.setText("");
        descpMbJurado.setText("");
    }
    
}
