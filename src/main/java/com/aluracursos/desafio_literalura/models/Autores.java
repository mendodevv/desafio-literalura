package com.aluracursos.desafio_literalura.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int añoNacimiento;
    private int añoMuerte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libros> libros;

    public Autores(DatosAutores datosAutores) {
        this.name = datosAutores.nombreAutor();
        this.añoNacimiento = datosAutores.añoNacimiento();
        this.añoMuerte = datosAutores.añoMuerte();
    }

    public Autores() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAñoNacimiento() {
        return añoNacimiento;
    }

    public void setAñoNacimiento(int añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }

    public int getAñoMuerte() {
        return añoMuerte;
    }

    public void setAñoMuerte(int añoMuerte) {
        this.añoMuerte = añoMuerte;
    }
}
