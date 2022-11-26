package com.sirere.sistema_registro_renal.repository;

import com.sirere.sistema_registro_renal.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRespository extends JpaRepository<Consulta,Long> {
    @Query(value = "SELECT * FROM consulta c WHERE c.id_paciente = ?1 ORDER BY c.fecha_atencion DESC",nativeQuery = true)
    List<Consulta> consultaPacientes(@Param("id_paciente") Long id_paciente);

    @Query(value = "SELECT * FROM consulta c WHERE c.id_personal = ?1 and c.visto = ?2 ORDER BY c.fecha_atencion DESC",nativeQuery = true)
    List<Consulta> consultaPersonal(@Param("id_personal") Long id_personal, @Param("c.visto") boolean visto);

    @Query(value = "SELECT * FROM consulta c WHERE c.visto = ?1 ORDER BY c.fecha_atencion ASC",nativeQuery = true)
    List<Consulta> consultaGes(@Param("c.visto") boolean visto);
}
