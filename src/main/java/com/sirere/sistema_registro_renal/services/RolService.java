package com.sirere.sistema_registro_renal.services;

import com.sirere.sistema_registro_renal.entity.Rol;
import com.sirere.sistema_registro_renal.enums.RolEnum;
import com.sirere.sistema_registro_renal.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public void save (Rol rol){
        rolRepository.save(rol);
    }

    public Optional<Rol> getByRolEnum(RolEnum rolEnum){
        return rolRepository.findByRolEnum(rolEnum);
    }

    public boolean existsByRolEnum(RolEnum rolEnum){
        return rolRepository.existsByRolEnum(rolEnum);
    }

}
