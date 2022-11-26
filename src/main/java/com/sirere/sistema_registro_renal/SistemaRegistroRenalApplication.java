package com.sirere.sistema_registro_renal;

import com.sirere.sistema_registro_renal.entity.Filiacion;
import com.sirere.sistema_registro_renal.entity.Paciente;
//import com.sirere.sistema_registro_renal.services.FiliacionService;
import com.sirere.sistema_registro_renal.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


//@SpringBootApplication
//public class SistemaRegistroRenalApplication implements CommandLineRunner{


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SistemaRegistroRenalApplication{

//	@Autowired
//	PacienteService pacienteService;

//	@Autowired
//	FiliacionService filiacionService;

	public static void main(String[] args) {
		SpringApplication.run(SistemaRegistroRenalApplication.class, args);
	}


	public void run(String... args) throws Exception {
	}


}
