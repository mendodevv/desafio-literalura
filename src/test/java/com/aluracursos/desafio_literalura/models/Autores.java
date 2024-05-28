package com.aluracursos.desafio_literalura.models;

public class Autores {
    private String name;
    private int añoNacimiento;
    private int añoMuerte;

    public Autores(DatosAutores datosAutores) {
        this.name = datosAutores.nombreAutor();
        this.añoNacimiento = datosAutores.añoNacimiento();
        this.añoMuerte = datosAutores.añoMuerte();
    }

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
