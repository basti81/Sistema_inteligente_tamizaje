package com.sirere.sistema_registro_renal.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "autoDiagnostico")
public class AutoDiagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_autoDiagnostico;
    private double fg;
    private String estadio;
    @Column(columnDefinition="TEXT")
    private String descripcion;
    private LocalDate fecha;
    private Boolean visto;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_examen")
    private Examen examen;

    public AutoDiagnostico() {
    }

    public Long getId_autoDiagnostico() {
        return id_autoDiagnostico;
    }

    public void setId_autoDiagnostico(Long id_autoDiagnostico) {
        this.id_autoDiagnostico = id_autoDiagnostico;
    }

    public double getFg() {
        return fg;
    }

    public void setFg(double fg) {
        this.fg = fg;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
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

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    @Override
    public String toString() {
        return "AutoDiagnostico{" +
                "id_autoDiagnostico=" + id_autoDiagnostico +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", visto=" + visto +
                '}';
    }
}
