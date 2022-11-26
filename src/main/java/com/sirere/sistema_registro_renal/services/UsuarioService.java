package com.sirere.sistema_registro_renal.services;

import com.sirere.sistema_registro_renal.biblioteca.Formato;
import com.sirere.sistema_registro_renal.entity.Usuario;
import com.sirere.sistema_registro_renal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    Formato frt = new Formato();

    public List<Usuario> lista() {
        return usuarioRepository.findAll();
    }


    //OBTENER USUARIO MEDIANTE LA ID DE FILIACION
    public Optional<Usuario> findUsuarioByFiliacion(Long id_filiacion) {
        Optional<Usuario> optional = usuarioRepository.findUsuarioByFiliacion(id_filiacion);
        if (optional.isPresent()) {
            return optional;
        }
        return null;
    }

    //OBTENER PACIENTE MEDIANTE ID USUARIO
    public Optional<Usuario> findPacienteById_usuario(long id_usuario) {
        Optional<Usuario> optional = usuarioRepository.findPacienteById_usuario(id_usuario);
        if (optional.isPresent()) {
            return optional;
        }
        return null;
    }


    public Optional<Usuario> getOne(Long id_usuario) {
        Optional<Usuario> optional = usuarioRepository.findById(id_usuario);
        if (optional.isPresent()) {
            return optional;
        }
        return null;
    }
    public Optional<Usuario> findUsuarioByRut(String rut) {
        Optional<Usuario> optional = usuarioRepository.findUsuarioByRut(rut);
        if (optional.isPresent()) {
            System.out.println(optional.get().toString());
            return optional;
        }
        return null;
    }
    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
    public boolean existsById(Long id_usuario) {
        return usuarioRepository.existsById(id_usuario);
    }
    public boolean existsByRut(String rut){return usuarioRepository.existsByRut(rut);}
    public void delete(Long id_usuario) {
        usuarioRepository.deleteById(id_usuario);
    }



}
