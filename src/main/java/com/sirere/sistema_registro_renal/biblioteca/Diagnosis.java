package com.sirere.sistema_registro_renal.biblioteca;

import com.sirere.sistema_registro_renal.entity.*;
import com.sirere.sistema_registro_renal.enums.Enfermedad.EnfermedadEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

public class Diagnosis {

    private Calculate calculate = new Calculate();
    private Formato frt = new Formato();
    private int estado = 0;



    public Examen sacaAutoDiagnosticoExamen(Examen examen) {
        examen.getAutoDiagnostico().setDescripcion(sacaDescripcion(examen));
        examen.setEstado(estado);
        examen.getAutoDiagnostico().setFecha(examen.getFecha_examen().toLocalDate());
        examen.getAutoDiagnostico().setVisto(false);
        return examen;
    }

    //----- SACA DESCRIPCION---------
    private String sacaDescripcion(Examen examen){
        String str = "";
        //--- Calcular ---
        if(!examen.getCreatinina().isNaN()){
            examen.setAutoDiagnostico(calculate.evaluateCreatinina(examen.getFiliacion().getEdad(),examen.getCreatinina(), examen.getFiliacion().getSexo(), examen.getFiliacion().getRaza(), examen.getAutoDiagnostico()));
            if(examen.getAutoDiagnostico().getEstadio().equals(EnfermedadEnum.NORMAL)){
                str = str +"Creatinina: "+examen.getAutoDiagnostico().getEstadio().toString();
            }else if(examen.getAutoDiagnostico().getEstadio().equals(EnfermedadEnum.G3b_MODERADA_A_GRAVEMENTE_DISMINUIDO)){
                str= str + ("Creatinina: "+examen.getAutoDiagnostico().getEstadio().toString()+", resultado alterado");
                estado = 3;
            }else if(examen.getAutoDiagnostico().getEstadio().equals(EnfermedadEnum.G4_GRAVEMENTE_DISMINUIDO)){
                str= str + ("Creatinina: "+examen.getAutoDiagnostico().getEstadio().toString()+", resultado alterado");
                estado = 4;
            }else if(examen.getAutoDiagnostico().getEstadio().equals(EnfermedadEnum.G5_FALLO_RENAL)){
                str= str + ("Creatinina: "+examen.getAutoDiagnostico().getEstadio().toString()+", falla crítica");
                estado = 5;
            }else{
                str= str + ("Creatinina: "+examen.getAutoDiagnostico().getEstadio().toString());
            }
            str = str + "\n";
        }
        if(!examen.getPotasio().isNaN()) {
            EnfermedadEnum potasio = calculate.evaluatePotasio(examen.getPotasio());
            str= str + "Potasio: "+potasio.toString();
            if(!potasio.equals(EnfermedadEnum.NORMAL)){
                estado++;
                str= str + (", resultado alterado");
            }
            str = str + "\n";
        }
        if(!examen.getAlbumina().isNaN()){
            EnfermedadEnum albumina = calculate.evaluateAlbumina(examen.getAlbumina());
            str= str + "Albumina: "+albumina.toString();
            if(!albumina.equals(EnfermedadEnum.NORMAL)){
                estado++;
                str= str +", resultado alterado";
            }
            str = str + "\n";
        }
        if(!examen.getCloro().isNaN()){
            EnfermedadEnum cloro = calculate.evaluateCloro(examen.getCloro());
            str= str + "Cloro: "+cloro.toString();
            if(!cloro.equals(EnfermedadEnum.NORMAL)){
                estado++;
                str= str +", resultado alterado";
            }
            str = str + "\n";
        }
        if(!examen.getSodio().isNaN()){
            EnfermedadEnum sodio = calculate.evaluateSodio(examen.getSodio());
            str= str + "Sodio: "+sodio.toString();
            if(!sodio.equals(EnfermedadEnum.NORMAL)){
                estado++;
                str= str + ", resultado alterado";
            }
            str = str + "\n";
        }
        return str;
    }
    //-------------------------------


    private Integer sacaEstado(){

        return 1;
    }



}
