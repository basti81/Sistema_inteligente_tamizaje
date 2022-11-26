package com.sirere.sistema_registro_renal.controllers;


import com.sirere.sistema_registro_renal.biblioteca.Util;
import com.sirere.sistema_registro_renal.entity.*;
import com.sirere.sistema_registro_renal.enums.RolEnum;
import com.sirere.sistema_registro_renal.services.*;
import com.sirere.sistema_registro_renal.util.Utility;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private  UsuarioService usuarioService;
    @Autowired
    private RolService rolService;
    @Autowired
    private ConsultaService consultaService;
    @Autowired
    private SendMailService sendMailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FiliacionService filiacionService;

    private Util util = new Util();

    @GetMapping("/lista")
    public ModelAndView list() throws ParseException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/paciente/lista");
        List<Paciente> pacientes = pacienteService.list();
        mv.addObject("pacientes", pacientes);
        return mv;
    }

    @GetMapping("/nuevo")
    public ModelAndView nuevo(Usuario usuario) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/paciente/nuevo");
        mv.addObject("usuario", usuario);
        return mv;
    }


    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/create")
    public ModelAndView crear(Usuario usuario, BindingResult result, RedirectAttributes attributes,
                              @RequestParam("fileProfile") MultipartFile multiPart) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            System.out.println("Existieron errores");
            mv.setViewName("paciente/nuevo");
            mv.addObject("error", "Paciente no Registrado");
            return mv;
        }

        if (!multiPart.isEmpty()) {
            String ruta = "C:/Users/basti/Documents/Trabajo/appWebSirere/sistema_registro_renal/src/main/resources/static/img/users/";
            String nombreImagen = Utility.saveFile(multiPart, ruta);
            if (nombreImagen != null){ // La imagen si se subio
            // Procesamos la variable nombreImagen
                usuario.setImg(nombreImagen);
            }
        }
        if(!usuarioService.existsByRut(usuario.getRut())){
            Rol rolCliente = rolService.getByRolEnum(RolEnum.CLIENTE).get();
            Set<Rol> rol = new HashSet<>();
            rol.add(rolCliente);
            usuario.setRoles(rol);
            String pass = util.getRandomString(6);
            System.out.println("Clave Paciente = "+pass);
            usuario.setPassword(passwordEncoder.encode(pass));
            usuario.setFecha_ingreso(LocalDate.now());
            usuarioService.save(usuario);
            attributes.addFlashAttribute("msg","Paciente Ingresado");
            sendMailPaciente(usuario,pass);
            mv.setViewName("redirect:/paciente/lista");
            return mv;
        }

        pacienteService.save(usuario.getPaciente());
        attributes.addFlashAttribute("msg","Paciente Modificado");
        Optional<Paciente> paciente = pacienteService.getOne(usuario.getPaciente().getId_paciente());
        mv.setViewName("/filiacion/detalle");
        mv.addObject("paciente",paciente.get());
        return mv;

    }

    public void sendMailPaciente(Usuario usuario,String pass){
        //enviar email
        Mail mail = new Mail();
        mail.setTo(usuario.getContacto().getCorreo());
        mail.setFrom("Soporte.Sistema.Inteligente@mail.com");
        mail.setSubject(" -- Paciente Ingresado -- ");
        mail.setBody("Estimado: " + usuario.getOnlyName() + ", su registro fue un exito!," +
                "la credenciales para el ingreso a la plataforma se despliegan a continuación\n" +
                "------------------------------------------\n"+
                "Nombre usuario: "+usuario.getRut()+"\n"+
                "Password: "+pass+"\n" +
                "------------------------------------------");

        sendMailService.sendMail(mail);
    }

    @GetMapping("/actualizar")
    public ModelAndView update(@RequestParam("id_usuario") long id_usuario) {
        ModelAndView mv = new ModelAndView();
        if(usuarioService.existsById(id_usuario)){
            Optional<Usuario> usuario = usuarioService.getOne(id_usuario);
            mv.setViewName("/paciente/nuevo");
            mv.addObject("usuario", usuario.get());
            return mv;
        }
        mv.setViewName("/paciente/lista");
        return mv;
    }


    @GetMapping("/detalle")
    public ModelAndView detalle(@RequestParam("id_paciente") long id_paciente) {
        ModelAndView mv = new ModelAndView();
        if(pacienteService.existsById(id_paciente)){
            Optional<Paciente> paciente = pacienteService.getOne(id_paciente);
            mv.setViewName("/filiacion/detalle");
            mv.addObject("paciente",paciente.get());
            return mv;
        }
        return mv;
    }

    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/eliminar")
    public ModelAndView eliminar(@RequestParam("id_usuario") long id_usuario, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if(usuarioService.existsById(id_usuario)){
            usuarioService.delete(id_usuario);
            attributes.addFlashAttribute("msg","Paciente Eliminado");
        }
        mv.setViewName("redirect:/paciente/lista");
        return mv;
    }

}
