package com.sirere.sistema_registro_renal.entity;

import com.sirere.sistema_registro_renal.enums.EspecilidadEnum;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "especialidad")
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private EspecilidadEnum especialidadEnum;

    public Especialidad() {
    }

    public Especialidad(EspecilidadEnum especilidadEnum) {
        this.especialidadEnum = especilidadEnum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EspecilidadEnum getEspecilidadNombre() {
        return especialidadEnum;
    }

    public void setEspecilidadNombre(EspecilidadEnum especilidadEnum) {
        this.especialidadEnum = especilidadEnum;
    }

    @Override
    public String toString() {
        return getEspecilidadNombre().toString();
    }
}

