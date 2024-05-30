package com.aluracursos.desafio_literalura.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
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
    private List<Libros> libros = new ArrayList<>();

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

    public List<Libros> getLibros() {
        return libros;
    }

    public void setLibros(List<Libros> libros) {
        this.libros = libros;
    }


    @Override
    // Obtener solo el título de los libros
    public String toString() {
        StringBuilder librosTitulos = new StringBuilder();
        for (Libros libro : libros) {
            librosTitulos.append(libro.getTitulo()).append(", ");
        }

        // Eliminar la última coma y espacio
        if (librosTitulos.length() > 0) {
            librosTitulos.setLength(librosTitulos.length() - 2);
        }

        return "Autor: " + name + "\n" +
                "Fecha de nacimiento: " + añoNacimiento + "\n" +
                "Fecha de fallecimiento: " + añoMuerte + "\n" +
                "Libros: " + librosTitulos + "\n";
    }
}
