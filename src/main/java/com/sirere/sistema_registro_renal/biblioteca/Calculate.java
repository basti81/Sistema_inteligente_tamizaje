package com.sirere.sistema_registro_renal.biblioteca;

import com.sirere.sistema_registro_renal.entity.AutoDiagnostico;
import com.sirere.sistema_registro_renal.entity.Farmaco;
import com.sirere.sistema_registro_renal.entity.Filiacion;
import com.sirere.sistema_registro_renal.enums.Enfermedad.EnfermedadEnum;
import com.sirere.sistema_registro_renal.enums.farmaco.FarmacoEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Calculate {
    private Formato formato = new Formato();

    //------------- CALCULADORA CREATININA-------------------
    public double sacaCockGault(int edad, double peso, double creatinina, String sexo) {
        double num = 1;
        if (sexo.equals("F")) {
            num = 0.85;
        }
        return ((140 - edad) * peso / 72 * creatinina) * num;
    }

    public double sacaMrdr4(int edad, double creatinina, String sexo, String raza) {
        double res = 0;
        res = (186 * Math.pow(creatinina, (-1.154)) * Math.pow(edad, (-0.203)));
        if (sexo.equals("F")) {  // SI ES MUJER
            res = res * (0.742);
        }
        if (raza.equals("ng")) { // SI ES AFROAMERICANA
            res = res * (1.21);
        }
        return Math.round(res * 100.0) / 100.0;
    }

    public AutoDiagnostico evaluateCreatinina(int edad, double creatinina, String sexo, String raza, AutoDiagnostico au) {
        au.setFg(sacaMrdr4(edad, creatinina, sexo, raza));
        if(!esAtrofia(sexo,creatinina).equals(EnfermedadEnum.NORMAL)){au.setEstadio(EnfermedadEnum.ATROFIA_MUSCULAR.name());}
        if (au.getFg() >= 90) {
            au.setEstadio(EnfermedadEnum.NORMAL.name());
        } else if (au.getFg() >= 60 && au.getFg() < 89) {
            au.setEstadio (EnfermedadEnum.G2_LIGERAMENTE_DISMINUIDO.name());
        } else if (au.getFg() >= 45 && au.getFg() < 60) {
            au.setEstadio (EnfermedadEnum.G3a_LIDERA_A_MODERADAMENTE_DISMINUIDO.name());
        } else if (au.getFg() >= 30 && au.getFg() < 45) {
            au.setEstadio (EnfermedadEnum.G3b_MODERADA_A_GRAVEMENTE_DISMINUIDO.name());
        } else if (au.getFg() >= 15 && au.getFg() < 30) {
            au.setEstadio (EnfermedadEnum.G4_GRAVEMENTE_DISMINUIDO.name());
        } else {
            au.setEstadio (EnfermedadEnum.G5_FALLO_RENAL.name());
        }
        return au;
    }

    public EnfermedadEnum esAtrofia(String sexo, double creatinina) {
        if (sexo.equals("M") && creatinina <= 0.74) {
            return EnfermedadEnum.ATROFIA_MUSCULAR;
        } else if (sexo.equals("F") && creatinina <= 0.59) {
            return EnfermedadEnum.ATROFIA_MUSCULAR;
        }
        return EnfermedadEnum.NORMAL;
    }
    //-------------------------------------------------------


    //---------- CALCULADORA POTASIO-------------------
    public EnfermedadEnum evaluatePotasio(double potasio) {
        if (potasio >= 3.5 && potasio <= 5) {
            return EnfermedadEnum.NORMAL;
        } else if (potasio < 3.5) {
            return EnfermedadEnum.HIPOPOTASEMIA;
        } else {
            return EnfermedadEnum.HIPERPOTASEMIA;
        }
    }
    //--------------------------------------------------


    //------------- CALCULADORA ALBUMINA----------------
    public EnfermedadEnum evaluateAlbumina(double albumina) {
        if (albumina >= 3.4 && albumina <= 5.4) {
            return EnfermedadEnum.NORMAL;
        } else if (albumina < 3.4) {
            return EnfermedadEnum.HIPOALBUMINEMIA;
        } else {
            return EnfermedadEnum.HIPERALBUMINEMIA;
        }
    }
    //--------------------------------------------------


    //------------- CALCULADORA CLORO----------------
    public EnfermedadEnum evaluateCloro(double cloro) {
        if (cloro >= 96 && cloro <= 106) {
            return EnfermedadEnum.NORMAL;
        } else if (cloro < 96) {
            return EnfermedadEnum.HIPOCLOREMIA;
        } else {
            return EnfermedadEnum.HIPERCLOREMIA;
        }
    }
    //-----------------------------------------------


    //------------- CALCULADORA SODIO----------------
    public EnfermedadEnum evaluateSodio(double sodio) {
        if (sodio >= 135 && sodio <= 145) {
            return EnfermedadEnum.NORMAL;
        } else if (sodio < 135) {
            return EnfermedadEnum.HIPONATREMIA;
        } else {
            return EnfermedadEnum.HIPERNATRIMIA;
        }
    }
    //-----------------------------------------------


    //------------- CALCULADORA IMC----------------
    public double sacaImc(Double peso, Double altura, String sexo) {
        double imc = peso / Math.pow(altura, 2);
        imc = Math.round(imc * 100.0) / 100.0;
        return imc;
    }

    public EnfermedadEnum evaluateImc(double imc) {
        if (imc <= 18.4) {
            return EnfermedadEnum.BAJO_PESO;
        } else if (imc > 18.4 && imc <= 24.9) {
            return EnfermedadEnum.NORMAL;
        } else if (imc > 24.9 && imc <= 29.9) {
            return EnfermedadEnum.SOBREPESO;
        } else if (imc > 29.9 && imc <= 34.9) {
            return EnfermedadEnum.OBESIDAD_CLAVE_1;
        } else if (imc > 34.9 && imc <= 39.9) {
            return EnfermedadEnum.OBESIDAD_CLAVE_2;
        } else {
            return EnfermedadEnum.OBESIDAD_CLAVE_3;
        }
    }
    //---------------------------------------------

    //------------- RECOMENDACION DE FARMACOS----------------
    public List<Farmaco> recomiendaFarmaco(EnfermedadEnum enfermedadEnum) {
        List<Farmaco> list = new ArrayList<>();
        //-------------------------------//
        Farmaco enapril = new Farmaco();
        enapril.setFarmacoEnum(FarmacoEnum.ENALAPRIL);
        enapril.setDosis("10 mg.");
        enapril.setFrecuencia("1C/12 hrs.VO");
        enapril.setCantidad(1);
        //-------------------------------//
        Farmaco paracetamol = new Farmaco();
        paracetamol.setFarmacoEnum(FarmacoEnum.PARACETAMOL);
        paracetamol.setDosis("500 mg.");
        paracetamol.setFrecuencia("1C/8 hrs.");
        paracetamol.setCantidad(1);
        //-------------------------------//
        Farmaco kcl = new Farmaco();
        kcl.setFarmacoEnum(FarmacoEnum.KCL);
        kcl.setDosis("600 Mg.");
        kcl.setFrecuencia("1C/8 hrs.VO");
        kcl.setCantidad(1);
        //-------------------------------//
        Farmaco aspirina = new Farmaco();
        kcl.setFarmacoEnum(FarmacoEnum.ASPIRINA);
        kcl.setDosis("500 Mg.");
        kcl.setFrecuencia("1C/8 hrs.VO");
        kcl.setCantidad(1);
        //-------------------------------//

        if (enfermedadEnum.equals(EnfermedadEnum.G3a_LIDERA_A_MODERADAMENTE_DISMINUIDO) ||
                enfermedadEnum.equals(EnfermedadEnum.G3b_MODERADA_A_GRAVEMENTE_DISMINUIDO) ||
                enfermedadEnum.equals(EnfermedadEnum.G4_GRAVEMENTE_DISMINUIDO) ||
                enfermedadEnum.equals(EnfermedadEnum.G5_FALLO_RENAL)
        ) {
            if (!list.equals(enapril)) {
                list.add(enapril);
            } else if (!list.equals(paracetamol)) {
                list.add(paracetamol);
            }
        } else if (enfermedadEnum.equals(EnfermedadEnum.HIPOPOTASEMIA)) {
            if (!list.equals(enapril)) {
                list.add(enapril);
            } else if (!list.equals(paracetamol)) {
                list.add(paracetamol);
            }
        } else if (enfermedadEnum.equals(EnfermedadEnum.HIPERPOTASEMIA)) {
            if (!list.equals(enapril)) {
                list.add(enapril);
            } else if (!list.equals(paracetamol)) {
                list.add(paracetamol);
            } else if (!list.equals(kcl)) {
                list.add(kcl);
            } else if (!list.equals(aspirina)) {
                list.add(aspirina);
            }
        }
        return list;
    }
    //-------------------------------------------------------

}
