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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
//            FXMLLoader loader = new FXMLLoader(App.class.getResource("menugrafo"));
//            App.scene.setRoot(loader.load());
            //FXMLLoader loader = App.loadFXML("dueñografo");
            App.setRoot("menugrafo");
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
