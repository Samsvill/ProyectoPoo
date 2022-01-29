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
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            App.setRoot("menugrafo");
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
            ex.printStackTrace();
        }
        nameDueño.setText("");
        apDueño.setText("");
        tlfDueño.setText("");
        eMailDueño.setText("");
        direcDueño.setText("");
    }
    
}
