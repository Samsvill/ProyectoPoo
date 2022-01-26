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
public class Concurso {

    private int idConcurso;
    private String nombre;
    private LocalDate fecha;
    private LocalDate fechaInscripcion;
    private LocalDate fechaCierreInscripcion;
    private String tematica;
    private ArrayList<Inscripcion> inscripcion;
    private ArrayList<Premio> premios;
    private ArrayList<Criterio> criterio;
    /*
    LocalDate fecha1 = LocalDate.of(Integer.parseInt(arr_fecha[0]), Integer.parseInt(arr_fecha[1]),Integer.parseInt(arr_fecha[2]));
        fecha1.format(DateTimeFormatter.ISO_LOCAL_DATE);
    */
    
    public Concurso(int idConcurso, String nombre, LocalDate fecha, LocalDate fechaInscripcion, LocalDate fechaCierreInscripcion, String tematica) {
        this.idConcurso = idConcurso;
        this.nombre = nombre;
        this.fecha = fecha;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaCierreInscripcion = fechaCierreInscripcion;
        this.tematica = tematica;        
        this.inscripcion = new ArrayList<>();
        this.premios = new ArrayList<>();
        this.criterio = new ArrayList<>();
    }
    
    

    public Concurso(int idConcurso, String nombre, LocalDate fecha, LocalDate fechaInscripcion, LocalDate fechaCierreInscripcion, String tematica, ArrayList<Inscripcion> inscripcion, ArrayList<Premio> premios, ArrayList<Criterio> criterio) {
        this.idConcurso = idConcurso;
        this.nombre = nombre;
        this.fecha = fecha;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaCierreInscripcion = fechaCierreInscripcion;
        this.tematica = tematica;
        this.inscripcion = new ArrayList<> ();
        this.premios = new ArrayList<> ();
        this.criterio = new ArrayList<> ();
    }

    public int getIdConcurso() {
        return this.idConcurso;
    }

    public String getNombre() {
        return this.nombre;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public LocalDate getFechaInscripcion() {
        return this.fechaInscripcion;
    }

    public LocalDate getFechaCierreInscripcion() {
        return this.fechaCierreInscripcion;
    }

    public String getTematica() {
        return this.tematica;
    }

    public ArrayList<Inscripcion> getInscripcion() {
        return this.inscripcion;
    }

    public ArrayList<Premio> getPremios() {
        return this.premios;
    }

    public ArrayList<Criterio> getCriterio() {
        return this.criterio;
    }

    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public void setFechaCierreInscripcion(LocalDate fechaCierreInscripcion) {
        this.fechaCierreInscripcion = fechaCierreInscripcion;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public void setInscripcion(ArrayList<Inscripcion> inscripcion) {
        this.inscripcion = inscripcion;
    }

    public void setPremios(ArrayList<Premio> premios) {
        this.premios = premios;
    }

    public void setCriterio(ArrayList<Criterio> criterio) {
        this.criterio = criterio;
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
        Concurso co = (Concurso) obj;
        return this.idConcurso == co.idConcurso && Objects.equals(this.nombre, co.nombre);
    }

   

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Concurso No. " + this.idConcurso + "\n Nombre del concurso: " + this.nombre+"\n Fecha: " + this.fecha +"/n Fecha de Inscripción: " + this.fechaInscripcion + "/n Fecha de cierre de Inscripción: " + this.fechaCierreInscripcion+ "/n Temática: " + this.tematica);
        for(Inscripcion ins : this.inscripcion)
            sb.append("\n La Mascota "+ins.getMascota().getNombre()+"\n obtiene un Descuento de : "+ins.getDescuento()+"\n dado el Valor a pagar:"+ins.getCostoInscripcion());
        
        for(Premio premio : this.premios)
            sb.append("\n El premio: "+ premio.getDescripcion() + " está destinado para el "+ premio.getPuesto() +"lugar. \n");
        
        for(Criterio crit: this.criterio)
            sb.append("Criterio No.: " + crit.getIdCriterio() + "/n Descripción: " + crit.getDescripcion() + "\n Puntaje máximo: "+ crit.getPuntajeMax());
        
        return sb.toString();
    }
    //métodos
    
     public void saveFile(String nomFile){ 
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile),true))){
            pw.println(Util.nextID(nomFile)+ "|" + this.nombre+"|" + this.fecha +"|" + this.fechaInscripcion + "|" + this.fechaCierreInscripcion+ "|" + this.tematica);
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Concurso> readFromFile(String nomFile){
        ArrayList<Concurso> concurso = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");// revisar video
                /*int idConcurso, String nombre, LocalDate fecha, LocalDate fechaInscripcion, LocalDate fechaCierreInscripcion, String tematica*/
                Concurso con = new Concurso (Integer.parseInt(tokens[0]),tokens[1],LocalDate.parse(tokens[2]),LocalDate.parse(tokens[3]),
                        LocalDate.parse(tokens[4]),tokens[5]);
                
                concurso.add(con);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return concurso;
    }
    
    public static Concurso nextConcurso(Scanner sc){
        sc.useDelimiter("\n");
        int idc = Util.nextID("concursos.txt");
        System.out.println("Ingrese el nombre del concurso: ");
        String name = sc.next();
        String nombre = name.toUpperCase().charAt(0) + name.substring(1, name.length()).toLowerCase();
        System.out.println("Ingrese la fecha del concurso: ");
        String fechaText = sc.next();
        LocalDate f = LocalDate.parse(fechaText);
        System.out.println("Ingrese la fecha de incripción del concurso en este orden año-mes-día: ");
        String fechaTextoIns = sc.next();
        LocalDate fi = LocalDate.parse(fechaTextoIns);
        System.out.println("Ingrese la fecha de cierre de incripción del consurso: ");
        String fechaTextCierre = sc.next();
        LocalDate fc = LocalDate.parse(fechaTextCierre);
        System.out.println("Ingrese la temática del concurso: ");
        String tm = sc.next().toLowerCase();
        Concurso con = new Concurso(idc, nombre,f, fi, fc,tm);
        return con;
    }

    public static Concurso obtenerConcursoXNombre(String nombre){
        ArrayList<Concurso> concursos = Concurso.readFromFile("concursos.txt");
        for(Concurso c : concursos){
            if(Objects.equals(c.nombre,nombre))
                 return c;   
        }
        return null;
    }        
    public static Concurso obtenerConcursoXNombre(Scanner sc){
        ArrayList<Concurso> concursos = Concurso.readFromFile("concursos.txt");
        sc.useDelimiter("\n");
        System.out.println("Ingrese el nombre del Concurso: ");
        String nombre1= sc.next();
        for(Concurso c : concursos){
            if(Objects.equals(c.nombre,nombre1))
                 return c;   
        }
        return null;
    }        
        
}
