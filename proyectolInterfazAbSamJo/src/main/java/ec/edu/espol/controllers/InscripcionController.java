/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.Criterio;
import ec.edu.espol.model.DatoNoCompletadoException;
import ec.edu.espol.model.Evaluacion;
import ec.edu.espol.model.Inscripcion;
import ec.edu.espol.model.Mascota;
import ec.edu.espol.model.MiembroJurado;
import ec.edu.espol.model.NumMenorQue0Exception;
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
import javafx.scene.control.Label;
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
    @FXML
    private Label idINS;
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
        try
        {
        
            String fech= fecha.getText();
            String [] arr_fecha= fech.split("-");
            Mascota m = Mascota.obtenerMascotaXNombre(nomMasc.getText());
            if(m == null){
                    Alert a = new Alert(AlertType.ERROR,"No existe el nombre de la Mascota");
                    a.show();
                } 
            Concurso c  = Concurso.obtenerConcursoXNombre(nomConcur.getText());
            if(c == null){
                    Alert a = new Alert(AlertType.ERROR,"No existe el Concurso ingresado");
                    a.show();
                }
            if(arr_fecha.length !=3){
                    Alert a = new Alert(AlertType.ERROR,"No ha ingresado la fecha correctamente");
                    a.show();
                    }
            LocalDate fecha1 = LocalDate.of(Integer.parseInt(arr_fecha[0]), Integer.parseInt(arr_fecha[1]),Integer.parseInt(arr_fecha[2]));
            fecha1.format(DateTimeFormatter.ISO_LOCAL_DATE);
            int indMc = m.getIdMascota();
            int idCo = c.getIdConcurso();
            Double costo = Double.parseDouble(valorcan.getText());
            Inscripcion new_inscrip = new Inscripcion(Util.nextID("inscripciones.txt"),indMc,idCo,fecha1,costo);
            new_inscrip.saveFile("inscripciones.txt");
            idINS.setText(String.valueOf(new_inscrip.getIdInscripcion()));
            }catch(NullPointerException npE){
                System.out.println(npE.getMessage());
            }catch(NumberFormatException nfE){
                Alert a = new Alert(AlertType.ERROR, "No Ingresó el valor a pagar,\nó Ingresó texto en este campo\n;ó Ingresó  la fecha incorrectamente\n"
                        + "Ingrese de nuevo");
                a.show();
            } catch(ArrayIndexOutOfBoundsException e){
                //System.out.println(e.getMessage());
                Alert a = new Alert(AlertType.ERROR, "Ingresó la fecha incorrectamente.");
                a.show();
            }catch(NumMenorQue0Exception nmq0e){
                Alert a = new Alert(AlertType.ERROR, nmq0e.getMessage());
            }
        
        nomMasc.setText("");
        nomConcur.setText("");
        valorcan.setText("");
        fecha.setText("");
        
//        
//                
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
