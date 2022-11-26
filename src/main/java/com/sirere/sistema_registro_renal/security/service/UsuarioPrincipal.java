package com.sirere.sistema_registro_renal.security.service;

import com.sirere.sistema_registro_renal.entity.Paciente;
import com.sirere.sistema_registro_renal.entity.Personal;
import com.sirere.sistema_registro_renal.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioPrincipal implements UserDetails {

    private Long id_usuario;
    private String rut;
    private String nombre;
    private String apellido;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Personal personal;
    private Paciente paciente;

        public UsuarioPrincipal(Long id_usuario, String rut, String nombre, String apellido, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id_usuario = id_usuario;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.authorities = authorities;
    }

    public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getId_usuario(), usuario.getRut(),usuario.getNombre(),usuario.getApellido(), usuario.getPassword(),authorities);
//        return new UsuarioPrincipal(usuario.getId_usuario(), usuario.getRut(),usuario.getNombre(),usuario.getApellido(), usuario.getPassword(),authorities, usuario.getPersonal(), usuario.getPaciente());
//
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return rut;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public String getNombre() {
        String[] newName = nombre.trim().split("\\s+");
        String[] newLastName = apellido.trim().split("\\s+");

       return newName[0]+" "+newLastName[0];
    }

    public String getRut() {
        return rut;
    }
    public String getPass() {return password;}
    @Override
    public String toString() {
        return "UsuarioPrincipal{" +
                "id_usuario=" + id_usuario +
                ", rut='" + rut + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
