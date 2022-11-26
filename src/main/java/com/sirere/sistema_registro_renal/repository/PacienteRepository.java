package com.sirere.sistema_registro_renal.repository;

import com.sirere.sistema_registro_renal.entity.Paciente;
import com.sirere.sistema_registro_renal.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query(value = "SELECT * " +
            "FROM usuario u  JOIN paciente p ON u.id_usuario = p.id_usuario " +
            "JOIN filiacion f ON p.id_paciente = f.id_paciente " +
            "JOIN usuario_rol ur ON ur.id_usuario = u.id_usuario\n" +
            "JOIN rol r ON r.id = ur.id_rol " +
            "WHERE p.id_usuario = ?1"
            ,nativeQuery = true)
    public Optional<Paciente> findPacienteById_usario(Long id_usario);

//    Optional<Paciente> findByNombre(String nombre);
//    boolean existsByNombre(String nombre);
}
