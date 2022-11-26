package com.sirere.sistema_registro_renal.entity;

import com.sirere.sistema_registro_renal.enums.farmaco.FarmacoEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name="farmaco")
public class Farmaco {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_farmaco;
    @NotNull
    @Enumerated(EnumType.STRING)
    private FarmacoEnum farmacoEnum;
    private String dosis;
    private String frecuencia;
    private Integer cantidad;
    private LocalDate fecInicio;
    private LocalDate fecFin;
    private LocalDate fecha_farmaco;

    public Farmaco() {
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Farmaco(FarmacoEnum farmacoEnum) {
        this.farmacoEnum = farmacoEnum;
    }

    public Integer getId_farmaco() {
        return id_farmaco;
    }

    public void setId_farmaco(Integer id_farmaco) {
        this.id_farmaco = id_farmaco;
    }

    public FarmacoEnum getFarmacoEnum() {
        return farmacoEnum;
    }

    public void setFarmacoEnum(FarmacoEnum farmacoEnum) {
        this.farmacoEnum = farmacoEnum;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public LocalDate getFecInicio() {
        return fecInicio;
    }

    public void setFecInicio(LocalDate fecInicio) {
        this.fecInicio = fecInicio;
    }

    public LocalDate getFecFin() {
        return fecFin;
    }

    public void setFecFin(LocalDate fecFin) {
        this.fecFin = fecFin;
    }

    public LocalDate getFecha_farmaco() {
        return fecha_farmaco;
    }

    public void setFecha_farmaco(LocalDate fecha_farmaco) {
        this.fecha_farmaco = fecha_farmaco;
    }

    @Override
    public String toString() {
        return "Farmaco{" +
                "id_farmaco=" + id_farmaco +
                ", farmacoEnum=" + farmacoEnum +
                ", dosis='" + dosis + '\'' +
                ", frecuencia='" + frecuencia + '\'' +
                ", fecInicio=" + fecInicio +
                ", fecFin=" + fecFin +
                ", fecha_farmaco=" + fecha_farmaco +
                '}';
    }
}
