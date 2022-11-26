package com.sirere.sistema_registro_renal.services;

import com.sirere.sistema_registro_renal.biblioteca.Formato;
import com.sirere.sistema_registro_renal.entity.Examen;
import com.sirere.sistema_registro_renal.entity.Usuario;
import com.sirere.sistema_registro_renal.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ExamenService {

    @Autowired
    private ExamenRepository examenRepository;
    private Formato frt = new Formato();

    public List<Examen> list() {
        return examenRepository.findAll();
    }
    public List<Examen> getCriticList(int estado, boolean e_visto, String p_estado) {
        return examenRepository.getCriticList(estado,e_visto,p_estado);}

    public List<Examen> getCriticListAlert(int estado, boolean e_visto, String p_estado) {
        return examenRepository.getCriticListAlert(estado,e_visto,p_estado);}


    public List<Examen> myExams(Long id_filiacion) {
        return examenRepository.findExamnByFiliacion(id_filiacion);
    }
    public List<Examen> listForGrafic(long id_filiacion){return examenRepository.reportForGrafic(id_filiacion);}
    public Optional<Examen> getOne(Long id) {
        return examenRepository.findById(id);
    }
    public void save(Examen examen) {
        examenRepository.save(examen);
    }
    public void delete(Long id) {
        examenRepository.deleteById(id);
    }
    public boolean existsById(Long id) {
        return examenRepository.existsById(id);
    }

//    public List<Examen> criticList(int estado,boolean visto){
//        Set<Examen> hs = new HashSet<>();
//        for (Examen examen: examenRepository.listStateExamn(estado,visto) ) {
//            hs.add(examen);
//        }
//        return new ArrayList<Examen>(hs);
//    }

}
