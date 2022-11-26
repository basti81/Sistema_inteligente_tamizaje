package com.sirere.sistema_registro_renal.services;

import com.sirere.sistema_registro_renal.entity.Especialidad;
import com.sirere.sistema_registro_renal.enums.EspecilidadEnum;
import com.sirere.sistema_registro_renal.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public void save(Especialidad especilidad){
        especialidadRepository.save(especilidad);
    }

    public Optional<Especialidad> getByEspecialidadEnum (EspecilidadEnum especilidadEnum){
        return especialidadRepository.findByEspecialidadEnum(especilidadEnum);
    }
    public boolean existsByEspecialidadEnum(EspecilidadEnum especilidadEnum){
        return especialidadRepository.existsByEspecialidadEnum(especilidadEnum);
    }
}
