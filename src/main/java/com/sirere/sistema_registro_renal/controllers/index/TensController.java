package com.sirere.sistema_registro_renal.controllers.index;

import com.sirere.sistema_registro_renal.entity.Usuario;
import com.sirere.sistema_registro_renal.services.PacienteService;
import com.sirere.sistema_registro_renal.services.SignosService;
import com.sirere.sistema_registro_renal.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/tens")
public class TensController {

    @GetMapping("/index")
    public ModelAndView index(@RequestParam("id_usuario")   Long id_usuario){
        ModelAndView mv = new ModelAndView();
        System.out.println("ENTRE A TENS, ID USUARIO = "+id_usuario);
        mv.setViewName("/tens/index");
        return mv;
    }





}
