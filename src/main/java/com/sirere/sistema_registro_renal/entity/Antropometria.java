package com.sirere.sistema_registro_renal.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "antropometria")
public class Antropometria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_antropometria;
    private Double altura;
    private Double peso;
    private Double imc;
    private LocalDate fecha_antropometria;
    @ManyToOne
    @JoinColumn(name = "id_filiacion",  nullable = false)
    private Filiacion filiacion;

    public Antropometria() {
    }

    public Long getId_antropometria() {
        return id_antropometria;
    }

    public void setId_antropometria(Long id_antropometria) {
        this.id_antropometria = id_antropometria;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getImc() {
        return imc;
    }

    public void setImc(Double imc) {
        this.imc = imc;
    }

    public LocalDate getFecha_antropometria() {
        return fecha_antropometria;
    }

    public void setFecha_antropometria(LocalDate fecha_antropometria) {
        this.fecha_antropometria = fecha_antropometria;
    }

    public Filiacion getFiliacion() {
        return filiacion;
    }

    public void setFiliacion(Filiacion filiacion) {
        this.filiacion = filiacion;
    }
}
