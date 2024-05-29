package com.aluracursos.desafio_literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutores> autor,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") double numeroDescargas
) {
}
