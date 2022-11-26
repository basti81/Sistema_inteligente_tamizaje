package com.sirere.sistema_registro_renal.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "signo_vital")
public class SignoVital {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_signo;
    private Integer p_alta;
    private Integer p_baja;
    private Integer respiracion;
    private Double temperatura;
    private Integer pulso;
    private Double saturacion;
    private LocalDate fecha_signo;
    private Boolean visto;
    @ManyToOne
    @JoinColumn(name = "id_filiacion",  nullable = false)
    private Filiacion filiacion;

    public SignoVital() {
    }

    public Long getId_signo() {
        return id_signo;
    }

    public void setId_signo(Long id_signo) {
        this.id_signo = id_signo;
    }

    public Integer getP_alta() {
        return p_alta;
    }

    public void setP_alta(Integer p_alta) {
        this.p_alta = p_alta;
    }

    public Integer getP_baja() {
        return p_baja;
    }

    public void setP_baja(Integer p_baja) {
        this.p_baja = p_baja;
    }

    public Integer getRespiracion() {
        return respiracion;
    }

    public void setRespiracion(Integer respiracion) {
        this.respiracion = respiracion;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Integer getPulso() {
        return pulso;
    }

    public void setPulso(Integer pulso) {
        this.pulso = pulso;
    }

    public Double getSaturacion() {
        return saturacion;
    }

    public void setSaturacion(Double saturacion) {
        this.saturacion = saturacion;
    }

    public LocalDate getFecha_signo() {
        return fecha_signo;
    }

    public void setFecha_signo(LocalDate fecha_signo) {
        this.fecha_signo = fecha_signo;
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
}
