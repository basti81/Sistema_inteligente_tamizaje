package com.sirere.sistema_registro_renal.entity;

import com.sirere.sistema_registro_renal.enums.RolEnum;
import com.sirere.sistema_registro_renal.services.EspecialidadService;
import com.sirere.sistema_registro_renal.services.RolService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name ="usuario")
public  class Usuario  {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    @NotNull
    @Column(unique = true)
    private String rut;
    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    private String password;
    private String img;
    private boolean habilitado;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="usu_rol",joinColumns = @JoinColumn (name = "id_usuario"),inverseJoinColumns =  @JoinColumn(name="id_rol"))
    private Set<Rol> roles = new HashSet<>();

    @OneToOne(mappedBy = "usuario",cascade=CascadeType.ALL,fetch = FetchType.LAZY,optional = false)
    private Paciente paciente;

    @OneToOne(mappedBy = "usuario",cascade=CascadeType.ALL,fetch = FetchType.LAZY,optional = false)
    private Personal personal;

    @OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Contacto contacto;

    private LocalDate fecha_ingreso;


    public Usuario() {
    }

    public Usuario(String rut, String nombre, String apellido, String password, Set<Rol> roles, LocalDate fecha_ingreso) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.roles = roles;
        this.fecha_ingreso = fecha_ingreso;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getOnlyName(){
        String[] newName = nombre.trim().split("\\s+");
        String[] newLastName = apellido.trim().split("\\s+");
        return newName[0]+" "+newLastName[0];
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public LocalDate getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        if(paciente == null){
            if(this.paciente !=null){
                this.paciente.setUsuario(null);
            }
        }else{
            paciente.setUsuario(this);
        }
        this.paciente = paciente;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        if(personal == null){
            if(this.personal !=null){
                this.personal.setUsuario(null);
            }
        }else{
            personal.setUsuario(this);
        }
        this.personal = personal;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        if(contacto == null){
            if(this.contacto !=null){
                this.contacto.setUsuario(null);
            }
        }else{
            contacto.setUsuario(this);
        }
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", rut='" + rut + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", password='" + password + '\'' +
                ", contacto=" + contacto +
                ", fecha_ingreso=" + fecha_ingreso +
                '}';
    }

}
