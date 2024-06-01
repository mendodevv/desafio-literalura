package com.aluracursos.desafio_literalura.principal;

import com.aluracursos.desafio_literalura.models.*;
import com.aluracursos.desafio_literalura.repositorio.IAutoresRepository;
import com.aluracursos.desafio_literalura.repositorio.ILibrosRepository;
import com.aluracursos.desafio_literalura.service.ConsumoApi;
import com.aluracursos.desafio_literalura.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final static String URL_BASE = "https://gutendex.com/books/?search=";

    private IAutoresRepository autoresRepository;
    private ILibrosRepository librosRepository;

    public Principal(IAutoresRepository autoresRepository, ILibrosRepository librosRepository) {
        this.autoresRepository = autoresRepository;
        this.librosRepository = librosRepository;
    }

    public void muestraElMenu () {
        var opcion = -1;
        System.out.println("Bienvenido! Por favor selecciona una opción: ");
        while (opcion != 0) {
            var menu = """
                    1 - | Buscar libros por título | 📕
                    2 - | Listar libros registrados | ✍️
                    3 - | Listar autores registrados | 👨‍🏫
                    4 - | Listar autores vivos en un determinado año | ⌛
                    5 - | Listar libros por idioma | ℹ️
                    6 - | Top 10 libros más descargados | 🔝
                    7 - | Obtener estadísiticas | 📊
                    0 - | Salir | 👋
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    agregarLibros();
                    break;
                case 2:
                    librosRegistrados();
                    break;
                case 3:
                    autoresRegistrados();
                    break;
                case 4:
                    autoresPorAño();
                    break;
                case 5:
                    listarPorIdioma();
                    break;
                case 6:
                    topDiezLibros();
                    break;
                case 7:
                    estaditicasApi();
                    break;
                    case 0:
                        System.out.println("Cerrando aplicación...");
                        break;

                default:
                    System.out.println("Opción no válida, intenta de nuevo");
            }

        }
    }

    private Datos getDatosLibros() {
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
        Datos datosLibros = conversor.obtenerDatos(json, Datos.class);
        return datosLibros;
    }

    private Libros crearLibro(DatosLibros datosLibros, Autores autor) {
        if (autor != null) {
            return new Libros(datosLibros, autor);
        } else {
            System.out.println("El autor es null, no se puede crear el libro");
            return null;
        }
    }

    private  void agregarLibros() {
        System.out.println("Escribe el libro que deseas buscar: ");
        Datos datos = getDatosLibros();
        if (!datos.resultados().isEmpty()) {
            DatosLibros datosLibro = datos.resultados().get(0);
            DatosAutores datosAutores = datosLibro.autor().get(0);
            Libros libro = null;
            Libros libroRepositorio = librosRepository.findByTitulo(datosLibro.titulo());
            if (libroRepositorio != null) {
                System.out.println("Este libro ya se encuentra en la base de datos");
                System.out.println(libroRepositorio.toString());
            } else {
                Autores autorRepositorio = autoresRepository.findByNameIgnoreCase(datosLibro.autor().get(0).nombreAutor());
                if (autorRepositorio != null) {
                    libro = crearLibro(datosLibro, autorRepositorio);
                    librosRepository.save(libro);
                    System.out.println("----- LIBRO AGREGADO -----\n");
                    System.out.println(libro);
                } else {
                    Autores autor = new Autores(datosAutores);
                    autor = autoresRepository.save(autor);
                    libro = crearLibro(datosLibro, autor);
                    librosRepository.save(libro);
                    System.out.println("----- LIBRO AGREGADO -----\n");
                    System.out.println(libro);
                }
            }
        } else {
            System.out.println("El libro no existe en la API de Gutendex, ingresa otro");
        }
    }

    private void librosRegistrados() {
        List<Libros> libros = librosRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados");
            return;
        }
        System.out.println("----- LOS LIBROS REGISTRADOS SON: -----\n");
        libros.stream()
                .sorted(Comparator.comparing(Libros::getTitulo))
                .forEach(System.out::println);
    }

    private void autoresRegistrados() {
        List<Autores> autores = autoresRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados");
            return;
        }
        System.out.println("----- LOS AUTORES REGISTRADOS SON: -----\n");
        autores.stream()
                .sorted(Comparator.comparing(Autores::getName))
                .forEach(System.out::println);
    }

    private void autoresPorAño() {
        System.out.println("Escribe el año en el que deseas buscar: ");
        var año = teclado.nextInt();
        teclado.nextLine();
        if(año < 0) {
            System.out.println("El año no puede ser negativo, intenta de nuevo");
            return;
        }
        List<Autores> autoresPorAño = autoresRepository.findByAñoNacimientoLessThanEqualAndAñoMuerteGreaterThanEqual(año, año);
        if (autoresPorAño.isEmpty()) {
            System.out.println("No hay autores registrados en ese año");
            return;
        }
        System.out.println("----- LOS AUTORES VIVOS REGISTRADOS EN EL AÑO " + año + " SON: -----\n");
        autoresPorAño.stream()
                .sorted(Comparator.comparing(Autores::getName))
                .forEach(System.out::println);
    }

    private void listarPorIdioma() {
        System.out.println("Escribe el idioma por el que deseas buscar: ");
        String menu = """
                es - Español
                en - Inglés
                fr - Francés
                pt - Portugués
                """;
        System.out.println(menu);
        var idioma = teclado.nextLine();
        if (!idioma.equals("es") && !idioma.equals("en") && !idioma.equals("fr") && !idioma.equals("pt")) {
            System.out.println("Idioma no válido, intenta de nuevo");
            return;
        }
        List<Libros> librosPorIdioma = librosRepository.findByLenguajesContaining(idioma);
        if (librosPorIdioma.isEmpty()) {
            System.out.println("No hay libros registrados en ese idioma");
            return;
        }
        System.out.println("----- LOS LIBROS REGISTRADOS EN EL IDIOMA SELECCIONADO SON: -----\n");
        librosPorIdioma.stream()
                .sorted(Comparator.comparing(Libros::getTitulo))
                .forEach(System.out::println);
    }

    private void topDiezLibros() {
        System.out.println("De donde quieres obtener los libros más descargados?");
        String menu = """
                1 - Gutendex
                2 - Base de datos
                """;
        System.out.println(menu);
        var opcion = teclado.nextInt();
        teclado.nextLine();

        if (opcion == 1) {
            System.out.println("----- LOS 10 LIBROS MÁS DESCARGADOS EN GUTENDEX SON: -----\n");
            var json = consumoApi.obtenerDatos(URL_BASE);
            Datos datos = conversor.obtenerDatos(json, Datos.class);
            List<Libros> libros = new ArrayList<>();
            for (DatosLibros datosLibros : datos.resultados()) {
                Autores autor = new Autores(datosLibros.autor().get(0));
                Libros libro = new Libros(datosLibros, autor);
                libros.add(libro);
            }
            libros.stream()
                    .sorted(Comparator.comparing(Libros::getNumeroDescargas).reversed())
                    .limit(10)
                    .forEach(System.out::println);
        } else if (opcion == 2) {
            System.out.println("----- LOS 10 LIBROS MÁS DESCARGADOS EN LA BASE DE DATOS SON: -----\n");
            List<Libros> libros = librosRepository.findAll();
            if (libros.isEmpty()) {
                System.out.println("No hay libros registrados");
                return;
            }
            libros.stream()
                    .sorted(Comparator.comparing(Libros::getNumeroDescargas).reversed())
                    .limit(10)
                    .forEach(System.out::println);
        } else {
            System.out.println("Opción no válida, intenta de nuevo");
        }
    }

    private void estaditicasApi() {
        System.out.println("De donde quieres obtener las estadísiticas: ");
        String menu = """
                1 - Gutendex
                2 - Base de datos
                """;
        System.out.println(menu);
        var opcion = teclado.nextInt();
        teclado.nextLine();

        if (opcion == 1) {
            System.out.println("----- ESTADÍSTICAS DE DESCARGAS EN GUTENDEX -----\n");
            var json = consumoApi.obtenerDatos(URL_BASE);
            Datos datos = conversor.obtenerDatos(json, Datos.class);
            DoubleSummaryStatistics estadisticas = datos.resultados().stream()
                    .collect(Collectors.summarizingDouble(DatosLibros::numeroDescargas));
            System.out.println("📈 Libro con más descargas: " + estadisticas.getMax());
            System.out.println("📉 Libro con menos descargas: " + estadisticas.getMin());
            System.out.println("📊 Promedio de descargas: " + estadisticas.getAverage());
            System.out.println("\n");
        } else if (opcion == 2) {
            System.out.println("----- ESTADÍSTICAS DE DESCARGAS EN LA BASE DE DATOS -----\n");
            List<Libros> libros = librosRepository.findAll();
            if (libros.isEmpty()) {
                System.out.println("No hay libros registrados");
                return;
            }
            DoubleSummaryStatistics estadisticas = libros.stream()
                    .collect(Collectors.summarizingDouble(Libros::getNumeroDescargas));
            System.out.println("📈 Libro con más descargas: " + estadisticas.getMax());
            System.out.println("📉 Libro con menos descargas: " + estadisticas.getMin());
            System.out.println("📊 Promedio de descargas: " + estadisticas.getAverage());
            System.out.println("\n");
        } else {
            System.out.println("Opción no válida, intenta de nuevo");
        }
    }
}




