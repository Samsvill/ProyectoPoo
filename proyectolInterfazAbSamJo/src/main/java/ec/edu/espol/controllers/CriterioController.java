/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.Criterio;
import ec.edu.espol.model.NumMenorQue0Exception;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Familia
 */
public class CriterioController implements Initializable {

    @FXML
    private Pane panel;
    @FXML
    private TextField numCrit;
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
    @FXML
    private VBox vbIndices;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vbIndices.getChildren().clear();
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
        vbIndices.getChildren().clear();
        try{
            ArrayList<Criterio> criterios = new ArrayList<>(); 
            int v1 = vbox.getChildren().size();
            for(int i =0; i<(v1-1);i+=6){
                 TextField f1=(TextField)vbox.getChildren().get(i+1);
                 TextField f2=(TextField)vbox.getChildren().get(i+3);
                 TextField f3=(TextField)vbox.getChildren().get(i+5);
                 Criterio c1 = new Criterio(Util.nextID("criterios.txt"),0,f1.getText(),f2.getText(),Double.parseDouble(f3.getText()));
                 criterios.add(c1);
             }
            TextField nomC =(TextField)vbox.getChildren().get(v1-1);
            String noC = nomC.getText();
            Concurso c1 = Concurso.obtenerConcursoXNombre(noC);
            if(c1 == null){
                        Alert a = new Alert(Alert.AlertType.ERROR,"No existe el Concurso");
                        a.show();
                    } 
            int idc1 = c1.getIdConcurso();
            for(Criterio c:criterios){
                c.setIdConcurso(idc1);
                Label lbI = new Label(String.valueOf(c.getIdCriterio()));
                vbIndices.getChildren().add(lbI);
                c.saveFile("criterios.txt");
            }

        }catch(IndexOutOfBoundsException e){
            Alert i =  new Alert(AlertType.ERROR, "No ha ingresado los datos ");
            i.show();
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }catch(NumberFormatException e){
            Alert i =  new Alert(AlertType.ERROR, "Debe ingresar el puntaje máximo.");
            i.show();
        }catch(NumMenorQue0Exception nmq0e){
            Alert i =  new Alert(AlertType.ERROR, nmq0e.getMessage());
            i.show();
        }finally{
            numCrit.clear();
            vbox.getChildren().clear();
        }
        
        
    }

    @FXML
    private void correrScroll(ActionEvent event) {
        vbox.getChildren().clear();
        try
        {
            if(Integer.parseInt(numCrit.getText()) > 0){
                int crites = Integer.parseInt(numCrit.getText());
                for(int i =0; i<crites;i++){
                    Label lb1 = new Label("Nombre del criterio #"+(i+1));
                    TextField nom = new TextField("");
                    Label lb2 = new Label("Descripción del criterio #"+(i+1));
                    TextField descr = new TextField(""); 
                    Label lb3 = new Label("Puntaje Máximo del criterio #"+(i+1));
                    TextField punt = new TextField(""); // Aqui se agregan la cantidad de textsfields de acuerdo a la cantidad de criterios
                    vbox.getChildren().add(lb1);
                    vbox.getChildren().add(nom);
                    vbox.getChildren().add(lb2);
                    vbox.getChildren().add(descr);
                    vbox.getChildren().add(lb3);
                    vbox.getChildren().add(punt);
                }
                Label lb4 = new Label("Nombre del Concurso");
                TextField nomconcurso = new TextField("");  //Aqui se pide el nombre del concurso al que pertenece el textfield
                vbox.getChildren().add(lb4);
                vbox.getChildren().add(nomconcurso);
            }else{
                vbox.getChildren().clear();
            }
        }catch(NumberFormatException e){
            Alert w = new Alert(AlertType.ERROR, "No ha ingresado correctamente la cantidad de criterios");
            w.show();
        }
    }
    
}