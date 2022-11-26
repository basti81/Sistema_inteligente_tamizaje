package com.sirere.sistema_registro_renal.repository;

import com.sirere.sistema_registro_renal.entity.Antecedente;
import com.sirere.sistema_registro_renal.entity.Rol;
import com.sirere.sistema_registro_renal.enums.AntecendeteEnum;
import com.sirere.sistema_registro_renal.enums.RolEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AntecendenteRepository extends JpaRepository<Antecedente,Integer> {
//    Optional<Antecedente> findByAntecendenteNombre (AntecendeteEnum antecendeteEnum);
//    boolean existsByAntecedenteNombre(AntecendeteEnum antecendeteEnum);
}
