package com.sirere.sistema_registro_renal.repository;

import com.sirere.sistema_registro_renal.entity.Rol;
import com.sirere.sistema_registro_renal.enums.RolEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolEnum (RolEnum rolEnum);
    boolean existsByRolEnum(RolEnum rolEnum);

}
