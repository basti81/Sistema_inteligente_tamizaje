package com.sirere.sistema_registro_renal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sirere.sistema_registro_renal.biblioteca.Formato;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "filiacion")
public class Filiacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_filiacion;
    @Column(name = "sexo", columnDefinition = "varchar(2)")
    private String sexo;
    @Column(name = "raza", columnDefinition = "varchar(10)")
    private String raza;

    @Transient
    private Integer edad;
    @Column(name = "fecha_nac")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha_nac;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;


    @OneToMany(mappedBy = "filiacion",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Antropometria> antropometrias;
//
    @OneToMany(mappedBy = "filiacion",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Examen> examenes;
//
    @OneToMany(mappedBy = "filiacion",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<SignoVital> signos;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name="id_filiacion" ,referencedColumnName = "id_filiacion")
//    private List<Diagnostico> diagnosticos;
//
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="fil_antecedente",joinColumns = @JoinColumn (name = "id_filiacion"),inverseJoinColumns =  @JoinColumn(name="id_antecendente"))
    private Set<Antecedente> antecedentes ;

    private LocalDate fecha_filiacion;


    public Filiacion() {
    }

    public Filiacion(String sexo, String raza) {
        this.sexo = sexo;
        this.raza = raza;
    }

    public Long getId_filiacion() {
        return id_filiacion;
    }

    public void setId_filiacion(Long id_filiacion) {
        this.id_filiacion = id_filiacion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    //ANTROPOMETRIA
    public List<Antropometria> getAntropometrias() {return antropometrias;}
    public void setAntropometrias(List<Antropometria> antropometrias) {this.antropometrias = antropometrias; }

     //SIGNOS VITALES
    public List<SignoVital> getSignos() {
        return signos;
    }
    public void setSignos(List<SignoVital> signos) {
        this.signos = signos;
    }

    //EXAMENES
    public List<Examen> getExamenes() {
        return examenes;
    }
    public void setExamenes(List<Examen> examenes) {
        this.examenes = examenes;
    }

    //ANTECEDENTES
    public Set<Antecedente> getAntecedentes() {return antecedentes;}
    public void setAntecedentes(Set<Antecedente> antecedentes) {this.antecedentes = antecedentes;}

    public Integer getEdad() {
        Formato formato = new Formato();
        return formato.today().getYear()- this.fecha_nac.getYear();
    }
    public String getEdadString(){
        Period periodo = Period.between(fecha_nac,LocalDate.now());
        String edad = periodo.getYears()+" años, "+periodo.getMonths()+" meses y "+periodo.getDays()+" días";
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public LocalDate getFecha_filiacion() {
        return fecha_filiacion;
    }

    public void setFecha_filiacion(LocalDate fecha_filiacion) {
        this.fecha_filiacion = fecha_filiacion;
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Filiacion{" +
                "id_filiacion=" + id_filiacion +
                ", sexo='" + sexo + '\'' +
                ", raza='" + raza + '\'' +
                ", edad=" + edad +
                ", fecha_nac=" + fecha_nac +
                ", fecha_filiacion=" + fecha_filiacion +
                '}';
    }
}
