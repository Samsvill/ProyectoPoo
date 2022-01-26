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
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Abraham
 */
public class Premio {
    private int id;
    private int idConcurso;
    private int puesto;
    private String descripcion;
    private Concurso concurso;
    
    //Constructor
    
    public Premio(int id, int idConcurso, int puesto, String descripcion, Concurso concurso){
        this.id = id;
        this.idConcurso = idConcurso;
        this.descripcion = descripcion;
        this.puesto = puesto;
        this.concurso = concurso;
    }

    public Premio(int id, int idConcurso, int puesto, String descripcion){
        this.id = id;
        this.idConcurso = idConcurso;
        this.puesto = puesto;
        this.descripcion = descripcion;
    }

    //Getters
    
    public int getId() {
        return id;
    }
    public int getIdConcurso() {
        return idConcurso;
    }    
    public int getPuesto() {
        return puesto;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public Concurso getConcurso() {
        return concurso;
    }

    //Setters
    
    public void setId(int id) {
        this.id = id;
    }    
    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }
    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }
    
    //toString
    
    @Override
    public String toString(){
        return "El premio "+this.descripcion+" de ID "+this.id+"  para el Concurso No. "+this.idConcurso+", esta destinado al lugar No. "+this.puesto;
    }

    //Guardar Objetos Premio en un archivo de estos
    
    public void saveFile(String nomFile){
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile),true))){
            pw.println(Util.nextID(nomFile)+"|"+this.idConcurso+"|"+this.puesto+"|"+
                this.descripcion);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
    
    public static ArrayList<Premio> readFromFile(String nomFile){
        ArrayList<Premio> prms = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Premio prm = new Premio(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3], null);
                prms.add(prm);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return prms;
    }
    
    public static Premio nextPremio(Scanner sc){
        int idp = Util.nextID("dueños.txt");
        int idC = 0; // este valor default se da a proposito, para luego hacer set en el por medio de la funcion menu
        System.out.println("Ingrese el puesto respectivo de su Premio");
        int plP = sc.nextInt();
        System.out.println("Ingrese la descripcion de su Premio: ");
        String descrP = sc.nextLine();
        Premio pr = new Premio(idp, idC, plP, descrP);
        return pr;
    }

}