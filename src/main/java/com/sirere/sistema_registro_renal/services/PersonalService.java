package com.sirere.sistema_registro_renal.services;


import com.sirere.sistema_registro_renal.entity.Personal;
import com.sirere.sistema_registro_renal.entity.Usuario;
import com.sirere.sistema_registro_renal.repository.PersonalRepository;
import com.sirere.sistema_registro_renal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class  PersonalService {

    @Autowired
    private PersonalRepository personalRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Personal> list(){
        return personalRepository.findAll();
    }

    public List<Usuario> listDoctor(){return usuarioRepository.findAllDoctor();}

    public Optional<Usuario> findPersonalById_usuario(Long id_usuario){
        Optional<Usuario> optional = usuarioRepository.findPersonalById_usuario(id_usuario);
        if(optional.isPresent()){
            return optional;
        }
        return null;
    }

    public Optional<Personal> getOne(Long id_personal) {

        return personalRepository.findById(id_personal);
    }

    public Optional<Personal> findByIdUsuario(Long id_usuario){
        return personalRepository.findByIdUsuario(id_usuario);
    }

    public void save(Personal usuario){
        personalRepository.save(usuario);
    }

    public boolean existsById(Long id_usuario){
        return personalRepository.existsById(id_usuario);
    }

    public void delete(Long id_usuario){personalRepository.deleteById(id_usuario);}

}
