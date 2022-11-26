package com.sirere.sistema_registro_renal.entity;

import com.sirere.sistema_registro_renal.enums.RolEnum;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "rol")
public class Rol {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RolEnum rolEnum;

    public Rol() {
    }

    public Rol(RolEnum rolEnum) {
        this.rolEnum = rolEnum;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RolEnum getRolNombre() {
        return rolEnum;
    }

    public void setRolNombre(RolEnum rolEnum) {
        this.rolEnum = rolEnum;
    }

    @Override
    public String toString() {
        return getRolNombre().toString();
    }
}
