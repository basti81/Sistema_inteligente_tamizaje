package com.sirere.sistema_registro_renal.services;

import com.sirere.sistema_registro_renal.biblioteca.Calculate;
import com.sirere.sistema_registro_renal.biblioteca.Formato;
import com.sirere.sistema_registro_renal.entity.Antropometria;
import com.sirere.sistema_registro_renal.entity.Examen;
import com.sirere.sistema_registro_renal.repository.AntropometriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AntropometriaService {

    @Autowired
    private AntropometriaRepository antropometriaRepository;
    private Formato frt = new Formato();
    private Calculate calculate = new Calculate();

    public List<Antropometria> list() {
        return antropometriaRepository.findAll();
    }

    public List<Antropometria> myAntropometrys(Long id_filiacion) {
        return antropometriaRepository.findAntropometriaByfiliacion(id_filiacion);
    }

    public Optional<Antropometria> getOne(Long id) {
        return antropometriaRepository.findById(id);
    }

    public void save(Antropometria antropometria) {
        Double imc  = calculate.sacaImc(antropometria.getPeso(), antropometria.getAltura(),antropometria.getFiliacion().getSexo());
        antropometria.setImc(imc);
        antropometria.setFecha_antropometria(frt.today());
        antropometriaRepository.save(antropometria);
    }

    public void delete(Long id) {
        antropometriaRepository.deleteById(id);
    }
    public boolean existsById(Long id) {
        return antropometriaRepository.existsById(id);
    }

}
