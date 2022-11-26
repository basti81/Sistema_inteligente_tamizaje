package com.sirere.sistema_registro_renal.repository;

import com.sirere.sistema_registro_renal.entity.AutoDiagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoDiagnosticoRepository extends JpaRepository<AutoDiagnostico, Long> {
    //Lista Auto Diagnostico
    @Query(value = " SELECT * FROM auto_diagnostico ad WHERE ad.visto = False ORDER BY ad.estado DESC", nativeQuery = true)
    public List<AutoDiagnostico> findAllAutoDiagnostico();

    @Query(value = "DELETE FROM auto_diagnostico ad WHERE ad.id_examen = 1?", nativeQuery = true)
    public List<AutoDiagnostico> deleteById(long id_examen);


}
