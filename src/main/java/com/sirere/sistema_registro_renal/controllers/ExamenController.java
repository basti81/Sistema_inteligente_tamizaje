package com.sirere.sistema_registro_renal.controllers;

import com.sirere.sistema_registro_renal.biblioteca.Diagnosis;
import com.sirere.sistema_registro_renal.biblioteca.SendEmail;
import com.sirere.sistema_registro_renal.entity.*;
import com.sirere.sistema_registro_renal.services.*;
import com.sirere.sistema_registro_renal.biblioteca.simulations.UtilSimulation;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/examen")
public class ExamenController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ExamenService examenService;
    @Autowired
    private FiliacionService filiacionService;
    @Autowired
    private AutoDiagnosticoService autoDiagnosticoService;
    @Autowired
    private SendMailService sendMailService;


    private Diagnosis diagnosis = new Diagnosis();

    @PostMapping("/save")
    public void save(Examen examen) {
        examenService.save(examen);
    }


    @GetMapping("/lista")
    public ModelAndView myExamns(@RequestParam("id_filiacion") long id_filiacion) {
        ModelAndView mv = new ModelAndView();
        Optional<Filiacion> filiacion = filiacionService.getOne(id_filiacion);
        mv.setViewName("/filiacion/examen/lista");
        if(filiacion.isPresent()){
            mv.addObject("filiacion", filiacion.get());
            filiacion.get().setExamenes(examenService.myExams(id_filiacion));
        }
        return mv;
    }


    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/nuevo")
    public ModelAndView nuevo(Examen examen, @RequestParam("id_filiacion") long id_filiacion) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/filiacion/examen/nuevo");
        Optional<Filiacion> filiacion = filiacionService.getOne(id_filiacion);
        if (filiacion.isPresent()){
            mv.addObject("filiacion",filiacion.get());
            examen.setFiliacion(filiacion.get());
            mv.addObject("examen", examen);
        }
        return mv;
    }

    @GetMapping("/actualizar")
    public ModelAndView update(@RequestParam("id_filiacion") long id_filiacion,
                               @RequestParam("id_examen") long id_examen) {
        ModelAndView mv = new ModelAndView();
        Optional<Filiacion> filiacion = filiacionService.getOne(id_filiacion);
        mv.addObject("filiacion",filiacion.get());
        mv.setViewName("/filiacion/examen/nuevo");
        if (examenService.existsById(id_examen)) {
            Optional<Examen> optional = examenService.getOne(id_examen);
            mv.addObject("examen", optional.get());
        }
        return mv;
    }

    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/create")
    public ModelAndView crear(Examen examen,
                              @RequestParam("id_filiacion") long id_filiacion,
                              BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            for(ObjectError error : result.getAllErrors()){
                System.out.println("Ocurrio un error" + error.getDefaultMessage());
            }
            mv.setViewName("filiacion/examen/nuevo");
            mv.addObject("error", "Examen no Registrado");
            return mv;
        }

        Optional<Filiacion> filiacion = filiacionService.getOne(id_filiacion);
        mv.setViewName("redirect:/examen/lista?id_filiacion=" + id_filiacion);
        if (filiacion.isPresent()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String str = LocalDateTime.now().format(formatter);
            LocalDateTime dateTime = LocalDateTime.parse(str,formatter);
            // --- Examen ---
            filiacion.get().getPaciente().setEstado("No agendado");
            examen.setFiliacion(filiacion.get());
            examen.setFecha_examen(dateTime);
            examen.setVisto(false);
            examen.equals(diagnosis.sacaAutoDiagnosticoExamen(examen));
            examenService.save(examen);

                // --- envio mail ---
                if (examen.getEstado() > 3){
                    sendMailPaciente(filiacion.get());
                }

            attributes.addFlashAttribute("msg", "Examen Ingresado");
        }
        return mv;
    }

    public void sendMailPaciente(Filiacion filiacion){
        Mail mail = new Mail();
        mail.setTo(filiacion.getPaciente().getUsuario().getContacto().getCorreo());
        mail.setFrom("Soporte.Sistema.Inteligente@mail.com");
        mail.setSubject(" -- Examen alterado -- ");
        mail.setBody("Estimado: " + filiacion.getPaciente().getUsuario().getOnlyName() + ", su examen fue ingresado con exito! \n" +
                "Esperando que tenga un buen dia, acudiremos a usted con un cita médica a la brevedad. " +
                "Debido a un resultado de su examen sanguineo que se vió alterado.");
        sendMailService.sendMail(mail);
    }


    @GetMapping("/detalle")
    public ModelAndView detalle(
            @RequestParam("id_filiacion") long id_filiacion,
            @RequestParam("id_examen") long id_examen) {
        ModelAndView mv = new ModelAndView();
        Optional<Filiacion> filiacion = filiacionService.getOne(id_filiacion);
        mv.setViewName("/filiacion/examen/detalle");
        mv.addObject("filiacion",filiacion.get());
        if (examenService.existsById(id_examen)) {
            Optional<Examen> optional = examenService.getOne(id_examen);
            mv.addObject("examen", optional.get());
        }
        return mv;
    }

    @GetMapping("/eliminar")
    public ModelAndView delete(
            @RequestParam("id_filiacion") long id_filiacion,
            @RequestParam("id_examen") long id_examen,
            RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/examen/lista?id_filiacion=" + id_filiacion);
        if (examenService.existsById(id_examen)) {
            examenService.delete(id_examen);
            attributes.addFlashAttribute("msg", "Examen eliminado");
            return mv;
        }
        attributes.addFlashAttribute("msg", "Examen no eliminado");
        return mv;
    }


    @GetMapping("/simulation")
    public ModelAndView simulation(@RequestParam("id_filiacion") long id_filiacion){
        ModelAndView mv = new ModelAndView();
        UtilSimulation utilSimulation = new UtilSimulation();
        Optional<Filiacion> optional = filiacionService.getOne(id_filiacion);
        if(optional.isPresent()){
            for( int i = 0; i<10; i++){
                System.out.println("estoy en controller ->"+optional.get().toString());
                examenService.save(utilSimulation.setInyecExamenes(optional.get()));
            }
        }
        mv.setViewName("redirect:/examen/lista?id_filiacion=" + id_filiacion);
        return mv;
    }

    @GetMapping("/critico")
    public ModelAndView criticList() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/paciente/criticos");
        List<Examen> examenes = examenService.getCriticList(3,false,"No agendado");
        for (Examen examen:examenes) {
            System.out.println(examen.toString());
        }
        mv.addObject("examenes", examenes);
        return mv;
    }

    @GetMapping("/reporte")
    public ModelAndView myEvolution(@RequestParam("id_filiacion") long id_filiacion){
        ModelAndView mv = new ModelAndView();
        ArrayList<LocalDate> v_date_crea = new ArrayList<>();
        ArrayList<Double> v_crea = new ArrayList<>();
        mv.setViewName("/filiacion/examen/reporte");
        Optional<Filiacion> filiacion = filiacionService.getOne(id_filiacion);

        if(filiacion.isPresent()){
            List<Examen> examenList = examenService.listForGrafic(id_filiacion);
            if (!examenList.isEmpty()){
                for (Examen examen: examenList) {
                    v_date_crea.add(examen.getFecha_examen().toLocalDate());
                    v_crea.add(examen.getCreatinina());
                }
                mv.addObject("examen",examenList.get(examenList.size()-1));
                mv.addObject("v_date_crea",v_date_crea);
                mv.addObject("v_crea",v_crea);
            }
            mv.addObject("filiacion",filiacion.get());
        }
        return mv;
    }
}
