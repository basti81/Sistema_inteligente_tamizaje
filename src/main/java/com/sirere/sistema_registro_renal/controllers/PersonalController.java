package com.sirere.sistema_registro_renal.controllers;

import com.sirere.sistema_registro_renal.entity.Personal;
import com.sirere.sistema_registro_renal.entity.Usuario;
import com.sirere.sistema_registro_renal.services.PersonalService;
import com.sirere.sistema_registro_renal.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PersonalService personalService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/lista")
    public ModelAndView list() throws ParseException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/personal/lista");
        List<Personal> list = personalService.list();
        mv.addObject("personals", list);
        return mv;
    }

    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/nuevo")
    public ModelAndView nuevo(Usuario usuario) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/personal/nuevo");
        mv.addObject("usuario", usuario);
        return mv;
    }

    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/create")
    public ModelAndView crear(Usuario usuario, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            System.out.println("Existieron errores");
            mv.setViewName("personal/nuevo");
            mv.addObject("error", "Personal no Registrado");
            return mv;
        }
//        System.out.println(usuario.toString());
//        System.out.println(usuario.getPersonal().toString());
//        System.out.println(usuario.getContacto().toString());
        if(usuarioService.existsByRut(usuario.getRut())){
            personalService.save(usuario.getPersonal());
            attributes.addFlashAttribute("msg", "Personal Modificado");
            mv.setViewName("redirect:/personal/lista");
            return mv;
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setFecha_ingreso(LocalDate.now());
        usuarioService.save(usuario);
        attributes.addFlashAttribute("msg", "Personal Ingresado");
        mv.setViewName("redirect:/personal/lista");
        return mv;
    }


    @GetMapping("/detalle")
    public ModelAndView detalle(@RequestParam("id_usuario") long id_usuario) {
        ModelAndView mv = new ModelAndView();
        if (usuarioService.existsById(id_usuario)) {
            Optional<Usuario> optional = usuarioService.getOne(id_usuario);
            if (optional.isPresent()) {
                mv.addObject("usuario", optional.get());
                mv.setViewName("/personal/detalle");
                return mv;
            }
        }
        mv.setViewName("redirect: /personal/lista");
        return mv;
    }

    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/eliminar")
    public ModelAndView eliminar(@RequestParam("id_usuario") long id_usuario, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (usuarioService.existsById(id_usuario)) {
            usuarioService.delete(id_usuario);
            attributes.addFlashAttribute("msg", "Personal Eliminado");
        } else {
            attributes.addFlashAttribute("msg", "Personal No Encontrado");
        }
        mv.setViewName("redirect:/personal/lista");
        return mv;
    }

    @GetMapping("/actualizar")
    public ModelAndView actualizar(@RequestParam("id_usuario") long id_usuario, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (usuarioService.existsById(id_usuario)) {
            Optional<Usuario> optional = usuarioService.getOne(id_usuario);
            if (optional.isPresent()) {
                mv.addObject(optional.get());
                mv.setViewName("/personal/nuevo");
                return mv;
            }
        }
        attributes.addFlashAttribute("msg","Personal No Encontrado");
        mv.setViewName("redirect:/personal/lista");
        return mv;
    }
}
