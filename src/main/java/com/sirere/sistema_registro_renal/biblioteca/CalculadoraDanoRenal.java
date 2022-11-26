package com.sirere.sistema_registro_renal.biblioteca;


import com.sirere.sistema_registro_renal.entity.Paciente;

public class CalculadoraDanoRenal {

    public void autoDiagnostico(Paciente paciente){

    }

    private double mrdr4(int edad, double creatinina, String sexo, String raza){
        double res = 0;
        res = (186 * Math.pow(creatinina,(-1.154)) * Math.pow(edad, (-0.203)));
        if(sexo.equals("F")){  // SI ES MUJER
            res = res * (0.742);
        }
        if(raza.equals("Afroamericana")){ // SI ES AFROAMERICANA
            res = res * (1.21);
        }
        return res;
    }

    private double mrdr6(){
       double res = 0;
        return res;
    }

    private double cro_Gault(int edad, double peso, double creatinina, String sexo){
        double res = 0;
        res = ((140-edad) * peso )/ (72*creatinina);
        if(sexo.equals("F")){
            res = res * (0.85);
        }
        return res;
    }

    private int estadio(double fg){
        int tipoEstadio = 0;
        if (fg >= 90) {
           tipoEstadio = 1;
        }else if (fg>60 && fg<89){
           tipoEstadio = 2;
        }else if (fg>30 && fg<59){
           tipoEstadio = 3;
        }else if (fg>15 && fg<29){
            tipoEstadio = 4;
        }else if (fg<=15){
            tipoEstadio = 5;
        }
       return tipoEstadio;
    }

    private String descripcionEstadio(int tipoEstadio){
        String str = "";
        switch (tipoEstadio){
            case 1:
                str = "Normal";
                break;
            case 2:
                str = "Leve";
                break;
            case 3:
                str= "Moderado";
                break;
            case 4:
                str= "Severa";
                break;
            case 5:
                str= "Fallo Renal";
                break;
        }
        return str;
    }
}
