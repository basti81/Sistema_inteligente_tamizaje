package com.sirere.sistema_registro_renal.util;

import com.sirere.sistema_registro_renal.entity.*;

import com.sirere.sistema_registro_renal.enums.EspecilidadEnum;
import com.sirere.sistema_registro_renal.enums.RolEnum;
import com.sirere.sistema_registro_renal.services.EspecialidadService;
import com.sirere.sistema_registro_renal.services.PersonalService;
import com.sirere.sistema_registro_renal.services.RolService;
import com.sirere.sistema_registro_renal.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LoadData implements CommandLineRunner {

    @Autowired
    private RolService rolService;

    @Autowired
    private EspecialidadService especialidadService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonalService personalService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void run(String... args) throws Exception {
//        getRoles();
//        getEspecialidad();
//        getTestUser();
    }

    private void getRoles(){
        List<Rol> rols = new ArrayList<>();
        rols.add(new Rol(RolEnum.ADMINISTRADOR));
        rols.add(new Rol(RolEnum.CLIENTE));
        rols.add(new Rol(RolEnum.DOCTOR));
        rols.add(new Rol(RolEnum.ENFERMERIA));
        rols.add(new Rol(RolEnum.ASISTENTE));
        rols.add(new Rol(RolEnum.LABORATORISTA));
        rols.add(new Rol(RolEnum.ESTADISTICAS));
        rols.add(new Rol(RolEnum.TENS));

        for(Rol rol: rols){
            rolService.save(rol);
        }
    }

    private void getEspecialidad(){
        List<Especialidad> especialidads = new ArrayList<>();
        especialidads.add(new Especialidad(EspecilidadEnum.NINGUNA));
        especialidads.add(new Especialidad(EspecilidadEnum.GENERAL));
        especialidads.add(new Especialidad(EspecilidadEnum.NEFROLOGIA));

        for (Especialidad especialidad : especialidads){
            especialidadService.save(especialidad);
        }
    }

    private void getTestUser() {

        //----- Personal - Admin -----
        Personal perAdmin = new Personal();
        Usuario userAdmin = new Usuario();
        Contacto contaAdmin = new Contacto();
        //Rol
        Rol rolAdmin = rolService.getByRolEnum(RolEnum.ADMINISTRADOR).get();
        Set<Rol> rol = new HashSet<>();
        rol.add(rolAdmin);
        //Contacto
        contaAdmin.setCorreo("administrador@mail.com");
        contaAdmin.setTelefono("+569 00000000");
        contaAdmin.setTelefono_aux("+569 00000001");
        //Usuario
        userAdmin.setRut("admin");
        userAdmin.setNombre("admin");
        userAdmin.setApellido("admin");
        userAdmin.setPassword(passwordEncoder.encode("admin"));
        userAdmin.setRoles(rol);
        userAdmin.setContacto(contaAdmin);
        userAdmin.setHabilitado(true);
        userAdmin.setPersonal(perAdmin);
        usuarioService.save(userAdmin);


        //---- Personal - doctor ----
        Personal perDoctor = new Personal();
        Usuario userDoctor = new Usuario();
        Contacto contaDoctor = new Contacto();
        //Rol
        Rol rolDoctor = rolService.getByRolEnum(RolEnum.DOCTOR).get();
        Set<Rol> rol2 = new HashSet<>();
        rol2.add(rolDoctor);
        //Contacto
        contaDoctor.setCorreo("doctor@mail.com");
        contaDoctor.setTelefono("+569 00000002");
        contaDoctor.setTelefono_aux("+569 00000003");
        //Usuario
        userDoctor.setRut("doctor");
        userDoctor.setNombre("doctor");
        userDoctor.setApellido("doctor");
        userDoctor.setHabilitado(true);
        userDoctor.setPassword(passwordEncoder.encode("doctor"));
        userDoctor.setRoles(rol2);
        userDoctor.setPersonal(perDoctor);
        userDoctor.setContacto(contaDoctor);
        userDoctor.setHabilitado(true);
        usuarioService.save(userDoctor);

        //---- Personal - Asistente ----
        Personal perAsitente = new Personal();
        Usuario userAsistente = new Usuario();
        Contacto contaAsistente = new Contacto();
        //Rol
        Rol rolGes = rolService.getByRolEnum(RolEnum.ASISTENTE).get();
        Set<Rol> rol4 = new HashSet<>();
        rol4.add(rolGes);
        //Contacto
        contaAsistente.setCorreo("asistente@mail.com");
        contaAsistente.setTelefono("+569 00000004");
        contaAsistente.setTelefono_aux("+569 00000005");
        //Usuario
        userAsistente.setRut("asistente");
        userAsistente.setNombre("asistente");
        userAsistente.setApellido("asistente");
        userAsistente.setPassword(passwordEncoder.encode("asistente"));
        userAsistente.setPersonal(perAsitente);
        userAsistente.setContacto(contaAsistente);
        userAsistente.setHabilitado(true);
        userAsistente.setRoles(rol4);
        usuarioService.save(userAsistente);

        //---- Personal - Laboratorista ----
        Personal perLabo = new Personal();
        Usuario userLabo = new Usuario();
        Contacto contaLabo = new Contacto();
        //Rol
        Rol rolLaboratorista = rolService.getByRolEnum(RolEnum.LABORATORISTA).get();
        Set<Rol> rol5 = new HashSet<>();
        rol5.add(rolLaboratorista);
        //Contacto
        contaLabo.setCorreo("laboratorista@gmail.com");
        contaLabo.setTelefono("+569 00000006");
        contaLabo.setTelefono_aux("+569 00000007");
        //Usuario
        userLabo.setRut("laboratorista");
        userLabo.setNombre("laboratorista");
        userLabo.setApellido("laboratorista");
        userLabo.setRoles(rol5);
        userLabo.setPassword(passwordEncoder.encode("laboratorista"));
        userLabo.setPersonal(perLabo);
        userLabo.setContacto(contaLabo);
        userLabo.setHabilitado(true);
        usuarioService.save(userLabo);



    }
}
