/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.NumMenorQue0Exception;
import ec.edu.espol.model.Premio;
import ec.edu.espol.proyectolinterfazabsamjo.App;
import ec.edu.espol.utilitario.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author saman
 */
public class PremioController implements Initializable {

    @FXML
    private Pane panel;
    @FXML
    private TextField numPrem;
    @FXML
    private ScrollPane srcllpane;
    @FXML
    private VBox vbox;
    @FXML
    private Button btnMenu;
    @FXML
    private Button guardar;
    @FXML
    private Button acept;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void irMenu(ActionEvent event) {
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
    private void guardar(ActionEvent event) {
        
        try{
            ArrayList<Premio> premios = new ArrayList<>(); 
            int v1 = vbox.getChildren().size();
            for(int i =0; i<(v1-1);i+=6){
                 TextField f1=(TextField)vbox.getChildren().get(i+1);
                 TextField f2=(TextField)vbox.getChildren().get(i+3);
                 TextField f3=(TextField)vbox.getChildren().get(i+5);
                 //Premio(int id, int idConcurso, int puesto, String descripcion)
                 Premio p1 = new Premio(Util.nextID("premios.txt"),0,Integer.parseInt(f1.getText()),f2.getText());
                 premios.add(p1);
             }
            TextField nomC =(TextField)vbox.getChildren().get(v1-1);
            String noC = nomC.getText();
            Concurso c1 = Concurso.obtenerConcursoXNombre(noC);
            if(c1 == null){
                        Alert a = new Alert(Alert.AlertType.ERROR,"No existe el Concurso");
                        a.show();
                    } 
            int idc1 = c1.getIdConcurso();
            for(Premio p:premios){
                p.setIdConcurso(idc1);
                Label lbI = new Label(String.valueOf(p.getIdConcurso()));
                p.saveFile("criterios.txt");
            }

        }catch(IndexOutOfBoundsException e){
            Alert i =  new Alert(Alert.AlertType.ERROR, "No ha ingresado los datos ");
            i.show();
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }catch(NumberFormatException e){
            Alert i =  new Alert(Alert.AlertType.ERROR, "Debe ingresar el puntaje máximo.");
            i.show();
        }finally{
            vbox.getChildren().clear();
        }
        
        
    }

    @FXML
    private void correrScroll(ActionEvent event) {
    }
    
}
