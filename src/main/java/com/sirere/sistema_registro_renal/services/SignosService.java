package com.sirere.sistema_registro_renal.services;

import com.sirere.sistema_registro_renal.biblioteca.Formato;
import com.sirere.sistema_registro_renal.entity.Examen;
import com.sirere.sistema_registro_renal.entity.SignoVital;
import com.sirere.sistema_registro_renal.repository.ExamenRepository;
import com.sirere.sistema_registro_renal.repository.SignosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SignosService {

    @Autowired
    private SignosRepository signosRepository;
    private Formato frt = new Formato();

    public List<SignoVital> list() {
        return signosRepository.findAll();
    }
    public List<SignoVital> listForGrafic(long id_filiacion){return signosRepository.reportForGrafic(id_filiacion);}
    public List<SignoVital> mySignos(long id_filiacion) {
        return signosRepository.findByfiliacion(id_filiacion);
    }
    public Optional<SignoVital> getOne(long id_signo) {
        return signosRepository.findById(id_signo);
    }
    public void save(SignoVital signo) {
        //signo.setFecha_signo(frt.today());
        //signo.setVisto(false);
        signosRepository.save(signo);
    }
    public void delete(Long id) {
        signosRepository.deleteById(id);
    }
    public boolean existsById(Long id) {
        return signosRepository.existsById(id);
    }
}
