/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Rebeca
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
        
    }    

    @FXML
    private void regresarMenu(MouseEvent event) {
    }

    @FXML
    private void guardarDueño(MouseEvent event) {
    }
    
}
