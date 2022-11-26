package com.sirere.sistema_registro_renal.controllers.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/enfermeria")
public class EnfermeriaController {

    @GetMapping("/index")
    public ModelAndView index(@RequestParam("id_usuario") Long id_usuario){
        ModelAndView mv = new ModelAndView();
        System.out.println("ENTRE A ENFERMERIA, ID USUARIO ="+id_usuario);
        mv.setViewName("/enfermeria/index");
        return mv;
    }
}
