package com.sirere.sistema_registro_renal.entity;


public class Enfermedad {
    private String tipoEnfermedad;
    private Integer categoria;
    private String cura;

    public Enfermedad() {
    }

    public String getCura() {
        return cura;
    }

    public void setCura(String cura) {
        this.cura = cura;
    }

    public String getTipoEnfermedad() {
        return tipoEnfermedad;
    }

    public void setTipoEnfermedad(String tipoEnfermedad) {
        this.tipoEnfermedad = tipoEnfermedad;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Enfermedad{" +
                "tipoEnfermedad='" + tipoEnfermedad + '\'' +
                ", categoria=" + categoria +
                ", cura='" + cura + '\'' +
                '}';
    }
}
