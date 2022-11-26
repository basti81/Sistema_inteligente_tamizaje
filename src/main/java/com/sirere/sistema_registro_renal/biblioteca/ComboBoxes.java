package com.sirere.sistema_registro_renal.biblioteca;

import com.sirere.sistema_registro_renal.enums.EspecilidadEnum;
import com.sirere.sistema_registro_renal.enums.EnumPrevision;
import com.sirere.sistema_registro_renal.enums.EnumRol;


import java.util.*;

public class ComboBoxes {

    public List comuna(){
        List<String> lista = new ArrayList<String>();
        lista.clear();
        lista.add("San felipe");
        lista.add("Los andes");
        lista.add("Valparaiso");
        return lista;
    }
    public List prevision() {
        List<String> lista = new ArrayList<String>();
        lista.clear();
        lista.add(EnumPrevision.FONASA.name());
        lista.add(EnumPrevision.ISAPRE.name());
        lista.add(EnumPrevision.OTRO.name());
       return lista;
    }
    public List rol() {
        List<String> lista = new ArrayList<String>();
        lista.clear();
        lista.add(EnumRol.ADMINISTRADOR.name());
        lista.add(EnumRol.DOCTOR.name());
        lista.add(EnumRol.ENFERMERIA.name());
        lista.add(EnumRol.GES.name());
        lista.add(EnumRol.LABORATORISTA.name());
        lista.add(EnumRol.ESTADISTICAS.name());
        lista.add(EnumRol.TENS.name());
        return lista;
    }

    public List especialidad() {
        List<String> lista = new ArrayList<>();
        lista.clear();
//        lista.add(EspecilidadNombre.NINGUNA.name());
        lista.add(EspecilidadEnum.NEFROLOGIA.name());
        return lista;
    }

    public List etnia() {
        List<String> lista = new ArrayList<>();
        lista.clear();
        lista.add("Caucasico");
        lista.add("AfroAmericana");
        return lista;
    }

}
