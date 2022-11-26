package com.sirere.sistema_registro_renal.test;

import com.sirere.sistema_registro_renal.biblioteca.Diagnosis;
import com.sirere.sistema_registro_renal.entity.AutoDiagnostico;
import com.sirere.sistema_registro_renal.entity.Examen;
import com.sirere.sistema_registro_renal.entity.Filiacion;
import com.sirere.sistema_registro_renal.services.ExamenService;
import com.sirere.sistema_registro_renal.services.FiliacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

//@Service
public class test_inyecc_exams implements CommandLineRunner {

    @Autowired
    private ExamenService examenService;
    @Autowired
    private FiliacionService filiacionService;
    private Diagnosis diagnosis = new Diagnosis();

    @Override
    public void run(String... args) throws Exception {
        Optional<Filiacion> optional = filiacionService.getOne(1l);
        if (optional.isPresent()){
            for( int i = 0; i<10; i++){
                Examen examen = new Examen();
                examen.setVisto(true);
                //----------------------
                examen.setCreatinina(getRandom(1,5));
                examen.setCloro(getRandom(1,5));
                examen.setAlbumina(getRandom(1,5));
                examen.setPotasio(getRandom(1,5));
                examen.setSodio(getRandom(1,5));
                examen.setFecha_examen(hora_rando());
                //-----------------------
                examen.setFiliacion(optional.get());
                //examenService.save(diagnosis.sacaAutoDiagnosticoExamen(optional.get(), examen));
                examenService.save(examen);
            }
        }
    }

    public LocalDateTime hora_rando(){
        LocalDateTime localDateTime;
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        localDateTime = randomDate.atStartOfDay();
        System.out.println(localDateTime);
        return localDateTime;
    }

    public double getRandom(int min, int max){
        return Math.random()*max + min;
    }

}
