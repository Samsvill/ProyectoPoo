/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.DatoNoCompletadoException;
import ec.edu.espol.model.Dueño;
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
 * @author Abraham
 */
public class DueñoGrafoController implements Initializable {

    @FXML
    private TextField nameDueño;
    @FXML
    private TextField apDueño;
    @FXML
    private TextField tlfDueño;
    @FXML
    private TextField eMailDueño;
    @FXML
    private TextField direcDueño;
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
        nameDueño.setText("");
        apDueño.setText("");
        tlfDueño.setText("");
        eMailDueño.setText("");
        direcDueño.setText("");
        //Aqui no se setea ninguna escena, porque este metodo es llamado apenas abre la ventana, osea apenas se setea la scene para pasarle el grafo debido
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
    private void guardarDueño(MouseEvent event) {
        try {
            String nombreD = nameDueño.getText();
            String apellD = apDueño.getText();
            String tlfD = tlfDueño.getText();
            String emlD = eMailDueño.getText();
            String dirD = direcDueño.getText();
            Dueño dueño = new Dueño(Util.nextID("dueños.txt"), nombreD, apellD, tlfD, emlD, dirD);
            dueño.saveFile("dueños.txt");
        } catch (DatoNoCompletadoException ex) {
            Alert a = new Alert(AlertType.ERROR, "NO HA INGRESADO el nombre del Dueño, ó el/los apellidos del Dueño, ó\n"
                    + "el teléfono del Dueño, ó el email del Dueño, ó su dirección.");
            a.show();
        }
        nameDueño.setText("");
        apDueño.setText("");
        tlfDueño.setText("");
        eMailDueño.setText("");
        direcDueño.setText("");
    }
    
}
