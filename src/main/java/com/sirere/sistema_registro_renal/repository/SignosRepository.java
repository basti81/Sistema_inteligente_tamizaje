package com.sirere.sistema_registro_renal.repository;

import com.sirere.sistema_registro_renal.entity.Examen;
import com.sirere.sistema_registro_renal.entity.SignoVital;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignosRepository extends JpaRepository<SignoVital, Long> {
    @Query(value = "SELECT * FROM signo_vital s WHERE s.id_filiacion = ?1", nativeQuery = true)
    List<SignoVital> findByfiliacion(@Param("id_filiacion") long id_filiacion);

    @Query(value = "SELECT * FROM signo_vital sv WHERE sv.id_filiacion = ?1 ORDER BY sv.fecha_signo ASC", nativeQuery = true)
    List<SignoVital> reportForGrafic(@Param("id_filiacion") long id_filiacion);
}
