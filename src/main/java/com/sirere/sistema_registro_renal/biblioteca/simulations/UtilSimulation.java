package com.sirere.sistema_registro_renal.biblioteca.simulations;

import com.sirere.sistema_registro_renal.biblioteca.Diagnosis;
import com.sirere.sistema_registro_renal.entity.*;
import com.sirere.sistema_registro_renal.services.ExamenService;
import com.sirere.sistema_registro_renal.services.FiliacionService;
import com.sirere.sistema_registro_renal.services.SignosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;


public class UtilSimulation {

    @Autowired
    private ExamenService examenService;
    @Autowired
    private FiliacionService filiacionService;
    @Autowired
    private SignosService signosService;
    private Diagnosis diagnosis = new Diagnosis();


    public Examen setInyecExamenes(Filiacion filiacion){
        Examen examen = new Examen();
        AutoDiagnostico autoDiagnostico = new AutoDiagnostico();
        examen.setFiliacion(filiacion);
        examen.setAutoDiagnostico(autoDiagnostico);
        //----------------------
        examen.setVisto(true);
        examen.setCreatinina(getNumberRandom(0.7,1.1));
        examen.setCloro(getNumberRandom(96,106));
        examen.setAlbumina(getNumberRandom(3.4,5.4));
        examen.setPotasio(getNumberRandom(3.5,5));
        examen.setSodio(getNumberRandom(135,145));
        examen.setFecha_examen(getDatetimeRandom());
        //-----------------------
        System.out.println("Entre a la clase examen ->"+diagnosis.sacaAutoDiagnosticoExamen(examen));
        return examen;
    }

    public SignoVital setInyecSigno(Filiacion filiacion){
        SignoVital sv =  new SignoVital();
        sv.setVisto(true);
        sv.setP_alta((int)getNumberRandom(100,160));
        sv.setP_baja((int)getNumberRandom(60,100));
        sv.setTemperatura(getNumberRandom(36.1,37.2));
        sv.setRespiracion((int)getNumberRandom(12,18));
        sv.setPulso((int)getNumberRandom(60,100));
        sv.setSaturacion(getNumberRandom(95,100));
        sv.setVisto(true);
        sv.setFecha_signo(getDatetimeRandom().toLocalDate());
        sv.setFiliacion(filiacion);
        return sv;
    }


    public Paciente setInyecPaciente(){
        Paciente paciente = new Paciente();
        String[] nameMen = {"Neil","Alex","Mateo","Tomás","Martín","José",""};
        String[] nameWomen = {"Julieta","María","Antonella","Valentina","Leonor","Josefa","Maite","Mia","Catalina"};
        paciente.getUsuario().setRut(getStringRandom(9));
//        paciente.getUsuario().setNombre();
        return new Paciente();
    }




    public double getNumberRandom(double m, double n){
        double rd =Math.random()*(n-m)+m;
        return Math.round(rd*100d)/100d;
    }
    public LocalDateTime getDatetimeRandom(){
        LocalDateTime localDateTime;
        long minDay = LocalDate.of(2000, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2022, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        localDateTime = randomDate.atStartOfDay();
        System.out.println("La date creada fue = "+localDateTime.toLocalDate());
        return localDateTime;
    }
    public  String getStringRandom(int max) {
        String theAlphaNumericS;
        StringBuilder builder;

        theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";

        //create the StringBuffer
        builder = new StringBuilder(5);

        for (int m = 0; m < max; m++) {

            // generate numeric
            int myindex
                    = (int)(theAlphaNumericS.length()
                    * Math.random());

            // add the characters
            builder.append(theAlphaNumericS
                    .charAt(myindex));
        }

        return builder.toString();
    }
}
