/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

/**
 *
 * @author Abraham
 */
public class DatoNoCompletadoException extends Exception{
    public DatoNoCompletadoException(String mensajeError){
        super(mensajeError);
    }
}
