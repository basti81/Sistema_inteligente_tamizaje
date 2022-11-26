package com.sirere.sistema_registro_renal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "examen")
public class Examen {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_examen;
    private Double creatinina;
    private Double albumina;
    private Double ma;
    private Double clCr;
    private Double rac;
    private Double cloro;
    private Double potasio;
    private Double sodio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fecha_examen;
    private Integer estado;
    private Boolean visto;
    @OneToOne(mappedBy = "examen",cascade=CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
    private AutoDiagnostico autoDiagnostico;
    @ManyToOne
    @JoinColumn(name = "id_filiacion",  nullable = false)
    private Filiacion filiacion;

    public Examen() {
    }

    public Examen(AutoDiagnostico autoDiagnostico) {
        this.autoDiagnostico = autoDiagnostico;
    }

    public Double getMa() {
        return ma;
    }

    public void setMa(Double ma) {
        this.ma = ma;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Double getClCr() {
        return clCr;
    }

    public void setClCr(Double clCr) {
        this.clCr = clCr;
    }

    public Double getRac() {
        return rac;
    }

    public void setRac(Double rac) {
        this.rac = rac;
    }

    public Long getId_examen() {
        return id_examen;
    }

    public void setId_examen(Long id_examen) {
        this.id_examen = id_examen;
    }

    public Boolean getVisto() {
        return visto;
    }

    public void setVisto(Boolean visto) {
        this.visto = visto;
    }

    public Filiacion getFiliacion() {
        return filiacion;
    }

    public void setFiliacion(Filiacion filiacion) {
        this.filiacion = filiacion;
    }

    public Double getCreatinina() {
        return creatinina;
    }

    public void setCreatinina(Double creatinina) {
        this.creatinina = creatinina;
    }

    public Double getCloro() {
        return cloro;
    }

    public void setCloro(Double cloro) {
        this.cloro = cloro;
    }

    public Double getAlbumina() {
        return albumina;
    }

    public void setAlbumina(Double albumina) {
        this.albumina = albumina;
    }

    public Double getPotasio() {
        return potasio;
    }

    public void setPotasio(Double potasio) {
        this.potasio = potasio;
    }

    public Double getSodio() {
        return sodio;
    }

    public void setSodio(Double sodio) {
        this.sodio = sodio;
    }

    public LocalDateTime getFecha_examen() {
        return fecha_examen;
    }

    public void setFecha_examen(LocalDateTime fecha_examen) {
        this.fecha_examen = fecha_examen;
    }

    public Boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }

    public AutoDiagnostico getAutoDiagnostico() {
        return autoDiagnostico;
    }

    public void setAutoDiagnostico(AutoDiagnostico autoDiagnostico) {
        if(autoDiagnostico == null){
            if(this.autoDiagnostico !=null){
                this.autoDiagnostico.setExamen(null);
            }
        }else{
            autoDiagnostico.setExamen(this);
        }
        this.autoDiagnostico = autoDiagnostico;
    }

    @Override
    public String toString() {
        return "Examen{" +
                "id_examen=" + id_examen +
                ", creatinina=" + creatinina +
                ", albumina=" + albumina +
                ", ma=" + ma +
                ", clCr=" + clCr +
                ", rac=" + rac +
                ", cloro=" + cloro +
                ", potasio=" + potasio +
                ", sodio=" + sodio +
                ", fecha_examen=" + fecha_examen +
                ", estado=" + estado +
                ", visto=" + visto +
                '}';
    }
}
