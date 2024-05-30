package com.aluracursos.desafio_literalura.repositorio;

import com.aluracursos.desafio_literalura.models.Libros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILibrosRepository extends JpaRepository<Libros, Long> {
    Libros findByTitulo(String titulo);
}
