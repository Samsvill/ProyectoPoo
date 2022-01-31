package ec.edu.espol.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ec.edu.espol.model.DatoNoCompletadoException;
import ec.edu.espol.model.Dueño;
import ec.edu.espol.model.Mascota;
import ec.edu.espol.proyectolinterfazabsamjo.App;
import ec.edu.espol.utilitario.Util;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author saman
 */
public class MascotaGrafoController implements Initializable {

    @FXML
    private Button Re;
    @FXML
    private TextField email;
    @FXML
    private TextField nombreMascota;
    @FXML
    private TextField raza;
    @FXML
    private TextField birthday;
    @FXML
    private TextField pathimage;
    private TextField tipo;
    @FXML
    private BorderPane panel;

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
        panel.setBackground(bGround);
    }

    @FXML
    private void guardarMascota(MouseEvent event) {
        try {
            String name = nombreMascota.getText();
            String type = tipo.getText();
            String race = raza.getText();
            LocalDate fecha = LocalDate.parse(birthday.getText());
            if (Dueño.obtenerDueñoXEmail(email.getText()) == null){
                Alert a = new Alert(AlertType.ERROR,"El email está vacio o no existe el dueño, favor de revisar.");
                a.show();
            }
            int id_dueño = Dueño.obtenerDueñoXEmail(email.getText()).getId();
            Mascota pet = new Mascota(Util.nextID("mascota.txt"), id_dueño, name, type, race, fecha);
            pet.saveFile("mascotas.txt");
        } catch(DatoNoCompletadoException e){
            Alert a = new Alert(AlertType.ERROR,"Uno de sus campos no está lleno, revise e inténtelo de nuevo.");
            a.show();
        } catch(NullPointerException e){
            e.getMessage();
        }
        
        email.setText("");
        nombreMascota.setText("");
        raza.setText("");
        birthday.setText("");
        tipo.setText("");
        //pathimage.setText("");

    }

    @FXML
    private void irAlMenu(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Menu.fxml"));
            Parent root;
            root = fxmlLoader.load();
            App.scene.setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
