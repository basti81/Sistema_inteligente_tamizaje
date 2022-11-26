package com.sirere.sistema_registro_renal.controllers.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("ges")
public class GesController {

    @GetMapping("index")
    public ModelAndView index(@RequestParam("id_usuario") Long id_usuario){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/ges/index");
        System.out.println("ENTRE A GES, ID USUARIO ="+id_usuario);
        return mv;
    }
}
