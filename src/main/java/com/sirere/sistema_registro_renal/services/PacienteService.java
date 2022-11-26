package com.sirere.sistema_registro_renal.services;

import com.sirere.sistema_registro_renal.biblioteca.Formato;
import com.sirere.sistema_registro_renal.entity.Paciente;
import com.sirere.sistema_registro_renal.entity.Usuario;
import com.sirere.sistema_registro_renal.repository.PacienteRepository;
import com.sirere.sistema_registro_renal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private List<Paciente> list = null;
    private  Formato frt = new Formato();



    public Optional<Usuario> findPacienteById_usuario(long id_usuario){
        Optional<Usuario> optional = usuarioRepository.findPacienteById_usuario(id_usuario);
        if (optional.isPresent()){
            return optional;
        }
        return null;
    }
    //public List<Paciente> list() {
        //return usuarioRepository.findAllPaciente();
    //}
    public List<Paciente> list(){
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> getOne(long id) {
        return pacienteRepository.findById(id);
    }

    public void save(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    public void delete(long id_paciente) {
        pacienteRepository.deleteById(id_paciente);
    }

    public boolean existsById(long id) {
        return pacienteRepository.existsById(id);
    }


}
