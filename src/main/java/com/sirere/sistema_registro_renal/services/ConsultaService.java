package com.sirere.sistema_registro_renal.services;

import com.sirere.sistema_registro_renal.biblioteca.Formato;
import com.sirere.sistema_registro_renal.entity.Consulta;
import com.sirere.sistema_registro_renal.repository.ConsultaRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRespository consultaRespository;
    Formato formato = new Formato();

    public void save(Consulta consulta){
        consultaRespository.save(consulta);
    }
    public Optional<Consulta> getOne(long id_consulta){ return consultaRespository.findById(id_consulta);}
    public List<Consulta> list(){
        return consultaRespository.findAll();
    }

    public List<Consulta> consultaPacientes(Long id_paciente){
        return consultaRespository.consultaPacientes(id_paciente);
    }
    public List<Consulta> consultaPersonal(Long id_personal, boolean visto){
        return consultaRespository.consultaPersonal(id_personal,visto);
    }
    public List<Consulta> consultaGes(boolean visto){
        return consultaRespository.consultaGes(visto);
    }


    public boolean existsById(Long id_consulta){
        return consultaRespository.existsById(id_consulta);
    }

    public void delete(Long id_consulta) {
        consultaRespository.deleteById(id_consulta);
    }


}
