package com.sirere.sistema_registro_renal.entity;

import javax.persistence.*;
import java.time.LocalDate;
//
//@Entity
//@Table(name="diagnostico_examen")
public class aDiagnosticoExamen {

//    @Id
    private Long id_aDiagnosticoExamen;
    private String estado;
    private String descripcion;
    private LocalDate fecha;
    private Boolean visto;
//    @MapsId
//    @OneToOne(fetch = FetchType.EAGER)
//    private Examen examen;

    public aDiagnosticoExamen() {
    }

    public Long getId_aDiagnosticoExamen() {
        return id_aDiagnosticoExamen;
    }

    public void setId_aDiagnosticoExamen(Long id_aDiagnosticoExamen) {
        this.id_aDiagnosticoExamen = id_aDiagnosticoExamen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Boolean getVisto() {
        return visto;
    }

    public void setVisto(Boolean visto) {
        this.visto = visto;
    }


}
