package com.sirere.sistema_registro_renal.controllers;
import com.sirere.sistema_registro_renal.biblioteca.simulations.UtilSimulation;
import com.sirere.sistema_registro_renal.entity.Filiacion;
import com.sirere.sistema_registro_renal.entity.SignoVital;
import com.sirere.sistema_registro_renal.services.FiliacionService;
import com.sirere.sistema_registro_renal.services.SignosService;
import com.sirere.sistema_registro_renal.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/signo")
public class SignosController {

    @Autowired
    private SignosService signosService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private FiliacionService filiacionService;


    @PostMapping("/save")
    public void save(SignoVital signoVital) {
        signosService.save(signoVital);
    }

    @GetMapping("/lista")
    public ModelAndView mySignos(@RequestParam("id_filiacion") long id_filiacion) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/filiacion/signos/lista");
        Optional<Filiacion> filiacion = filiacionService.getOne(id_filiacion);
        if (filiacion.isPresent()){
            mv.addObject("filiacion", filiacion.get());
            filiacion.get().setSignos(signosService.mySignos(id_filiacion));
        }
        return mv;
    }

//    @GetMapping("/lista")
//    public ModelAndView list() {
//        ModelAndView mv = new ModelAndView();
//        Filiacion filiacion = new Filiacion();
//        filiacion.setExamenes(signosService.list());
//        mv.setViewName("/filiacion/examen/lista");
//        mv.addObject("filiacion", filiacion);
//        return mv;
//    }

    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/nuevo")
    public ModelAndView nuevo(SignoVital signoVital, @RequestParam("id_filiacion") long id_filiacion) {
        ModelAndView mv = new ModelAndView();
        Optional<Filiacion> filiacion= filiacionService.getOne(id_filiacion);
        if(filiacion.isPresent()){
            signoVital.setFiliacion(filiacion.get());
            mv.addObject("filiacion",filiacion.get());
        }
        mv.addObject("signo", signoVital);
        mv.setViewName("/filiacion/signos/nuevo");
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView crear(SignoVital signoVital,
                              @RequestParam("id_filiacion") long id_filiacion,
                              BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            for(ObjectError error : result.getAllErrors()){
                System.out.println("Ocurrio un error" + error.getDefaultMessage());
            }
            mv.setViewName("filiacion/signos/nuevo");
            mv.addObject("error", "Signos Vitales no registrado");
            return mv;
        }
        Optional<Filiacion> optional = filiacionService.getOne(id_filiacion);
        mv.setViewName("redirect:/signo/lista?id_filiacion=" + id_filiacion);
        if(optional.isPresent()){
            signoVital.setFiliacion(optional.get());
            signosService.save(signoVital);
            attributes.addFlashAttribute("msg","Signo Vital Ingresado");
            return mv;
        }
        attributes.addFlashAttribute("error","Signo Vital no Ingresado");
        return mv;
    }

    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/actualizar")
    public ModelAndView actualizar(@RequestParam("id_filiacion") long id_filiacion,
            @RequestParam("id_signo") long id_signo){
        ModelAndView mv = new ModelAndView();
        Optional<Filiacion> filiacion = filiacionService.getOne(id_filiacion);
        mv.addObject("filiacion",filiacion.get());
        mv.setViewName("/filiacion/signos/nuevo");
        if(signosService.existsById(id_signo)){
            Optional<SignoVital> optional = signosService.getOne(id_signo);
            mv.addObject("signo",optional.get());
        }
        return mv;
    }


    @GetMapping("/detalle")
    public ModelAndView detalle(@RequestParam("id_filiacion") long id_filiacion,
            @RequestParam("id_signo") long id_sinos) {
        ModelAndView mv = new ModelAndView();
        Optional<Filiacion> filiacion = filiacionService.getOne(id_filiacion);
        mv.setViewName("/filiacion/signos/detalle");
        if (filiacion.isPresent()){
            System.out.println("Encontré filiacion "+filiacion.get().toString());
            mv.addObject("filiacion",filiacion.get());
            if (signosService.existsById(id_sinos)) {
                Optional<SignoVital> optional = signosService.getOne(id_sinos);
                mv.addObject("signo", optional.get());
            }
        }
        return mv;
    }

    @GetMapping("/eliminar")
    public ModelAndView delete(@RequestParam("id_filiacion") long id_filiacion,
            @RequestParam("id_signo") long id_signo, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/signo/lista?id_filiacion=" + id_filiacion);
        if (signosService.existsById(id_signo)) {
            signosService.delete(id_signo);
            attributes.addFlashAttribute("msg", "Signos Vitales eliminado");
            return mv;
        }
        attributes.addFlashAttribute("msg", "Signos Vitales no eliminado");
        return mv;
    }

    @GetMapping("/simulation")
    public ModelAndView simulation(@RequestParam("id_filiacion") long id_filiacion){
        ModelAndView mv = new ModelAndView();
        UtilSimulation utilSimulation = new UtilSimulation();
        Optional<Filiacion> optional = filiacionService.getOne(id_filiacion);
        if(optional.isPresent()){
            for( int i = 0; i<10; i++){
                signosService.save(utilSimulation.setInyecSigno(optional.get()));
            }
        }
        mv.setViewName("redirect:/signo/lista?id_filiacion=" + id_filiacion);
        return mv;
    }

    @GetMapping("/reporte")
    public ModelAndView myEvolution(@RequestParam("id_filiacion") long id_filiacion){
        ModelAndView mv = new ModelAndView();
        ArrayList<LocalDate> v_date_signo = new ArrayList<>();
        ArrayList<Integer> v_signo_pa = new ArrayList<>();
        ArrayList<Integer> v_signo_pb = new ArrayList<>();

        mv.setViewName("/filiacion/signos/reporte");
        Optional<Filiacion> filiacion = filiacionService.getOne(id_filiacion);
        if(filiacion.isPresent()){
            List<SignoVital> signoVitalList = signosService.listForGrafic(id_filiacion);
            if(!signoVitalList.isEmpty()){
                for (SignoVital signo: signoVitalList) {
                    v_date_signo.add(signo.getFecha_signo());
                    v_signo_pa.add(signo.getP_alta());
                    v_signo_pb.add(signo.getP_baja());
                }
                mv.addObject("signo",signoVitalList.get(signoVitalList.size()-1));
                mv.addObject("v_date_signo",v_date_signo);
                mv.addObject("v_signo_pa",v_signo_pa);
                mv.addObject("v_signo_pb",v_signo_pb);
            }
            mv.addObject("filiacion",filiacion.get());
        }
        return mv;
    }
}
