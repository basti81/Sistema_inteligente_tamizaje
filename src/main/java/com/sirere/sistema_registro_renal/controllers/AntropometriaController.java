package com.sirere.sistema_registro_renal.controllers;

import com.sirere.sistema_registro_renal.entity.Antropometria;
import com.sirere.sistema_registro_renal.entity.Examen;
import com.sirere.sistema_registro_renal.entity.Filiacion;
import com.sirere.sistema_registro_renal.services.AntropometriaService;
import com.sirere.sistema_registro_renal.services.FiliacionService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.Option;
import java.awt.print.Pageable;
import java.util.Optional;


@Controller
@RequestMapping("/antropometria")
public class AntropometriaController {

    @Autowired
    private AntropometriaService antropometriaService;

    @Autowired
    private FiliacionService filiacionService;

    @GetMapping("/lista")
    public ModelAndView list(@RequestParam("id_filiacion") long id_filiacion) {
        ModelAndView mv = new ModelAndView();
        Filiacion filiacion = new Filiacion();
        filiacion.setId_filiacion(id_filiacion);
        filiacion.setAntropometrias(antropometriaService.myAntropometrys(id_filiacion));
        mv.addObject("filiacion", filiacion);
        mv.setViewName("/filiacion/antropometria/lista");
        return mv;
    }

    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/nuevo")
    public ModelAndView nuevo(Antropometria antropometria, @RequestParam("id_filiacion") long id_filiacion) {
//    @GetMapping("/nuevo")
//    public ModelAndView nuevo(Examen examen) {
//        System.out.println("Entre a nuevo examen " + id_filiacion);
        ModelAndView mv = new ModelAndView();
        Filiacion filiacion = new Filiacion();
        filiacion.setId_filiacion(id_filiacion);
        antropometria.setFiliacion(filiacion);
        mv.setViewName("/filiacion/antropometria/nuevo");
        mv.addObject("antropometria", antropometria);
        return mv;
    }

    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/create")
    public ModelAndView crear(Antropometria antropometria, @RequestParam("id_filiacion") long id_filiacion, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            System.out.println("Existieron errores");
            mv.setViewName("/filiacion/antropometria/nuevo");
            mv.addObject("error", "el nombre no puede estar vacío");
            return mv;
        }

        Filiacion filiacion = new Filiacion();
        filiacion.setId_filiacion(id_filiacion);
        antropometria.setFiliacion(filiacion);
        antropometriaService.save(antropometria);
        mv.setViewName("redirect:/antropometria/lista?id_filiacion="+id_filiacion);
        return mv;
    }

    @GetMapping("/eliminar")
    public ModelAndView eliminar(@RequestParam("id_antropometria") long id_antropometria, RedirectAttributes attributes){
        ModelAndView mv = new ModelAndView();
        if(antropometriaService.existsById(id_antropometria)){
            Optional<Antropometria> optional = antropometriaService.getOne(id_antropometria);
            if(optional.isPresent()){
                mv.setViewName("redirect:antropometria/lista?id_filiacion="+optional.get().getFiliacion().getId_filiacion());
                antropometriaService.delete(id_antropometria);
                attributes.addFlashAttribute("msg","Antropometria Eliminada");
            }
        }else{
            attributes.addFlashAttribute("msg","Antropometria No Eliminada");
        }
        return mv;
    }

    @GetMapping("/actualizar")
    public ModelAndView actualizar (@RequestParam("id_antropometria") long id_antropometria) {
        ModelAndView mv = new ModelAndView();
        if (antropometriaService.existsById(id_antropometria)) {
            Optional<Antropometria> optional = antropometriaService.getOne(id_antropometria);
            if (optional.isPresent()) {
                mv.setViewName("/filiacion/antropometria/nuevo");
                mv.addObject("antropometria", optional.get());
                return mv;
            }
        }
        mv.setViewName("/filiacion/antropometria/lista");
        return mv;
    }

    @GetMapping("/detalle")
    public ModelAndView detalle(@RequestParam("id_antropometria") long id_antropometria){
        ModelAndView mv = new ModelAndView();
        if(antropometriaService.existsById(id_antropometria)){
            Optional<Antropometria> optional = antropometriaService.getOne(id_antropometria);
            if(optional.isPresent()){
                mv.setViewName("/filiacion/antropometria/detalle");
                mv.addObject("antropometria",optional.get());
            }
        }
        return mv;
    }


}
