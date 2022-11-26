package com.sirere.sistema_registro_renal.repository;

import com.sirere.sistema_registro_renal.entity.Filiacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FiliacionRepository extends JpaRepository<Filiacion,Long> {
    @Query(value = "SELECT * FROM filiacion f WHERE f.id_filiacion = ?1", nativeQuery = true)
    Optional<Filiacion> getOne(@Param("id_filiacion") long id_filiacion);
}
