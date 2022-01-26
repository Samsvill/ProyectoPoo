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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;


/**
 *
 * @author 
 */
public class Mascota {

    private int idMascota;
    private String nombre;
    private String raza;
    private LocalDate fechaNacimiento;
    private String tipo;
    private int idDueño;
    private Dueño dueño;
    private ArrayList<Inscripcion> inscripciones;
    
     public Mascota(int idMascota,int idDueño, String nombre, String tipo, String raza, LocalDate fechaNacimiento) {
        this.idMascota = idMascota;
        this.idDueño = idDueño;
        this.nombre = nombre;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo = tipo;
        this.inscripciones = new ArrayList<>();
    }

    public Mascota(int idMascota, String nombre, String tipo, String raza, LocalDate fechaNacimiento, int idDueño, Dueño dueño, ArrayList<Inscripcion> inscripciones) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo = tipo;
        this.idDueño = idDueño;
        this.dueño = dueño;
        this.inscripciones = new ArrayList<> ();
    }

    public int getIdMascota() {
        return this.idMascota;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getRaza() {
        return this.raza;
    }

    public LocalDate getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public String getTipo() {
        return this.tipo;
    }

    public int getIdDueño() {
        return this.idDueño;
    }

    public Dueño getDueño() {
        return this.dueño;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return this.inscripciones;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setIdDueño(int idDueño) {
        this.idDueño = idDueño;
    }

    public void setDueño(Dueño dueño) {
        this.dueño = dueño;
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Mascota msc = (Mascota) obj;
        return (this.idMascota == msc.idMascota && this.idDueño == msc.idDueño && Objects.equals(this.nombre, msc.nombre));
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Macota No. " + this.idMascota + "\n Nombre de la mascota: " + this.nombre+"\n Tipo de animal: " + this.tipo +"/n Raza de la mascota: " + this.raza + "/n Fecha de nacimiento: " + this.fechaNacimiento+ "Pertenece a /n"+ "Id de Dueño: "+this.dueño.getId()+"/n Nombre: "+this.dueño.getNombre()+", Apellidos: "+ this.dueño.getApellidos());
        for(Inscripcion inscripcion : this.inscripciones)
            sb.append("\n Costo de la inscripcion: "+inscripcion.getCostoInscripcion()+"/n Fecha de inscripción: "+inscripcion.getFechaInscripcion()+".\n");
        return sb.toString();
    }
    
    public void saveFile(String nomFile){ 
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))){
            pw.println(Util.nextID(nomFile)+"|"+this.idDueño+"|"+this.nombre+"|"+this.tipo+"|"+this.raza+"|"+this.fechaNacimiento);
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Mascota> readFromFile(String nomFile){
        ArrayList<Mascota> mascota = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                /*int idMascota,int idDueño, String nombre, String tipo, String raza, LocalDate fechaNacimiento*/
                Mascota mas = new Mascota(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]), tokens[2],tokens[3],tokens[4],LocalDate.parse(tokens[5]));
                mascota.add(mas);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return mascota;
    }
    
    public static Mascota nextMascota(Scanner sc){
        sc.useDelimiter("\n");
        int idm = Util.nextID("mascotas.txt");
        int idD = 0;
        System.out.println("Ingrese el nombre de la mascota: ");
        String name = sc.next().toLowerCase();
        String nombre = name.toUpperCase().charAt(0) + name.substring(1, name.length()).toLowerCase();
        System.out.println("Ingrese el tipo de mascota: ");
        String tip = sc.next().toLowerCase();
        System.out.println("Ingrese la raza de su mascota: ");
        String raz = sc.next().toLowerCase();
        System.out.println("Ingrese la fecha de nacimiento de su mascota en este orden año-mes-día: ");
        LocalDate fn = LocalDate.parse(sc.next()); 
        Mascota mas1 = new Mascota(idm,idD, name, tip, raz, fn);
        return mas1;
    }
    
    //Metodo para buscar el nombre de Mascota y Obtenerla
        public static Mascota obtenerMascotaXNombre(String nombre){
        ArrayList<Mascota> mascotas = Mascota.readFromFile("mascotas.txt");
        for(Mascota msc : mascotas){
            if(Objects.equals(msc.nombre, nombre))
                 return msc;   
        }
        return null;
    }    
        
}
