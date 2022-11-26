package com.sirere.sistema_registro_renal.test;

import com.sirere.sistema_registro_renal.entity.Contacto;
import com.sirere.sistema_registro_renal.entity.Personal;
import com.sirere.sistema_registro_renal.entity.Rol;
import com.sirere.sistema_registro_renal.entity.Usuario;
import com.sirere.sistema_registro_renal.enums.RolEnum;
import com.sirere.sistema_registro_renal.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


public class test_update_usuario implements CommandLineRunner {
    @Autowired
    private RolService rolService;

    @Autowired
    private EspecialidadService especialidadService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private UsuarioService usuarioService;
    @Override
    public void run(String... args) throws Exception {

       if (usuarioService.existsById(5l)) {
           Optional<Usuario> optional = usuarioService.getOne(5l);
           if (optional.isPresent()){
               optional.get().setNombre("pp");
               pacienteService.save(optional.get().getPaciente());
           }
        }

    }
}
