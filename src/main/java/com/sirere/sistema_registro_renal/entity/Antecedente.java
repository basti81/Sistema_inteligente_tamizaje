package com.sirere.sistema_registro_renal.entity;

import com.sirere.sistema_registro_renal.enums.AntecendeteEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="antecedente")
public class Antecedente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_antecedente;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AntecendeteEnum antecendeteEnum;

    public Antecedente() {
    }

    public Antecedente(AntecendeteEnum antecendeteEnum) {
        this.antecendeteEnum = antecendeteEnum;
    }

    public Integer getId_antecedente() {
        return id_antecedente;
    }

    public void setId_antecedente(Integer id_antecedente) {
        this.id_antecedente = id_antecedente;
    }

    public AntecendeteEnum getAntecendeteNombre() {
        return antecendeteEnum;
    }

    public void setAntecendeteNombre(AntecendeteEnum antecendeteEnum) {
        this.antecendeteEnum = antecendeteEnum;
    }

    @Override
    public String toString() {
        return "Antecedente{" +
                "id_antecedente=" + id_antecedente +
                ", antecendeteNombre=" + antecendeteEnum +
                '}';
    }
}
