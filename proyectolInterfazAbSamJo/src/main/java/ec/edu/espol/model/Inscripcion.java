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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
/**
 *
 * @author 
 */
public class Inscripcion {
    private int idInscripcion;
    private int idMascota;
    private Mascota mascota;
    private int idConcurso; 
    private Concurso concurso;
    private LocalDate fechaInscripcion;  
    private double costoInscripcion;
    private ArrayList<Evaluacion> evaluaciones;
    private double descuento;
    /*  idInscripcion, idConcurso, fecha, costo */

    public Inscripcion(int idInscripcion, int idMascota, Mascota mascota, int idConcurso, Concurso concurso, LocalDate fechaInscripcion, double costoInscripcion, ArrayList<Evaluacion> evaluaciones, double descuento) {
        this.idInscripcion = Util.nextID("inscripciones.txt");
        this.idMascota = idMascota;
        this.mascota = mascota;
        this.idConcurso = idConcurso;
        this.concurso = concurso;
        this.fechaInscripcion = fechaInscripcion;
        if(costoInscripcion>=0)
            this.costoInscripcion = costoInscripcion;
        else
            this.costoInscripcion = -costoInscripcion;
        this.evaluaciones = new ArrayList<> ();
        this.descuento = descuento;
        this.evaluaciones = new ArrayList<>();
    }

    public Inscripcion(int idInscripcion, int idMascota, int idConcurso, LocalDate fechaInscripcion, double costoInscripcion) {
        this.idInscripcion = idInscripcion;
        this.idMascota = idMascota;
        this.idConcurso = idConcurso;
        this.fechaInscripcion = fechaInscripcion;
        if(costoInscripcion>=0)
            this.costoInscripcion = costoInscripcion;
        else
            this.costoInscripcion = -costoInscripcion;
        this.evaluaciones = new ArrayList<>();
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
        Inscripcion new_ins = (Inscripcion) obj;
        return (this.idInscripcion == new_ins.idInscripcion);
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }
  
    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }

    public Concurso getConcurso() {
        return concurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
    
    public int getIdInscripcion() {
        return idInscripcion;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public double getCostoInscripcion() { 
        return costoInscripcion;
    }

    public void setCostoInscripcion(double costoInscripcion) {
        if(costoInscripcion>=0)
            this.costoInscripcion=costoInscripcion;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        if(this.costoInscripcion>25)
        this.descuento = this.costoInscripcion-this.costoInscripcion*0.1;
        else{
            this.descuento=0;
        }
    }
    
    //Falta el toString
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("La Inscripcion no. "+this.idInscripcion+", fue hecha para la participacion de la mascota "+this.mascota.getNombre()
                +" en el concurso "+this.concurso.getNombre()+" realizado en  "+this.fechaInscripcion+". \n Esta tiene un costo de "+this.costoInscripcion
                +" y tuvo las siguientes evaluaciones: ");
        for(Evaluacion ev : this.evaluaciones){
            sb.append("Hecha por "+ev.getMiembroJurado().getNombre()+ " sobre el criterio "+ev.getCriterio().getNombre()+ "con calificacion de "+ev.getCalificacion()+"\n.");
        }
        return sb.toString();
    }
            
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true )))
        {
            pw.println(this.idInscripcion+"|"+this.idMascota+"|"+ this.idConcurso+"|"+ this.fechaInscripcion+"|"+this.costoInscripcion);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
       public static ArrayList<Inscripcion> readFromFile(String nomfile){
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        try(Scanner sc= new Scanner(new File(nomfile))){
            while(sc.hasNextLine())
            {
                String linea=sc.nextLine();
                String [] tokens= linea.split("\\|");
                Inscripcion insc = new Inscripcion(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), LocalDate.parse(tokens[3]), Double.parseDouble(tokens[4]));
                inscripciones.add(insc);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
  
        }
        return inscripciones;
       }
    
    
    public static Inscripcion nextInscripcion(Scanner sc){
        sc.useDelimiter("\n");
        System.out.println("Ingrese el nombre de su Mascota: ");
        String nomMsc = sc.next();
        Mascota mc = Mascota.obtenerMascotaXNombre(nomMsc);
        if(mc == null){
            System.out.println("La Mascota cuyo nombre ingreso para su Inscripcion NO EXISTE ");
            System.out.println("Ingrese los datos de la Mascota para su Inscripcion");
            mc = Mascota.nextMascota(sc); 
            mc.saveFile("mascotas.txt");
        }
        int indMc = mc.getIdMascota();
        Concurso conco = Concurso.obtenerConcursoXNombre(sc);
        if(conco == null){
            System.out.println("El Concurso cuyo nombre ingreso, NO EXISTE");
            System.out.println("Ingrese los datos del concurso en el que esta su inscripcion");
            conco =  Concurso.nextConcurso(sc);
            conco.saveFile("concursos.txt");
        }
        int idCo = conco.getIdConcurso();
        System.out.println("Ingrese el costo de inscripcion: ");
        double costo = sc.nextDouble();  
        System.out.println("Ingrese la fecha de inscripcion en el formato: año-mes-dia");
        sc.useDelimiter(",");
        String fecha= sc.next();
        String [] arr_fecha= fecha.split("-");
        LocalDate fecha1 = LocalDate.of(Integer.parseInt(arr_fecha[0]), Integer.parseInt(arr_fecha[1]),Integer.parseInt(arr_fecha[2]));
        fecha1.format(DateTimeFormatter.ISO_LOCAL_DATE);
        Inscripcion new_inscrip = new Inscripcion(Util.nextID("inscripciones.txt"),indMc,idCo,fecha1,costo);
        return new_inscrip;
    }
    
    
    public static Inscripcion ObtenerObjetoInscripcion(int id){    
        ArrayList<Inscripcion> list_inscripciones=Inscripcion.readFromFile("inscripciones.txt");
        for (Inscripcion inscripcion:list_inscripciones){
            if(inscripcion.getIdInscripcion() == id){
                return inscripcion;   
            }
        }return null;
        
    }
   
    
}
