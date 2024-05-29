package com.aluracursos.desafio_literalura.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autores autor;

    @Column(name = "nombre_autor")
    private String nombreAutor;

    private List<String> lenguajes;
    private double numeroDescargas;

    public Libros() {}

    public Libros(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.lenguajes = datosLibros.languages();
        this.numeroDescargas = datosLibros.numeroDescargas();
    }

    public Libros(Datos datosLibros) {}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autores getAutor() {
        return autor;
    }

    public void setAutor(Autores autor) {
        this.autor = autor;
        this.nombreAutor = autor.getName();
    }
    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public List<String> getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(List<String> lenguajes) {
        this.lenguajes = lenguajes;
    }

    public double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}
