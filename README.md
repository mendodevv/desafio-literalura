# Desafío literalura
![banner-final](https://github.com/mendodevv/desafio-literalura/assets/129572136/be633bf7-0081-45df-8b8b-5f0ceecaac22)

Este proyecto, llamado "Desafío Literalura", fue realizado como parte de la formación en el programa Oracle Next Education (Oracle ONE). Es una aplicación de consola en Java que permite a los usuarios buscar libros por título, listar libros y autores registrados, listar autores vivos en un determinado año, listar libros por idioma, ver los 10 libros más descargados y obtener estadísticas.

## Descripción del proyecto
El proyecto utiliza Java como lenguaje de programación, Spring Boot como framework y Maven para la gestión de dependencias. La estructura del proyecto sigue la estructura estándar de Maven y está organizado en varios paquetes, incluyendo modelos, repositorios y servicios.

La aplicación consume la API de Gutendex para obtener datos de libros y autores, y luego convierte estos datos en objetos de modelo que se pueden manipular dentro de la aplicación. Los datos de los libros y autores se almacenan en una base de datos, lo que permite a los usuarios realizar búsquedas y obtener estadísticas.

La comunicación con la aplicación se realiza a través de la consola, con el siguiente menú: 

                    1 - Buscar libros por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    6 - Top 10 libros más descargados
                    7 - Obtener estadísiticas
                    0 - Salir

## Organización del código
El proyecto sigue la estructura estándar de Maven y está organizado en varios paquetes:

- `com.aluracursos.desafio_literalura.models`: Contiene las clases de modelo para Datos, Libros, DatosLibros, Autores y DatosAutores.
  - Datos.java: Record que hace la conexión entre la lista de libros (la cual está contenida en el array "results") del Json y la aplicación.
  - DatosLibros.java: Record que hace la conexión entre los datos de los libros del Json y las variables de la aplicación.
  - DatosAutores.java: Record que hace la conexión entre los datos de los autores de cada libro del Json (los autores y sus datos están dentro de otro array) y la aplicación.
  - Libros.java: Clase que define los atributos de los libros y crea su respectiva tabla en la base de datos. Tiene conexión ManyToOne con el modelo Autores.
  - Autores.java: Clase que define los atributos de los autores y crea su respectiva tabla en la base de datos. Tiene relación OneToMany con el modelo Libros.

- `com.aluracursos.desafio_literalura.repositorio`: Contiene las interfaces de repositorio para interactuar con la base de datos.
  -  IAutoresRepository.java: Repositorio para hacer consultas y busquedas relacionadas con los autores en la base de datos.
  -  ILibrosRepository.java: Repositorio para hacer consultas y busquedas relacionadas con los libros en la base de datos.
  
- `com.aluracursos.desafio_literalura.service`: Contiene las clases de servicio para consumir la API de Gutendex y convertir los datos.
  - ConsumoApi.java: Se encarga de obtener los datos de la API por medio del link proporcionado, y devuelve los datos como Json.
  - ConvierteDatos.java: Se encarga de convertir el Json en una clase (modelo) genérica.
  - IConvierteDatos.java: Esta interfaz se encarga de definir el tipo de dato genérico.

- `com.aluracursos.desafio_literalura.principal`: Contiene la clase Principal que maneja la lógica principal de la aplicación.
  - Principal.java: Es la clase principal de la aplicación que maneja la interacción con el usuario y la ejecución de las funcionalidades, y cada opción mostrada en el menú se realiza con propio método interno, igual definido en principal.

Asimismo, contamos con:
- Las dependencias necesarias:
  - Jackson Dataformat
  - Jackson Core
  - Spring Data JPA
  - PostgreSQL Driver
- La clase de arranque `DesafioLiteraluraApplication`, encargada iniciar la aplicación, crear el contexto de Spring y ejecuta la lógica principal de la aplicación a través de la clase Principal.
- La declaración y configuración de la base de datos en el archivo `application.properties`

## Organización del código de manera gráfica
![desafio_literalura_diagrama](https://github.com/mendodevv/desafio-literalura/assets/129572136/f83dc1b0-3fe2-4a1e-8505-504eb7a465cb)

## Demostración de la aplicación
### Youtube Overview
[![Video demostración de la aplicación](https://img.youtube.com/vi/haLSyWFrgFM/0.jpg)](https://www.youtube.com/watch?v=haLSyWFrgFM)

### General Overview
<table>
  <tr>
    <td>
      <p>Menú: muestra las opciones a elegir por el usuario</p>
      <img src="readme-images/menu.png" alt="Menú del programa" width="400"/>
    </td>
    <td>
      <p>Opción 1: buscar libros por título en la API de Gutendex</p>
      <img src="readme-images/opcion1.png" alt="Opción 1" width="400"/>
    </td>
  </tr>
  <tr>
    <td>
      <p>Opción 2: listar libros registrados</p>
      <img src="readme-images/opcion2.png" alt="Opción 2" width="400"/>
    </td>
    <td>
      <p>Opción 3: listar autores registrados</p>
      <img src="readme-images/opcion3.png" alt="Opción 3" width="400"/>
    </td>
  </tr>
  <tr>
    <td>
      <p>Opción 4: listar autores vivos en un determinado año</p>
      <img src="readme-images/opcion4.png" alt="Opción 4" width="400"/>
    </td>
    <td>
      <p>Opción 5: listar libros por idioma</p>
      <img src="readme-images/opcion5.png" alt="Opción 5" width="400"/>
    </td>
  </tr>
  </tr>
  <tr>
    <td>
      <p>Opción 6: Mostrar top 10 libros por deescargas de la API o de la base de datos</p>
      <img src="readme-images/opcion6.png" alt="Opción 6" width="400"/>
    </td>
    <td>
      <p>Opción 7: mostrar estadísitcas</p>
      <img src="readme-images/opcion7.png" alt="Opción 7" width="400"/>
    </td>
  </tr>
</table>

## Como usar la aplicación
**Clonar el repositorio**

Para empezar, necesitas clonar el repositorio en tu máquina local. Puedes hacerlo utilizando el siguiente comando en tu terminal:
   `git clone https://github.com/mendodevv/desafio-literalura.git`
   
O si lo prefieres, puedes presionar el botón verde `code<>` del repositorio y descargar los archivos como ZIP

1. Configurar la base de datos

La aplicación utiliza una base de datos PostgreSQL. Deberás crear una base de datos local y configurar las credenciales de la base de datos en el archivo src/main/resources/application.properties. Reemplaza ${DB_HOST}, ${DB_NAME_LITERALURA} ${DB_USER} y ${DB_PASSWORD} con tus propios valores (Puede ser en el mismo archivo properties, o creando tus propias variables de entorno).
- DB_HOST: el nombre de tu host (si la base de datos está en tu computadora, pon localhost).
- DB_NAME_LITERALURA: el nombre de tu base de datos.
- DB_USER: el nombre del usuario de la base datos (por default, postgres pondrá el nombre de "postgre").
- DB_PASSWORD: la contraseña que pusiste para postgreSQL

2. Compilar y ejecutar la aplicación

La aplicación utiliza Maven para la gestión de dependencias. Puedes compilar y ejecutar la aplicación utilizando los siguientes comandos en tu terminal:  <pre>cd desafio-literalura mvn clean install mvn spring-boot:run </pre> Esto compilará la aplicación y la iniciará.
Asimismo, si prefieres ejecutarla desde un IDE como IntelliJ por ejemplo, solo tienes que abrir el proyecto y correr la clase DesafioLiteraluraApplication.

3. Interactuar con la aplicación

Una vez que la aplicación esté en ejecución, puedes interactuar con ella a través de la consola. Se te presentará un menú con varias opciones para buscar libros, listar libros y autores registrados, listar autores vivos en un determinado año, listar libros por idioma, ver los 10 libros más descargados y obtener estadísticas.  Simplemente sigue las instrucciones en pantalla para interactuar con la aplicación.

## Tecnologías utilizadas
- Java SE17
- Maven
- Spring Boot V3.3.0
- Para el desarrollo del código, se usó el IDE IntelliJ
- Para la base de datos, se usó PostgreSQL

## Estado del proyecto

Finalizado.

## Licencias

MIT License.















