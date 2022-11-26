package com.sirere.sistema_registro_renal.services;

import com.sirere.sistema_registro_renal.entity.AutoDiagnostico;
import com.sirere.sistema_registro_renal.entity.Usuario;
import com.sirere.sistema_registro_renal.repository.AutoDiagnosticoRepository;
import com.sirere.sistema_registro_renal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoDiagnosticoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AutoDiagnosticoRepository autoDiagnosticoRepository;

    public List<AutoDiagnostico> list(){
        return autoDiagnosticoRepository.findAllAutoDiagnostico();
    }
//    public List<AutoDiagnostico> list(){
//        return autoDiagnosticoRepository.findAll();
//    }

    public void delete(Long id) {
        autoDiagnosticoRepository.deleteById(id);
    }
}
