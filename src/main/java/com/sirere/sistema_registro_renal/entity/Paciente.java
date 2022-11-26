package com.sirere.sistema_registro_renal.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
@Table (name = "paciente")
public class Paciente{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_paciente;

    @Column(name = "region", columnDefinition = "varchar(100)")
    private String region;

    @Column(name = "comuna", columnDefinition = "varchar(100)")
    private String comuna;

    @Column(name = "direccion", columnDefinition = "varchar(100)")
    private String direccion;

    @Column(name = "prevision", columnDefinition = "varchar(50)")
    private String prevision;

    @Column(name = "estado", columnDefinition = "varchar(50)")
    private String estado;

    @OneToOne(mappedBy = "paciente",cascade=CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
    private Filiacion filiacion;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;


    @OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Consulta> consultas;


    public Paciente() {
    }



    public Long getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(long id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPrevision() {
        return prevision;
    }

    public void setPrevision(String prevision) {
        this.prevision = prevision;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Filiacion getFiliacion() {
        return filiacion;
    }

    public void setFiliacion(Filiacion filiacion) {
        if(filiacion == null){
            if(this.filiacion != null){
                this.filiacion.setPaciente(null);
            }
        }else{
            filiacion.setPaciente(this);
        }
        this.filiacion = filiacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public void setId_paciente(Long id_paciente) {
        this.id_paciente = id_paciente;
    }


    @Override
    public String toString() {
        return "Paciente{" +
                "id_paciente=" + id_paciente +
                ", region='" + region + '\'' +
                ", comuna='" + comuna + '\'' +
                ", direccion='" + direccion + '\'' +
                ", prevision='" + prevision + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
