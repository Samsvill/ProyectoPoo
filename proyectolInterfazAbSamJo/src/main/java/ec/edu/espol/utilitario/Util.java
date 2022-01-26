/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.utilitario;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.Criterio;
import ec.edu.espol.model.Dueño;
import ec.edu.espol.model.Evaluacion;
import ec.edu.espol.model.Inscripcion;
import ec.edu.espol.model.Mascota;
import ec.edu.espol.model.MiembroJurado;
import ec.edu.espol.model.Premio;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author 
 */
public class Util {
    
    // el constructor se lo ha declarado privado
    // ya que esta clase solo va a contener comportamientos estáticos
    // por lo tanto, no se van a permitir crear instancia de la clase Util
    private Util(){}
    
    public static int nextID(String nomfile)
    {
        int id = 0;
        try(Scanner sc = new Scanner(new File(nomfile)))
        {
           while(sc.hasNextLine())
           {
               String linea = sc.nextLine();
               String[] tokens = linea.split("\\|");
               id = Integer.parseInt(tokens[0]);
           }
        }
        catch(Exception e)
        {
        }
        return id+1;
    }
    //Menu
    public static void menuOpciones(Scanner sc){ 
        //codigo
        System.out.println("-- Gracias por Ingresar al menú de opciones --\n");
        System.out.println("-- Tiene 8 posibles opciones sobre las que crear registros. -- \n-- Elija con cuidado un NUMERO de opcion, de los dados a continuacion:--\n");
        System.out.println("1: Dueño. \n2: Macota. \n3: Concurso. \n4: Premio. \n5: Criterio. \n6: Inscripcion. \n7: MiembroJurado. \n8: Evaluacion\n");
        int infNGTV = (int)Double.NEGATIVE_INFINITY;
        int infPSTV = (int)Double.POSITIVE_INFINITY;
        int opcion;
//        File mjArc = new File("miembroJurados.txt");
//        File insArc = new File("inscripciones.txt");
//        File critArc = new File("criterios.txt");
//        File evArc = new File("evaluaciones.txt");
//        File dueArc = new File("dueños.txt");
//        File preArc = new File("premios.txt");
//        File mascArc = new File("mascotas.txt");
//        File concArc = new File("concursos.txt");
        do{
            do{
                System.out.println("Escriba un numero de opcion\n");
                opcion = sc.nextInt();
            }while(opcion < 1 || opcion > 8);
            switch(opcion){
                case 1:
                    System.out.println("Por favor ingrese los datos del Dueño: \n");
                    Dueño due = Dueño.nextDueno(sc);
                    due.saveFile("dueños.txt");
                    System.out.println("\n");
                    break;
                    
                case 2:
                    System.out.println("Por favor ingrese los datos de la Mascota\n");
                    Mascota mascota1 = Mascota.nextMascota(sc);
                    Dueño dueno;
                    int idD = 0;
                    dueno = Dueño.obtenerDueñoXEmail(sc);  
                    if(dueno == null){ // aqui es cuando no existe ese dueño en el archivo o si no hay archivo dueño como tal
                        System.out.println("El dueño de su email ingresado no existe");
                        System.out.println("Por favor ingrese los datos del Dueño de preferencia para la mascota ingresada: \n");
                        dueno = Dueño.nextDueno(sc);
                        dueno.saveFile("dueños.txt");
                        System.out.println("\n");
                    }
                    idD = dueno.getId();
                    mascota1.setIdDueño(idD);
                    mascota1.saveFile("mascotas.txt");
                    break;
                    
                    
                
                case 3:
                    
                    System.out.println("Por favor ingrese los datos del Concurso: \n");
                    Concurso concurso1 = Concurso.nextConcurso(sc);
                    concurso1.saveFile("concursos.txt");
                    System.out.println("\n");
                    break;
                
                case 4:
                    System.out.println("Escriba la cantidad de premios a ingresar: \n");
                    int cantidad = sc.nextInt();
                    ArrayList<Premio> awrdsCntst = new ArrayList<>();
                    for(int i = 0 ; i<cantidad; i++){
                        Premio p = Premio.nextPremio(sc);
                        //añade cada premio en esta lista
                        awrdsCntst.add(p);
                    }
                    int idC = 0;
                    Concurso conc;
                    conc = Concurso.obtenerConcursoXNombre(sc); 
                    if(conc == null){
                        System.out.println("El concurso cuyo nombre ingreso no existe");
                        System.out.println("Ingrese los datos del Concurso cuyos premios ingreso");
                        conc = Concurso.nextConcurso(sc);
                        conc.saveFile("concursos.txt");                        
                    }
                    idC = conc.getIdConcurso();
                    for(Premio p: awrdsCntst){
                        p.setIdConcurso(idC);
                        p.saveFile("premios.txt");
                    }
                    break;

                case 5:
                    System.out.println("Escriba la cantidad de criterios a ingresar: \n");
                    int cant = sc.nextInt();
                    ArrayList<Criterio> lst_crit = new ArrayList<>();
                    for(int i = 0 ; i<cant; i++){
                        Criterio c = Criterio.nextCriterio(sc);
                        lst_crit.add(c);
                    }
                    int indC = 0;
                    Concurso con =  Concurso.obtenerConcursoXNombre(sc); 
                    if(con == null){
                        System.out.println("El concurso cuyo nombre ingreso no existe");
                        System.out.println("Ingrese los datos del Concurso cuyos criterios ingreso");
                        con = Concurso.nextConcurso(sc);
                        con.saveFile("concursos.txt");                        
                    }
                    indC = con.getIdConcurso();                    
                    for(Criterio crit: lst_crit){
                        crit.setIdConcurso(indC);
                        crit.saveFile("criterios.txt");
                    }
                    break;
                    
                case 6:
                    System.out.println("Ingrese los datos de su Inscripcion \n");
                    Inscripcion insF = Inscripcion.nextInscripcion(sc);
                    insF.saveFile("inscripciones.txt");
                    break;
               
                case 7:
                    System.out.println("Por favor ingrese los datos del Miembro del Jurado\n");
                    MiembroJurado mbjr = MiembroJurado.nextMiembroJurado(sc);
                    mbjr.saveFile("miembroJurados.txt");
                    System.out.println("\n");
                    break;
                
                case 8:
                    System.out.println("Por favor ingrese los datos de Evaluación\n");
                    sc.useDelimiter("\n");
                    MiembroJurado mj;
                    String correo;                    
                    System.out.println("Ingrese el email del jurado: ");
                    correo = sc.next();
                    mj = MiembroJurado.obtenerMiembroJuradoXEmail(correo);
                    if(mj == null){
                        System.out.println("Su miembro del jurado cuyo email ingreso NO EXISTE");
                        System.out.println("Por favor ingrese los datos del Miembro del Jurado que calificara en la Evaluacion: \n");
                        mj = MiembroJurado.nextMiembroJurado(sc);
                        mj.saveFile("miembroJurados.txt");
                        System.out.println("\n");
                    }
                    int idMiembroJurado = mj.getId();
                    
                    Inscripcion inscr;
                    System.out.println("Ingrese el id de la inscripcion: ");
                    int idInscripcion = sc.nextInt();
                    inscr = Inscripcion.ObtenerObjetoInscripcion(idInscripcion);
                    if(inscr == null){
                        System.out.println("La inscripcion cuyo id ingreso NO EXISTE");
                        sc.useDelimiter("\n");
                        System.out.println("Ingrese los datos de su Inscripcion \n");
                        inscr = Inscripcion.nextInscripcion(sc);
                        inscr.saveFile("inscripciones.txt");
                    }
                    idInscripcion = inscr.getIdInscripcion();
                    
                    System.out.println("Ingrese el id del criterio: ");
                    int idCriterio = sc.nextInt();
                    Criterio crt = Criterio.ObtenerObjetoCriterio(idCriterio);
                    if(crt == null){
                        System.out.println("El Criterio cuyo id ingreso, NO EXISTE");
                        System.out.println("Ingrese los datos de Criterio: ");
                        crt = Criterio.nextCriterio(sc);
                        int idco = 0;
                        Concurso cons =  Concurso.obtenerConcursoXNombre(sc); 
                        if(cons == null){
                            System.out.println("El concurso cuyo nombre ingreso no existe");
                            System.out.println("Ingrese los datos del Concurso cuyos criterios ingreso");
                            cons = Concurso.nextConcurso(sc);
                            cons.saveFile("concursos.txt");                        
                        }
                        idco = cons.getIdConcurso();                        
                        crt.setIdConcurso(idco);
                        crt.saveFile("criterios.txt");
                    }
                    idCriterio = crt.getIdCriterio();
                    
                    Evaluacion eva = Evaluacion.nextEvaluacion(sc);
                    eva.setIdMiembroJurado(idMiembroJurado);
                    eva.setIdCriterio(idCriterio);
                    eva.setIdInscripcion(idInscripcion);
                    eva.saveFile("evaluaciones.txt");
                    break;

            }
        }while(opcion > infNGTV && opcion < infPSTV);
    }
}
