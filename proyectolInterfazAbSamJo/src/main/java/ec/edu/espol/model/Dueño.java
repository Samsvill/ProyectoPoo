/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.utilitario.Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Abraham
 */
public class Dueño extends Persona{
    private String direccion;
    private ArrayList<Mascota> mascotas;
    //ESTA LISTA NO SE LLENA, NI NINGUNA OTRA DE CARDINALIDADES
    //Constructores
    
    public Dueño(int id, String nombre, String apellidos, String telefono,
        String email, String direccion) {
        super(id, nombre, apellidos, telefono, email);
        this.direccion = direccion;
        this.mascotas = new ArrayList<>();
    }
    
    //Getters no Heredados

    public String getDireccion() {
        return direccion;
    }

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
    
    //Setters no Heredados 
    
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    
    //toString sobreescrito
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(/*"Id de Dueño: "+this.nombre+*/" Dueño nombre: "+this.nombre+", Apellidos: "
                +this.apellidos+", Direccion: "+this.direccion+" tiene: \n");
        for(Mascota mascota : this.mascotas){
            sb.append("El/La"+mascota.getTipo()+", de Nombre "+mascota.getNombre()+" y raza "+mascota.getRaza()+".\n");
        }        
        return sb.toString();
    }
    
    
    //NO ES NECESARIO SOBREESCRIBIR EL EQUALS, SOLO SE HEREDA
    
    //Comportamientos para Archivos (guardar objetos en ellos, leerlos y crear listas con cada uno) y nextObjeto

    @Override
    public void saveFile(String nomFile){
        try (FileWriter fw = new FileWriter(nomFile, true); BufferedWriter bw = new BufferedWriter(fw)){
            bw.write(Util.nextID(nomFile)+"|"+this.nombre+"|"+this.apellidos+"|"+
                    this.telefono+"|"+this.email+"|"+this.direccion);
            bw.newLine();
        }
        catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
    }
    
    //Crear lista de Dueños por medio de los que llenan un archivo.txt
    
    public static ArrayList<Dueño> readFromFile(String nomFile){
        ArrayList<Dueño> dos = new ArrayList<>();
        try(FileReader fr = new FileReader(nomFile); BufferedReader br = new BufferedReader(fr)){
            String line;
            while((line = br.readLine()) != null){
                String[] tokens = line.split("\\|");
                Dueño deo = new Dueño(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                dos.add(deo);
            }
            return dos;
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
        return dos;
    }
    
    public static Dueño nextDueno(Scanner sc){
        sc.useDelimiter("\n");
        int idd = Util.nextID("dueños.txt");
        System.out.println("Ingrese el nombre del Dueño de la/s mascota/s: ");
        String nom0 = sc.next();
        String nombre = nom0.toUpperCase().charAt(0) + nom0.substring(1, nom0.length()).toLowerCase();
        System.out.println("Ingrese los apellidos del Dueño de la/s mascota/s: ");
        String apell = sc.next();
        String apellido = apell.toUpperCase().charAt(0) + apell.substring(1, apell.length()).toLowerCase(); 
        System.out.println("Ingrese el numero de telefono del Dueño de la/s mascota/s: ");
        String phoNum = sc.next();
        System.out.println("Ingrese el email del Dueño de la/s mascota/s: ");
        String email = sc.next().toLowerCase();
        System.out.println("Ingrese la direccion del Dueño de la/s mascota/s: ");
        String direction = sc.next().toLowerCase();
        Dueño neoDu = new Dueño(idd, nombre, apellido, phoNum, email, direction);
        return neoDu;
    }
    //Obtener Dueño por email
    public static Dueño obtenerDueñoXEmail(Scanner sc){
        ArrayList<Dueño> dueños = Dueño.readFromFile("dueños.txt");
        sc.useDelimiter("\n");
        System.out.println("Ingrese el email del Dueño/a de la mascota: ");
        String email1 = sc.next();
        for(Dueño dF : dueños){
            if(Objects.equals(dF.email,email1))
                 return dF;   
        }
        return null;
    }
    
    //Obtener Dueño por nombre
    public static Dueño obtenerDueñoXNombre(Scanner sc){
        ArrayList<Dueño> dueños = Dueño.readFromFile("dueños.txt");
        sc.useDelimiter("\n");
        System.out.println("Ingrese el nombre del Dueño/a de la mascota: ");
        String nombre1 = sc.next();
        for(Dueño dF : dueños){
            if(Objects.equals(dF.nombre,nombre1))
                 return dF;   
        }
        return null;
    }    

}
