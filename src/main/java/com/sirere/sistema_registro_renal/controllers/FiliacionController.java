package com.sirere.sistema_registro_renal.controllers;


import com.sirere.sistema_registro_renal.entity.Filiacion;
import com.sirere.sistema_registro_renal.entity.Usuario;
import com.sirere.sistema_registro_renal.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/filiacion")
public class FiliacionController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private FiliacionService filiacionService;
    @Autowired
    private ExamenService examenService;
    @Autowired
    private SignosService signosService;
    @Autowired
    private AntropometriaService antropometriaService;



    @GetMapping("/lista")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/filiacion/lista");
        List<Filiacion> filiaciones = filiacionService.list();
        mv.addObject("filiaciones", filiaciones);
//        for (Filiacion filiacion : filiaciones) {
//            System.out.println("Filiacion = " + filiacion);
//        }
        return mv;
    }

    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/nuevo")
    public ModelAndView nuevo(Filiacion filiacion) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("filiacion/nuevo");
        mv.addObject("filiacion", filiacion);
        return mv;
    }

    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/create")
    public ModelAndView create(Filiacion filiacion, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        System.out.println("Filiacion = " + filiacion.toString());
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio un error" + error.getDefaultMessage());
            }
            mv.setViewName("filiacion/nuevo");
            attributes.addFlashAttribute("msg", "Error de Ingreso");
            return mv;
        }

        filiacionService.save(filiacion);
        attributes.addFlashAttribute("msg", "Registro Guardado");
        mv.setViewName("redirect:/filiacion/lista");
        return mv;
    }

    @PostMapping("/save")
    public void save(Filiacion filiacion) {
        filiacionService.save(filiacion);
    }

    @GetMapping("/eliminar")
    public ModelAndView delete(@RequestParam("id_filiacion") long id_filiacion, RedirectAttributes attributes) {
        if (filiacionService.existsById(id_filiacion)) {
            filiacionService.delete(id_filiacion);
            return new ModelAndView("redirect:/filiacion/lista");
        }
        return null;
    }


    // MIS DATOS
    @GetMapping("/detalle")
    public ModelAndView misDatos(@RequestParam("id_usuario") long id_usuario) {
        ModelAndView mv = new ModelAndView();
        if(usuarioService.existsById(id_usuario)){
            Optional<Usuario> optional = usuarioService.getOne(id_usuario);
            if(optional.isPresent()){
                mv.addObject("usuario",optional.get());
                mv.setViewName("/filiacion/detalle");
            }
        }
        return mv;
    }

    //HISTORIAL
    @GetMapping("/historial")
    public ModelAndView historial(@RequestParam("id_usuario") long id_usuario){
        ModelAndView mv = new ModelAndView();
        if(usuarioService.existsById(id_usuario)){
            Optional<Usuario> optional = usuarioService.getOne(id_usuario);
            if(optional.isPresent()){
                mv.addObject("usuario",optional.get());
                mv.setViewName("/filiacion/historial/detalle");
                return mv;
            }
            mv.setViewName("redirect:/paciente/lista");
        }
        return mv;
    }








}
