package com.sirere.sistema_registro_renal.repository;

import com.sirere.sistema_registro_renal.entity.Examen;
import com.sirere.sistema_registro_renal.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {
    @Query(value = "SELECT * FROM examen e WHERE e.id_filiacion = ?1 ORDER BY e.fecha_examen DESC", nativeQuery = true)
    List<Examen> findExamnByFiliacion(Long id_filiacion);


    @Query(value = "SELECT * FROM examen e " +
            "INNER JOIN filiacion f ON e.id_filiacion = f.id_filiacion " +
            "INNER JOIN paciente p ON f.id_paciente = p.id_paciente " +
            "INNER JOIN usuario u ON p.id_usuario = u.id_usuario " +
            "WHERE e.estado > ?1 AND e.visto = ?2 AND p.estado = ?3 " +
            "ORDER BY e.fecha_examen DESC ;" , nativeQuery = true)
    List<Examen> getCriticList(@Param("e.estado") int estado, @Param("e.visto") boolean e_visto, @Param("p.estado") String p_estado);

    @Query(value = "SELECT * FROM examen e " +
            "INNER JOIN filiacion f ON e.id_filiacion = f.id_filiacion " +
            "INNER JOIN paciente p ON f.id_paciente = p.id_paciente " +
            "INNER JOIN usuario u ON p.id_usuario = u.id_usuario " +
            "WHERE e.estado > ?1 AND e.visto = ?2 AND p.estado = ?3 " +
            "ORDER BY e.fecha_examen DESC " +
            "LIMIT 3;" , nativeQuery = true)
    List<Examen> getCriticListAlert(@Param("e.estado") int estado, @Param("e.visto") boolean e_visto, @Param("p.estado") String p_estado);


    @Query(value = "SELECT * FROM examen e WHERE e.id_filiacion = ?1 ORDER BY e.fecha_examen ASC", nativeQuery = true)
    List<Examen> reportForGrafic(Long id_filiacion);

}
