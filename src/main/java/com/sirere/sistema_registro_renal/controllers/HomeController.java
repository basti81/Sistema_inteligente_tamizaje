package com.sirere.sistema_registro_renal.controllers;

import com.sirere.sistema_registro_renal.entity.Examen;
import com.sirere.sistema_registro_renal.entity.Paciente;
import com.sirere.sistema_registro_renal.entity.Personal;
import com.sirere.sistema_registro_renal.entity.Usuario;
import com.sirere.sistema_registro_renal.enums.EnumRol;
import com.sirere.sistema_registro_renal.enums.RolEnum;
import com.sirere.sistema_registro_renal.services.ExamenService;
import com.sirere.sistema_registro_renal.services.PacienteService;
import com.sirere.sistema_registro_renal.services.PersonalService;
import com.sirere.sistema_registro_renal.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private ExamenService examenService;
    @Autowired
    private PersonalService personalService;
    private EnumRol enumRol;

//    @GetMapping(value = {"/", "/index"})
//    public ModelAndView index(){
//        ModelAndView mv = new ModelAndView();
//        List<Examen> examenes = examenService.getCriticList(3,false,"No agendado");
//        mv.addObject("examenes", examenes);
//        mv.addObject("countList",examenes.size());
//        mv.addObject("msg","Bienvenido");
//        mv.setViewName("index");
//        return mv;
//    }


    @GetMapping(value = {"/", "/index"})
    public ModelAndView index(@RequestParam(value = "principalId_usuario",required = false) Long id_usuario){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        if (id_usuario == null){
            mv.addObject("msg","Bienvenido, nos alegra verte!");
            return mv;
        }
        if(usuarioService.existsById(id_usuario)){
            Optional<Usuario> optional = usuarioService.getOne(id_usuario);
            String auxRol = optional.get().getRoles().stream().findAny().get().getRolNombre().name();

            if(auxRol.equals(EnumRol.ADMINISTRADOR.name())){
                mv.setViewName("redirect:/personal/lista");
                return mv;
            }
            if(auxRol.equals(EnumRol.GES.name())){
                mv.setViewName("redirect:/paciente/lista");
                return mv;
            }
            if(auxRol.equals(EnumRol.DOCTOR.name())){
                //todos los pacientes agendados del medico
                Optional<Personal> personal = personalService.findByIdUsuario(id_usuario);
                mv.setViewName("redirect:/consulta/misConsultas?id_personal=" + personal.get().getId_personal());
                return mv;
            }
            if(auxRol.equals(EnumRol.LABORATORISTA.name())){
                //todos los pacientes agendados
                mv.setViewName("redirect:/paciente/lista");
                return mv;
            }
            if(auxRol.equals(EnumRol.CLIENTE.name())){
                //todos los pacientes agendados
                Optional<Usuario> usuario = pacienteService.findPacienteById_usuario(id_usuario);
                mv.setViewName("redirect:/paciente/detalle?id_paciente="+usuario.get().getPaciente().getId_paciente());
                return mv;
            }


        }
        mv.addObject("msg",null);
        return mv;
    }

    @GetMapping("/prueba")
    public String test(){
        return "test";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/forbidden")
    public String forbidden(){
        return "forbidden";
    }



}

