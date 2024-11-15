package aluracursos.App_literatura.principal;

import aluracursos.App_literatura.model.Autor;
import aluracursos.App_literatura.model.DatosLibro;
import aluracursos.App_literatura.model.Libro;
import aluracursos.App_literatura.model.LibroRespuesta;
import aluracursos.App_literatura.repository.AutorRepository;
import aluracursos.App_literatura.repository.LibroRepository;
import aluracursos.App_literatura.service.ConsumoAPI;
import aluracursos.App_literatura.service.ConvierteDatos;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Principal {

    // VARIABLES LOCALES

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<Libro> datosLibro = new ArrayList<>();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                        \n<------------BIENVENIDA/O------------>
                        <--------SELECCIONE UNA OPCIÓN------->
                        1 - Buscar Libros por titulos 
                        2 - Ver libros registrados
                        3 - Ver Autores registrados
                        4 - ver autores vivos en un año determinado
                        5 - ver libros por idiomas
                        6 - Top 10 
                        
                        0 - Salir de la aplicación
                        """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibrosPorTitulos();
                    break;
                case 2:
                    verLibrosRegistrados();
                    break;
                case 3:
                    verAutoresRegistrados();
                    break;
                case 4:
                    verAutoresVivosEnUnAñoDeterminado();
                    break;
                case 5:
                    verLibrosPorIdioma();
                    break;
                case 6:
                    top10Libros();
                    break;
                case 0:
                    System.out.println("...");
                    System.out.println("Cerrando aplicación ...");
                    break;
                default:
                    System.out.println("¡Opción iválida!");
            }
        }

    }

    private Libro getDatosLibro () {
        System.out.println("Ingrese el nombre del libro: ");
        var nombreLibro = teclado.nextLine().toLowerCase();
        var json = consumoAPI.obtenerDatos(URL_BASE+"?search=" + nombreLibro.replace(" ", "+"));
        var datos = conversor.obtenerDatos(json, LibroRespuesta.class);

        if (datos != null && datos.getResultadoLibros() != null && !datos.getResultadoLibros().isEmpty()) {
            DatosLibro primerLibro = datos.getResultadoLibros().get(0); // Obtener el primer libro de la lista
            return new Libro(primerLibro);
        } else {
            System.out.println("No se encontraron resultados.");
            return null;
        }
    }

    private void buscarLibrosPorTitulos() {
        Libro libro = getDatosLibro();

        if (libro == null) {
            System.out.println("Libro no encontrado. el valor es null");
            return;
        }

        try {
            boolean libroExists = libroRepository.existsByTitulo(libro.getTitulo());
            if (libroExists) {
                System.out.println("El libro ya existe en la base de datos!");
            } else {
                libroRepository.save(libro);
                System.out.println(libro.toString());
            }
        } catch (InvalidDataAccessApiUsageException e) {
            System.out.println("No se puede persisitir el libro buscado!");
        }
    }


    private void  verLibrosRegistrados(){
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en la base de datos.");
        } else {
            System.out.println("\n-------------------------------->LIBROS<------------------------------------");
            for (Libro libro : libros) {
                System.out.println(libro.toString());
                System.out.println("-----------------------------------------------------------------------------\n");
            }
        }
    }

    private void verAutoresRegistrados(){
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores en la base de datos. \n");
        } else {
            System.out.println("\n---------------------->AUTORES<--------------------------\n");
            Set<String> autoresUnicos = new HashSet<>();
            for (Autor autor : autores) {
                // add() retorna true si el nombre no estaba presente y se añade correctamente
                if (autoresUnicos.add(autor.getNombre())){
                    System.out.println(autor.getNombre()+'\n');
                    System.out.println("---------------------------------------------------------\n");
                }
            }
        }
    }

    private void verAutoresVivosEnUnAñoDeterminado() {

        System.out.println("Indica el año para consultar que autores estan vivos: \n");
        var anioBuscado = teclado.nextInt();
        teclado.nextLine();

        List<Autor> autoresVivos = autorRepository
                .findByFechaNacimientoLessThanOrFechaFallecimientoGreaterThanEqual(anioBuscado, anioBuscado);

        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores que estuvieran vivos en el año " + anioBuscado + ".");
        } else {
            System.out.println("\n------------->AUTORES VIVOS EN AÑO BUSCADO<--------------\n");
            System.out.println("Los autores que estaban vivos en el año " + anioBuscado + " son:");
            System.out.println("---------------------------------------------------------\n");
            Set<String> autoresUnicos = new HashSet<>();

            for (Autor autor : autoresVivos) {
                if (autor.getFechaNacimiento() != null && autor.getFechaFallecimiento() != null) {
                    if (autor.getFechaNacimiento() <= anioBuscado && autor.getFechaFallecimiento() >= anioBuscado) {
                        if (autoresUnicos.add(autor.getNombre())) {
                            System.out.println("Autor: " + autor.getNombre());
                            System.out.println("---------------------------------------------------------\n");
                        }
                    }
                }
            }
        }
    }

    private void  verLibrosPorIdioma(){
        System.out.println("Ingrese Idioma en el que quiere buscar: \n");
        System.out.println(" - Opción - es : Libros en español - ");
        System.out.println(" - Opción - en : Libros en ingles -  ");


        var idioma = teclado.nextLine();
        List<Libro> librosPorIdioma = libroRepository.findByIdioma(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No se encontraron libros en la base de datos.");
        } else {
            System.out.println("\n--------------------->AUTORES POR IDIOMA SELECCIONADO<-------------------------\n");
            System.out.println("Libros encontrados en el idioma seleccionado son:");
            for (Libro libro : librosPorIdioma) {
                System.out.println(libro.toString());
                System.out.println("-------------------------------------------------------------------------------");
            }
        }
    }

    private void top10Libros(){
        List<Libro> top10Libros = libroRepository.findTop10ByTituloByCantidadDescargas();
        if (!top10Libros.isEmpty()){
            int index = 1;
            for (Libro libro: top10Libros){
                System.out.println("\n----------------------------------------------->TOP 10 LIBROS<-----------------------------------------------");
                System.out.printf("Libro %d: %s Autor: %s Descargas: %d\n",
                        index, libro.getTitulo(), libro.getAutores().getNombre(), libro.getCantidadDescargas());
                System.out.println("-------------------------------------------------------------------------------------------------------------");
                index++;
            }
        }
    }

// FIN--------------------------------------------------------------------------------------------------------------
}

