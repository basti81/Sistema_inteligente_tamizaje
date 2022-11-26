package com.sirere.sistema_registro_renal.biblioteca;


import com.sirere.sistema_registro_renal.enums.EnumPrevision;

public class GestionPrevision {

    public String sacaPrevision(int prevision){
        String str = "";
        if(prevision == 0){
            str = EnumPrevision.FONASA.name();
        }else if (prevision == 1){
            str = EnumPrevision.ISAPRE.name();
        }else if(prevision == 2){
            str = EnumPrevision.OTRO.name();
        }
        return str;
    }
}
