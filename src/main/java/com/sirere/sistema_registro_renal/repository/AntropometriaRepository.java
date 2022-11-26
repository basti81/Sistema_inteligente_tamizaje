package com.sirere.sistema_registro_renal.repository;

import com.sirere.sistema_registro_renal.entity.Antropometria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AntropometriaRepository extends JpaRepository<Antropometria,Long> {
    @Query(value = "SELECT * FROM antropometria a WHERE a.id_filiacion= ?1 ORDER BY a.id_antropometria DESC", nativeQuery = true)
    List<Antropometria> findAntropometriaByfiliacion(Long id_filiacion);
}
