package com.sirere.sistema_registro_renal.controllers;

import com.sirere.sistema_registro_renal.biblioteca.Diagnosis;
import com.sirere.sistema_registro_renal.entity.Examen;
import com.sirere.sistema_registro_renal.entity.SignoVital;
import com.sirere.sistema_registro_renal.entity.Usuario;
import com.sirere.sistema_registro_renal.services.ExamenService;
import com.sirere.sistema_registro_renal.services.FiliacionService;
import com.sirere.sistema_registro_renal.services.SignosService;
import com.sirere.sistema_registro_renal.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.SocketHandler;

@Controller
@RequestMapping("/reporte")
public class ReportesController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ExamenService examenService;
    @Autowired
    private FiliacionService filiacionService;
    @Autowired
    private SignosService signosService;
    private Diagnosis diagnosis = new Diagnosis();

    @GetMapping("/detalle")
    public ModelAndView myEvolution(@RequestParam("id_filiacion") long id_filiacion){
        ModelAndView mv = new ModelAndView();
        ArrayList<LocalDate> v_date_crea = new ArrayList<>();
        ArrayList<LocalDate> v_date_signo = new ArrayList<>();
        ArrayList<Double> v_crea = new ArrayList<>();
        ArrayList<Integer> v_signo_pa = new ArrayList<>();
        ArrayList<Integer> v_signo_pb = new ArrayList<>();

        Optional<Usuario> optional = usuarioService.findUsuarioByFiliacion(id_filiacion);
        if (optional.isPresent()){
            List<Examen> examns = examenService.listForGrafic(id_filiacion);
            for (Examen examen: examns) {
                v_date_crea.add(examen.getFecha_examen().toLocalDate());
                v_crea.add(examen.getCreatinina());
            }
            List<SignoVital> signoVitals = signosService.listForGrafic(id_filiacion);
            for (SignoVital signo: signoVitals) {
                v_date_signo.add(signo.getFecha_signo());
                v_signo_pa.add(signo.getP_alta());
                v_signo_pb.add(signo.getP_baja());
            }

        }
        mv.addObject("usuario",optional.get());
        mv.addObject("v_date_crea",v_date_crea);
        mv.addObject("v_crea",v_crea);

        mv.addObject("v_date_signo",v_date_signo);
        mv.addObject("v_signo_pa",v_signo_pa);
        mv.addObject("v_signo_pb",v_signo_pb);
        mv.setViewName("/filiacion/reporte/detalle");
        return mv;
    }
}
