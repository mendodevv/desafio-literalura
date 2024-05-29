package com.aluracursos.desafio_literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAutores(
        @JsonAlias("name") String nombreAutor,
        @JsonAlias("birth_year") int añoNacimiento,
        @JsonAlias("death_year") int añoMuerte
) {
}
