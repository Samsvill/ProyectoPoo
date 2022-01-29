/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Criterio;
import ec.edu.espol.model.DatoNoCompletadoException;
import ec.edu.espol.model.Evaluacion;
import ec.edu.espol.model.Inscripcion;
import ec.edu.espol.model.MiembroJurado;
import ec.edu.espol.proyectolinterfazabsamjo.App;
import ec.edu.espol.utilitario.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Abraham
 */
public class EvaluacionGrafoController implements Initializable {

    @FXML
    private TextField emailJur;
    @FXML
    private TextField idInscr;
    @FXML
    private TextField calific;
    @FXML
    private TextField idCrit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emailJur.setText("");
        idInscr.setText("");
        calific.setText("");
        idCrit.setText("");
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
    private void guardarEvaluacion(MouseEvent event) {
        Inscripcion inscr = null;
        Criterio cr = null;
        try {
            String emlJur = emailJur.getText();
            double calf = Double.valueOf(calific.getText());
            MiembroJurado miembro = MiembroJurado.obtenerMiembroJuradoXEmail(emlJur.toLowerCase());
            if(miembro == null){
                Alert alt = new Alert(AlertType.ERROR, "MIEMBRO DE JURADO NO EXISTENTE. \n"
                        + "Digite el email del Miembro del Jurado de nuevo, \n"
                        + "ó regrese a la opción de \"Miembro Jurado\" y cree un Miembro de Jurado para este problema \n"
                        + "IMPORTANTE: Debe recordar el email de este, el cual deberá ser ingresado por usted en la ventana \"Miembro Jurado\"");
                alt.show();
                
            }
             
            int idInsc = Integer.valueOf(idInscr.getText());
            inscr = Inscripcion.ObtenerObjetoInscripcion(idInsc);
            if(inscr == null){
                Alert alt = new Alert(AlertType.ERROR, "INSCRIPCION NO EXISTENTE.\n"
                        + "Digite el id de la Inscripción de nuevo, \n "
                        + "ó regrese a la opción de \"Inscripcion\" y cree una Inscripción para este problema\n"
                        + "IMPORTANTE: Debe recordar el id de esta provisto en la Ventana, (el cual aparecerá LUEGO DE GUARDAR EL CRITERIO)\n"
                        + "PARA DIGITARLO AQUI en la casilla correspondiente"); //ESTO ES IMPOSIBLE PARA UN USUARIO, EL NO VERÁ EL ID
                alt.show();
            }
            
            
            int idCr = Integer.valueOf(idCrit.getText());
            cr = Criterio.ObtenerObjetoCriterio(idCr);
            if(cr == null){
                Alert alt = new Alert(AlertType.ERROR, "CRITERIO NO EXISTENTE.\n"
                        + "Digite el id del Criterio de nuevo, \n"
                        + "ó regrese a la opción de \"Criterio\" y cree un Criterio para este problema \n"
                        + "IMPORTANTE: Debe recordar el id de esta provisto en la Ventana, (el cual aparecerá LUEGO DE GUARDAR EL CRITERIO) \n"
                        + "PARA DIGITARLO AQUI en la casilla correspondiente"); //ESTO ES IMPOSIBLE PARA UN USUARIO, EL NO VERÁ EL ID
                alt.show();
            }
            
            int idJur = miembro.getId();
            idInsc = inscr.getIdInscripcion();
            idCr = cr.getIdCriterio();
            Evaluacion evaluacion = new Evaluacion(Util.nextID("evaluaciones.txt"), idJur, idInsc, calf, idCr);
            evaluacion.saveFile("evaluaciones.txt");
        }catch(NullPointerException npE){
            System.out.println(npE.getMessage());
        } catch (DatoNoCompletadoException ex) {
            System.out.println(ex.getMessage());
            
        }catch(NumberFormatException nfE){
            Alert a = new Alert(AlertType.ERROR, "No ingresó el id de Inscripcion, ó el id de Criterio, ó la calificación; \nó Ingresó texto en alguno de estos 3. \n"
                    + "Ingrese de nuevo");
            a.show();
        }
        emailJur.setText("");
        idInscr.setText("");
        calific.setText("");
        idCrit.setText("");
    }
    
}