package ec.edu.espol.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ec.edu.espol.model.DatoNoCompletadoException;
import ec.edu.espol.model.Dueño;
import ec.edu.espol.model.Mascota;
import ec.edu.espol.utilitario.Util;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private TextField tipo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

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
}
