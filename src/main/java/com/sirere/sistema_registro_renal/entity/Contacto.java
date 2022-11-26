package com.sirere.sistema_registro_renal.entity;

import javax.persistence.*;

@Entity
@Table(name="contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_contacto;
    private String telefono;
    private String telefono_aux;
    private String correo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Contacto() {
    }


    public Integer getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(int id_contacto) {
        this.id_contacto = id_contacto;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono_aux() {
        return telefono_aux;
    }

    public void setTelefono_aux(String telefono_aux) {
        this.telefono_aux = telefono_aux;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setId_contacto(Integer id_contacto) {
        this.id_contacto = id_contacto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "id_contacto=" + id_contacto +
                ", telefono='" + telefono + '\'' +
                ", telefono_aux='" + telefono_aux + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
