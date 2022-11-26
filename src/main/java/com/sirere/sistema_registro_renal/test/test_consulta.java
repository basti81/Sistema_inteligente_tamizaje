package com.sirere.sistema_registro_renal.test;

import com.sirere.sistema_registro_renal.entity.Consulta;
import com.sirere.sistema_registro_renal.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class test_consulta implements CommandLineRunner {
    @Autowired
    private ConsultaService consultaService;

    @Override
    public void run(String... args) throws Exception {
        LocalDateTime ldt = LocalDateTime.now();
        String dateToStr = DateFormat.getDateTimeInstance().format(ldt);
    }
}
