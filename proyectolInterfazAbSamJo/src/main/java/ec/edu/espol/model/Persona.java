/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.utilitario.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Objects;

/**
 *
 * @author Abraham
 */
public abstract class Persona {
    protected int id;
    protected String nombre;
    protected String apellidos;
    protected String telefono;
    protected String email;
    
    //Constructores
    

    public Persona(int id, String nombre, String apellidos, String telefono, String email) throws DatoNoCompletadoException{
        this.id = id;
        if(nombre == null || Objects.equals(nombre, ""))
            throw new DatoNoCompletadoException("No ingresó el nombre");
        this.nombre = nombre;
        if(apellidos == null || Objects.equals(apellidos, ""))
            throw new DatoNoCompletadoException("No ingresó el/los apellido/s");
        this.apellidos = apellidos;
        if(telefono == null || Objects.equals(telefono, ""))
            throw new DatoNoCompletadoException("No ingresó el telefono");
        this.telefono = telefono;
        if(email == null || Objects.equals(email, ""))
            throw new DatoNoCompletadoException("No ingresó el email");
        this.email = email;
    }
    
    //Setters

    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    //Getters

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getEmail() {
        return email;
    }
    
    //toString
    
    @Override
    public String toString(){
        return "Id: "+this.id+"Nombre: "+this.nombre+", Apellidos: "+this.apellidos;
    }
    
    //equals (retorna true si el id, el nombre y los apellidos son iguales, ya que estos no cambian)
    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(this.getClass() != obj.getClass())
            return false;
        Persona pple = (Persona)obj;
        return this.id == pple.id && Objects.equals(this.nombre, pple.nombre) && Objects.equals(this.apellidos, pple.apellidos);
    }
    
    //saveFile abstracto
    public abstract void saveFile(String nomFile);
    
    
    
    
}
