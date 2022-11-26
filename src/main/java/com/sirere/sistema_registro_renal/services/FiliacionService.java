package com.sirere.sistema_registro_renal.services;

import com.sirere.sistema_registro_renal.biblioteca.Formato;
import com.sirere.sistema_registro_renal.entity.Filiacion;
import com.sirere.sistema_registro_renal.repository.FiliacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FiliacionService {

    @Autowired
    private FiliacionRepository filiacionRepository;
    private Formato frt = new Formato();
    private Filiacion filiacion;

    public List<Filiacion> list(){
        return filiacionRepository.findAll();
    }

    public Optional<Filiacion> getOne(Long id){
        System.out.println("Entre a filiacion");
        return filiacionRepository.findById(id);
    }

//    public Optional<Filiacion> getByNombre(String nombre){
//        return filiacionRepository.findByNombre(nombre);
//    }


    public void save(Filiacion filiacion){
        filiacion.setFecha_filiacion(frt.today());
        filiacionRepository.save(filiacion);
    }

    public void delete(Long id){
        filiacionRepository.deleteById(id);
    }
    public boolean existsById(Long id){
        return filiacionRepository.existsById(id);
    }

//    public boolean existsByNombre(String nombre){
//        return filiacionRepository.existsByNombre(nombre);
//    }


}
