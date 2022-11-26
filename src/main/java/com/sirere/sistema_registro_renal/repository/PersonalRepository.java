package com.sirere.sistema_registro_renal.repository;

import com.sirere.sistema_registro_renal.entity.Personal;
import com.sirere.sistema_registro_renal.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PersonalRepository extends JpaRepository<Personal,Long> {
    @Query(value = "SELECT * " +
            "FROM usuario u  JOIN personal p ON u.id_usuario = p.id_usuario " +
            "JOIN usuario_rol ur ON ur.id_usuario = u.id_usuario\n" +
            "JOIN rol r ON r.id = ur.id_rol" +
            " WHERE r.id <> 2"
            ,nativeQuery = true)
    public List<Usuario> findAllPersonal();


    @Query(value = "SELECT * FROM usuario u  JOIN personal p ON u.id_usuario = p.id_usuario " +
            "WHERE p.id_usuario = ?1"
        ,nativeQuery = true)
    public Optional<Personal> findByIdUsuario(Long id_usuario);

//    @Query(value = "SELECT * " +
//            "FROM usuario u  JOIN personal p ON u.id_usuario = p.id_usuario " +
//            "JOIN usuario_rol ur ON ur.id_usuario = u.id_usuario\n" +
//            "JOIN rol r ON r.id = ur.id_rol" +
//            " WHERE r.id <> 2 AND p.id_usuario = ?1"
//            ,nativeQuery = true)
//    public Optional<Personal> findPersonalById_usuario(Long id_usuario);
}
