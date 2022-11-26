package com.sirere.sistema_registro_renal.repository;

import com.sirere.sistema_registro_renal.entity.AutoDiagnostico;
import com.sirere.sistema_registro_renal.entity.Examen;
import com.sirere.sistema_registro_renal.entity.Personal;
import com.sirere.sistema_registro_renal.entity.Usuario;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
//@NoRepositoryBean
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

//    //LISTA AUTODIAGNOSTICO
//    @Query(value = " SELECT *  FROM usuario u JOIN paciente p ON u.id_usuario = p.id_usuario \n" +
//            "JOIN filiacion f ON p.id_paciente = f.id_paciente \n" +
//            "JOIN examen e ON f.id_filiacion = e.id_filiacion \n" +
//            "JOIN auto_diagnostico ad ON e.id_examen = ad.id_examen\n" +
//            "ORDER BY ad.estado DESC", nativeQuery = true)
//    public List<Usuario> findAllAutoDiagnostico();



    //LISTA DE PACIENTES
    @Query(value = "SELECT * " +
            "FROM usuario u  JOIN paciente p ON u.id_usuario = p.id_usuario " +
            "JOIN filiacion f ON p.id_paciente = f.id_paciente " +
            "JOIN usu_rol ur ON ur.id_usuario = u.id_usuario\n" +
            "JOIN rol r ON r.id = ur.id_rol" +
            " WHERE r.id = 2"
            , nativeQuery = true)
    public List<Usuario> findAllPaciente();

    //LISTA DE PERSONALES
    @Query(value = "SELECT * " +
            "FROM usuario u  JOIN personal p ON u.id_usuario = p.id_usuario " +
            "JOIN usu_rol ur ON ur.id_usuario = u.id_usuario\n" +
            "JOIN rol r ON r.id = ur.id_rol" +
            " WHERE r.id <> 2"
            , nativeQuery = true)
    public List<Usuario> findAllPersonal();

    //LISTA DE DOCTORES
    @Query(value = "SELECT * " +
            "FROM usuario u  JOIN personal p ON u.id_usuario = p.id_usuario " +
            "JOIN usu_rol ur ON ur.id_usuario = u.id_usuario\n" +
            "JOIN contacto c ON u.id_usuario = c.id_usuario " +
            "JOIN rol r ON r.id = ur.id_rol" +
            " WHERE r.id = 3"
            , nativeQuery = true)
    public List<Usuario> findAllDoctor();

    //USUARIO MEDIANTE ID DE FILIACION
    @Query(value = "SELECT * " +
            "FROM usuario u JOIN paciente p " +
            "ON u.id_usuario = p.id_usuario " +
            "JOIN filiacion f ON p.id_paciente = f.id_paciente " +
            "JOIN contacto c ON u.id_usuario = c.id_usuario " +
            "WHERE id_filiacion = ?1"
            , nativeQuery = true)
    public Optional<Usuario> findUsuarioByFiliacion(@Param("id_filiacion") long id_filiacion);


    //OBJETO PACIENTE
    @Query(value = "SELECT * " +
            "FROM usuario u  JOIN paciente p ON u.id_usuario = p.id_usuario " +
            "JOIN filiacion f ON p.id_paciente = f.id_paciente " +
            "JOIN contacto c ON u.id_usuario = c.id_usuario " +
            "WHERE p.id_usuario = ?1"
            , nativeQuery = true)
    public Optional<Usuario> findPacienteById_usuario(@Param("id_usuario") long id_usuario);

    //OBJETO PERSONAL
    @Query(value = "SELECT * " +
            "FROM usuario u  JOIN personal p ON u.id_usuario = p.id_usuario " +
            "JOIN usu_rol ur ON ur.id_usuario = u.id_usuario\n" +
            "JOIN rol r ON r.id = ur.id_rol " +
            " WHERE r.id <> 2 AND p.id_usuario = ?1"
            , nativeQuery = true)
    public Optional<Usuario> findPersonalById_usuario(@Param("id_usuario") long id_usuario);


    @Query(value = "SELECT * FROM usuario u", nativeQuery = true)
    public List<Usuario> findOnlyUsuario();

    @Query(value = "SELECT * FROM usuario u  WHERE u.rut =?1", nativeQuery = true)
    public Optional<Usuario> findUsuarioByRut(String rut);


    boolean existsByRut(String rut);

//    @Query(value = "SELECT * FROM usuario u" +
//            " INNER JOIN paciente p ON u.id_usuario = p.id_usuario" +
//            " INNER JOIN consulta c ON p.id_paciente = c.id_paciente" +
//            " INNER JOIN filiacion f ON p.id_paciente = f.id_paciente" +
//            " INNER JOIN examen e ON f.id_filiacion = e.id_filiacion" +
//            " WHERE e.estado > ?1 AND e.visto = ?2 AND c.visto != ?3" +
//            "ORDER BY e.fecha_examen DESC", nativeQuery = true)
//    List<Usuario> getCriticList(@Param("estado") int estado, @Param("e.visto") boolean e_visto,@Param("c.visto") boolean c_visto);

}
