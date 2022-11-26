package com.sirere.sistema_registro_renal.biblioteca;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Formato {
    private   ZoneId z = ZoneId.of("Chile/Continental");
    private   LocalDate date = LocalDate.now(z);
    public LocalDate today(){
        return date;
    }

}
