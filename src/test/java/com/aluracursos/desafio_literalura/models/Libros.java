package com.aluracursos.desafio_literalura.models;

import java.util.List;

public class Libros {
    private String titulo;
    private String autor;
    private List<String> lenguajes;
    private double numeroDescargas;

    public Libros(DatosLibros datosLibros, Autores autor) {
        this.titulo = datosLibros.titulo();
        this.autor = autor.getName();
        this.lenguajes = datosLibros.languages();
        this.numeroDescargas = datosLibros.numeroDescargas();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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
