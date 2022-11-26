package com.sirere.sistema_registro_renal.security.service;

import com.sirere.sistema_registro_renal.entity.Rol;
import com.sirere.sistema_registro_renal.entity.Usuario;
import com.sirere.sistema_registro_renal.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String rut) throws UsernameNotFoundException {
        System.out.println("INGRESAR A LOAD USER BY USERNAME");
        Usuario usuario = usuarioService.findUsuarioByRut(rut).orElseThrow(()-> new UsernameNotFoundException(rut));
        System.out.println(usuario.getRut());
        System.out.println(usuario.getNombre() + " "+ usuario.getApellido());
        return UsuarioPrincipal.build(usuario);
    }

}
