/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.DatoNoCompletadoException;
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
public class ConcursoGrafoController implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField fecha;
    @FXML
    private TextField fechain;
    @FXML
    private TextField fechafin;
    @FXML
    private TextField tematica;
    @FXML
    private BorderPane panel;
    @FXML
    private Button btnMenu;

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
    private void guardarConcurso(MouseEvent event) {
        try {
            String name = nombre.getText();
            LocalDate date = LocalDate.parse(fecha.getText());
            LocalDate datein = LocalDate.parse(fechain.getText());
            LocalDate dateout = LocalDate.parse(fechafin.getText());
            String theme = tematica.getText();

            //int idConcurso, String nombre, LocalDate fecha, LocalDate fechaInscripcion, LocalDate fechaCierreInscripcion, String tematica
            Concurso contest = new Concurso(Util.nextID("concursos.txt"),name,date,datein,dateout,theme);
            contest.saveFile("concursos.txt");
        } catch (DatoNoCompletadoException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Uno de sus campos no está lleno, revise e inténtelo de nuevo.");
            a.show();
        } catch (NullPointerException e) {
            e.getMessage();
        }

        nombre.setText("");
        fecha.setText("");
        fechain.setText("");
        fechafin.setText("");
        tematica.setText("");

    }

    @FXML
    private void irAlMen(ActionEvent event) {
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
