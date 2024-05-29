package com.aluracursos.desafio_literalura.principal;

import com.aluracursos.desafio_literalura.models.*;
import com.aluracursos.desafio_literalura.repositorio.ILibrosRepository;
import com.aluracursos.desafio_literalura.service.ConsumoApi;
import com.aluracursos.desafio_literalura.service.ConvierteDatos;
import com.aluracursos.desafio_literalura.service.IConvierteDatos;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final static String URL_BASE = "https://gutendex.com/books/?search=";

    private ILibrosRepository repositorio;

    public Principal(ILibrosRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu () {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libros por título
                    2 - Listar libros registrados
                    3 - Listar libros registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    agregarLibros();
                    break;
            }

        }
    }

    private Datos getDatosLibros() {
        Datos datosLibros = null;
        try {
            System.out.println("Escribe el libro que desea buscar: ");
            var nombreLibro = teclado.nextLine();
            var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
            datosLibros = conversor.obtenerDatos(json, Datos.class);

        } catch (Exception e) {
            System.err.println("Error al obtener datos del libro: " + e.getMessage());
        }
        return datosLibros;
    }

    private void agregarLibros() {
        Datos datosLibros = getDatosLibros();
        Libros libro = new Libros(datosLibros);
        libro = repositorio.save(libro);
        System.out.println(datosLibros);
    }
}
