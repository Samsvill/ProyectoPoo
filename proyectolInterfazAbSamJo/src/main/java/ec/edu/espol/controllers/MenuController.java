/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.proyectolinterfazabsamjo.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Familia
 */
public class MenuController implements Initializable {

    @FXML
    private MenuItem dueno;
    @FXML
    private MenuItem mascot;
    @FXML
    private MenuItem concurso;
    @FXML
    private MenuItem premio;
    @FXML
    private MenuItem Criterio;
    @FXML
    private MenuItem inscrip;
    @FXML
    private MenuItem miembro;
    @FXML
    private MenuItem evaluacion;
    @FXML
    private MenuItem close;
    @FXML
    private Pane panel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image("Imagenes\\concursoMascotas.jpg");
        ImageView wm = new ImageView(img);
        wm.setFitHeight(400);
        wm.setFitWidth(600);
        panel.getChildren().add(wm);
    }    

    @FXML
    private void llamarDueno(ActionEvent event) {
        try {
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("duenoGrafo.fxml"));
                    Parent root;
                    root = fxmlLoader.load();
                    App.scene.setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
    }

    @FXML
    private void llamarMascot(ActionEvent event) {
        try {
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("mascotaGrafo.fxml"));
                    Parent root = fxmlLoader.load();
                    App.scene.setRoot(root);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
    }

    @FXML
    private void llamarConcur(ActionEvent event) {
    }

    @FXML
    private void llamrpremio(ActionEvent event) {
    }

    @FXML
    private void llamrCriterio(ActionEvent event) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Criterio.fxml"));
                    Parent root;
                    root = fxmlLoader.load();
                    App.scene.setRoot(root);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
    }

    @FXML
    private void llamrInscrip(ActionEvent event) {
                
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Inscripcion.fxml"));
                    Parent root;
                    root = fxmlLoader.load();
                    App.scene.setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
    }
                
    @FXML
    private void llamarmien(ActionEvent event) {
        try {
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("miembroJuradoGrafo.fxml"));
                    Parent root;
                    root = fxmlLoader.load();
                    App.scene.setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
    }

    @FXML
    private void llamareva(ActionEvent event) {
        try {
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("evaluacionGrafo.fxml"));
                    Parent root;
                    root = fxmlLoader.load();
                    App.scene.setRoot(root);
            } catch (IOException ex) {
                    ex.printStackTrace();
                    }
    }

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }
    
}
