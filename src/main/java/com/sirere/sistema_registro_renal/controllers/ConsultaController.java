package com.sirere.sistema_registro_renal.controllers;

import com.sirere.sistema_registro_renal.entity.*;
import com.sirere.sistema_registro_renal.services.*;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PersonalService personalService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private ConsultaService consultaService;
    @Autowired
    private ExamenService examenService;
    @Autowired
    private FiliacionService filiacionService;
    @Autowired
    private SignosService signosService;
    @Autowired
    private SendMailService sendMailService;
    @Autowired
    private AntropometriaService antropometriaService;


    @GetMapping("/lista")
    public ModelAndView lista() {
        ModelAndView mv = new ModelAndView();
        List<Consulta> consultas = consultaService.consultaGes(false);
        mv.setViewName("/consulta/lista");
        mv.addObject("consultas", consultas);
        return mv;
    }


    @GetMapping("/misConsultas")
    public ModelAndView myConsultas(@RequestParam(value = "id_paciente", required = false) Long id_paciente,
                                    @RequestParam(value = "id_personal", required = false) Long id_personal) {
        ModelAndView mv = new ModelAndView();
        System.out.println("id Personal -> " + id_personal);
        if (id_paciente != null){
            Optional<Paciente> optional = pacienteService.getOne(id_paciente);
            List<Consulta> consultas = consultaService.consultaPacientes(optional.get().getId_paciente());
            mv.setViewName("/paciente/consulta/lista");
            mv.addObject("paciente", optional.get());
            mv.addObject("consultas", consultas);
            return mv;
        }
        if (id_personal != null){
            Optional<Personal> optional = personalService.getOne(id_personal);
            List<Examen> examenes = examenService.getCriticListAlert(3,false,"No agendado");
            List<Consulta> consultas = consultaService.consultaPersonal(optional.get().getId_personal(),false);
            mv.addObject("examenes", examenes);
            mv.addObject("countList",examenes.size());
            mv.addObject("consultas", consultas);
            mv.setViewName("/personal/consulta/lista");
            return mv;
        }
        mv.setViewName("/index");
        return mv;
    }
    @GetMapping("/nuevo")
    public ModelAndView nuevo(@RequestParam("id_paciente") long id_paciente, Consulta consulta) {
        ModelAndView mv = new ModelAndView();
        Optional<Paciente> paciente = pacienteService.getOne(id_paciente);
        List<Usuario> list_doctor = personalService.listDoctor();
        mv.setViewName("/consulta/nuevo");
        if(paciente.isPresent()){
            mv.addObject("paciente", paciente.get());
        }
        mv.addObject("consulta", consulta);
        mv.addObject("list_doctor", list_doctor);
        return mv;
    }

    @GetMapping("/eliminar")
    public ModelAndView delete(
            @RequestParam("id_paciente") long id_paciente,
            @RequestParam("id_consulta") long id_consulta,
            RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/consulta/misConsultas?id_paciente=" + id_paciente);
        if (consultaService.existsById(id_consulta)){
            consultaService.delete(id_consulta);
            attributes.addFlashAttribute("msg", "Consulta Eliminada");
            return mv;}
        attributes.addFlashAttribute("msg", "Consulta No Encontrada");
        return mv;
    }

    @GetMapping("/actualizar")
    public ModelAndView update(@RequestParam("id_consulta") long id_consulta) {
        ModelAndView mv = new ModelAndView();
        if (consultaService.existsById(id_consulta)){
            Optional<Consulta> optional = consultaService.getOne(id_consulta);
            mv.setViewName("/filiacion/consulta/nuevo");
            mv.addObject("paciente",optional.get().getPaciente());
            mv.addObject("consulta", optional.get());
            return mv;}
        mv.setViewName("/consu/lista");
        return mv;
    }

    @GetMapping("/detalle")
    public ModelAndView detalle(@RequestParam("id_paciente") long id_paciente,
                                @RequestParam("id_consulta") long id_consulta,
                                RedirectAttributes attributes){
        ArrayList<LocalDate> v_date_crea = new ArrayList<>();
        ArrayList<LocalDate> v_date_signo = new ArrayList<>();
        ArrayList<Double> v_crea = new ArrayList<>();
        ArrayList<Integer> v_signo_pa = new ArrayList<>();
        ArrayList<Integer> v_signo_pb = new ArrayList<>();
        ModelAndView mv = new ModelAndView();

        Optional<Paciente> paciente = pacienteService.getOne(id_paciente);
        Optional<Consulta> consulta = consultaService.getOne(id_consulta);
        List<Consulta> listConsulta = consultaService.consultaPacientes(id_paciente);
        mv.setViewName("/consulta/detalle");

        if(!paciente.isPresent()){
            attributes.addFlashAttribute("msg", "Paciente no encontrado");
            return mv;
        }
        // -- Examenes --
        List<Examen> examns = examenService.listForGrafic(paciente.get().getFiliacion().getId_filiacion());
        if(examns.isEmpty()){
            Examen examen = new Examen();
            examen.setFiliacion(paciente.get().getFiliacion());
            mv.addObject("examen", examen);
        }else{
            mv.addObject("examen",examns.get(examns.size()-1));
            for (Examen examen: examns) {
                v_date_crea.add(examen.getFecha_examen().toLocalDate());
                v_crea.add(examen.getCreatinina());
            }
        }
        // -- Signo Vitales --
        List<SignoVital> signoVitals = signosService.listForGrafic(paciente.get().getFiliacion().getId_filiacion());
        if(signoVitals.isEmpty()){
            SignoVital signoVital = new SignoVital();
            signoVital.setFiliacion(paciente.get().getFiliacion());
            mv.addObject("signo", signoVital);
        }else{
            mv.addObject("signo",signoVitals.get(signoVitals.size()-1));
            for (SignoVital signo: signoVitals) {
                v_date_signo.add(signo.getFecha_signo());
                v_signo_pa.add(signo.getP_alta());
                v_signo_pb.add(signo.getP_baja());
            }
        }


        List<Antropometria> antropometriaList = antropometriaService.myAntropometrys(paciente.get().getFiliacion().getId_filiacion());
        if(antropometriaList.isEmpty()){
            Antropometria antropometria = new Antropometria();
            mv.addObject("antropometria",antropometria);
        }else{
            mv.addObject("antropometria",antropometriaList.get(antropometriaList.size()-1));
        }
        if(consulta.isPresent()){
            mv.addObject("paciente",paciente.get());
            mv.addObject("consulta",consulta.get());
            mv.addObject("consultas",listConsulta);
            //---------------- Graficos ----------------
            mv.addObject("v_date_crea",v_date_crea);
            mv.addObject("v_crea",v_crea);
            mv.addObject("v_date_signo",v_date_signo);
            mv.addObject("v_signo_pa",v_signo_pa);
            mv.addObject("v_signo_pb",v_signo_pb);
        }
        return mv;
    }

    @PostMapping("/agendar")
    public ModelAndView agendar(
            @RequestParam(value = "id_paciente", required = true) long id_paciente,
            Consulta consulta, BindingResult result, RedirectAttributes attributes
    ) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            mv.setViewName("consulta/nuevo");
            mv.addObject("error", "Consulta Medica No ingresada");
            return mv;
        }
        // --- estado paciente ---
        Optional<Paciente> paciente = pacienteService.getOne(id_paciente);
        paciente.get().setEstado("Agendado");
        consulta.setPaciente(paciente.get());
        Optional<Personal> personal = personalService.getOne(consulta.getPersonal().getId_personal());
        consulta.setPersonal(personal.get());

        // --- consulta ---
        consulta.setVisto(false);
        consulta.setFecha_consulta(LocalDate.now());
        consultaService.save(consulta);
            // --- envio mail ---
            Mail mail = new Mail();
            mail.setTo(paciente.get().getUsuario().getContacto().getCorreo());
            mail.setFrom("Soporte.Sistema.Inteligente@mail.com");
            mail.setSubject(" -- Cita Medica Agendada -- ");
            mail.setBody("Estimado: " + paciente.get().getUsuario().getOnlyName() + ", su cita medica fue agendada con exito! \n" +
                    "Fecha Atencion: "+ consulta.getFecha_atencion() +"\n" +
                    "Con: "+ personal.get().getUsuario().getOnlyName() + "\n" +
                    "Se ruega llegar con una hora de anticipación");
            sendMailService.sendMail(mail);

        attributes.addFlashAttribute("msg", "Consulta Ingresada");
        mv.setViewName("redirect:/consulta/misConsultas?id_paciente=" + id_paciente);
        return mv;
    }

    @PostMapping("/consulta")
    public ModelAndView consulta(
            @RequestParam(value = "id_paciente", required = true) long id_paciente,
            @RequestParam(value = "id_personal", required = true) long id_personal,
            Consulta consulta, BindingResult result, RedirectAttributes attributes
    ) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            mv.setViewName("consulta/nuevo");
            mv.addObject("error", "Consulta Medica No ingresada");
            return mv;
        }
        // --- consulta ---
        consulta.setPaciente(pacienteService.getOne(id_paciente).get());
        consulta.getPaciente().setEstado("Atendido");
        consulta.setPersonal(personalService.getOne(id_personal).get());
        consulta.setVisto(true);
        consultaService.save(consulta);
        mv.setViewName("redirect:/consulta/detalle?id_paciente=" + id_paciente + "&id_consulta=" + consulta.getId_consulta());
        Optional<Consulta> optional = consultaService.getOne(consulta.getId_consulta());
        if (optional.isPresent()){
            System.out.println("entre a consulta para guardar");
            attributes.addFlashAttribute("msg", "Cambios guardados y consulta finalizada");
            return mv;
        }
        attributes.addFlashAttribute("msg", "Cambios no guardados");
        return mv;
    }

}
