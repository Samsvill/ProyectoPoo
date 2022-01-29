/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.Inscripcion;
import ec.edu.espol.model.Mascota;
import ec.edu.espol.proyectolinterfazabsamjo.App;
import ec.edu.espol.utilitario.Util;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author Familia
 */
public class InscripcionController implements Initializable {


    @FXML
    private Button aceptar;
    @FXML
    private Button btnMenu;
    @FXML
    private TextField nomMasc;
    @FXML
    private TextField nomConcur;
    @FXML
    private TextField valorcan;
    @FXML
    private TextField fecha;
    @FXML
    private Pane panel;
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
    private void hacerLaIncripcion(ActionEvent event) {
        String fech= fecha.getText();
        String [] arr_fecha= fech.split("-");
        if(nomMasc.getText() == ""){
            Alert a = new Alert(AlertType.ERROR,"No ha ingresado el nombre de la Mascota");
            a.show();
//        }else{
//            String nombreM = nomMasc.getText();
//            Mascota mc = Mascota.obtenerMascotaXNombre(nombreM);
//            if(mc == null){
//                Alert a = new Alert(AlertType.ERROR,"La Mascota cuyo nombre ingreso para su Inscripcion NO EXISTE");
            }   
           // int indMc = mc.getIdMascota();
            //}
            if(nomConcur.getText() == "" ){
            Alert a = new Alert(AlertType.ERROR,"No ha ingresado el nombre del Concurso");
            a.show();
//            }else{
//                String nombreC = nomConcur.getText();
//                Concurso conco = Concurso.obtenerConcursoXNombre(nombreC);
//                if(conco == null){
//                    Alert a = new Alert(AlertType.ERROR,"El concurso cuyo nombre ingreso para la Inscripcion NO EXISTE");  
//                    }
//                int idCo = conco.getIdConcurso();
                }
        if(valorcan.getText() == ""){
            Alert a = new Alert(AlertType.ERROR,"No ha ingresado el valor a cancelar");
            a.show();
//            }else{
//                Double valor = Double.parseDouble(valorcan.getText());
             }
        if(fecha.getText() == ""){
            Alert a = new Alert(AlertType.ERROR,"No ha ingresado la fecha");
            a.show();
        }
        if(arr_fecha.length !=3){
                    Alert a = new Alert(AlertType.ERROR,"No ha ingresado la fecha correctamente");
                }
                LocalDate fecha1 = LocalDate.of(Integer.parseInt(arr_fecha[0]), Integer.parseInt(arr_fecha[1]),Integer.parseInt(arr_fecha[2]));
                fecha1.format(DateTimeFormatter.ISO_LOCAL_DATE);

//                Inscripcion new_inscrip = new Inscripcion(Util.nextID("inscripciones.txt"),indMc,idCo,fecha1,costo);

                
    }

    @FXML
    private void irAlmenu(ActionEvent event) {
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
