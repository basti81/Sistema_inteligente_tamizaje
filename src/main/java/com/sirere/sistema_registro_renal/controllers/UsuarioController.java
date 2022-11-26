package com.sirere.sistema_registro_renal.controllers;

import com.sirere.sistema_registro_renal.entity.Especialidad;
import com.sirere.sistema_registro_renal.entity.Personal;
import com.sirere.sistema_registro_renal.entity.Rol;
import com.sirere.sistema_registro_renal.entity.Usuario;
import com.sirere.sistema_registro_renal.enums.EspecilidadEnum;
import com.sirere.sistema_registro_renal.enums.RolEnum;
import com.sirere.sistema_registro_renal.services.EspecialidadService;
import com.sirere.sistema_registro_renal.services.RolService;
import com.sirere.sistema_registro_renal.services.UsuarioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @Autowired
    private EspecialidadService especialidadService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @PostMapping("/registrar")
    public ModelAndView registrar(String rut, String password) {
        ModelAndView mv = new ModelAndView();
        if (StringUtils.isBlank(rut)) {
            mv.setViewName("/registro");
            mv.addObject("error", "el nombre no puede estar vacío");
            return mv;
        }
        if (StringUtils.isBlank(password)) {
            mv.setViewName("/registro");
            mv.addObject("error", "la contraseña no puede estar vacía");
            return mv;
        }
        if (usuarioService.existsByRut(rut)) {
            mv.setViewName("/registro");
            mv.addObject("error", "ese nombre de usuario ya existe");
            return mv;
        }
        Usuario usuario = new Usuario();
        Personal personal = new Personal();
        System.out.println("Entre a registro");
        System.out.println("Rut = "+rut);
        System.out.println("Password = "+password);
        usuario.setRut(rut);
        usuario.setPassword(passwordEncoder.encode(password));

        //INGRESAR ROLES
        Rol rolAdmin = rolService.getByRolEnum(RolEnum.DOCTOR).get();
        Set<Rol> roles = new HashSet<>();
        roles.add(rolAdmin);
        usuario.setRoles(roles);

        //INGRESAR ESPECIALIDAD
        Especialidad especialidad = especialidadService.getByEspecialidadEnum(EspecilidadEnum.NINGUNA).get();
        Set<Especialidad> especialidades = new HashSet<>();
        especialidades.add(especialidad);
        personal.setEspecialidad(especialidades);
        usuario.setPersonal(personal);

        usuarioService.save(usuario);
        mv.setViewName("/login");
        mv.addObject("registroOK", "Cuenta creada, " + usuario.getRut() + ", ya puedes iniciar sesión");
        return mv;
    }
}
