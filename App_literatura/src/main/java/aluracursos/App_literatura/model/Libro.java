package aluracursos.App_literatura.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Entity
    @Table(name = "libros")
    public class Libro {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonProperty("libro_id")
        private Long id;

        @Column(unique = true)
        private String titulo;

        @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // Asegura que el autor se guarde automáticamente
        @JoinColumn(name = "autor_id")
        //@Transient
        private Autor autor;
        @Enumerated(EnumType.STRING)
        private Genero genero;
        private String idioma;
        private String imagen;
        private Long cantidadDescargas;


        public Libro() {
        }

        public Libro(DatosLibro datosLibro) {
            this.id = datosLibro.id();
            this.titulo = datosLibro.titulo();
            // Si autor es una lista de autores
            if (datosLibro.autor() != null && !datosLibro.autor().isEmpty()) {
                this.autor = datosLibro.autor().get(0); // Toma el primer autor de la lista
            } else {
                this.autor = null; // o maneja el caso de que no haya autor
            }
            this.genero =  generoLibros(datosLibro.genero());
            this.idioma = idiomaLibros(datosLibro.idioma());
            this.imagen = imagenLibros(datosLibro.imagen());
            this.cantidadDescargas = datosLibro.cantidadDescargas();

        }

        public Libro(Libro libro) {
        }

        private Genero generoLibros(List<String> generos) {
            if (generos == null || generos.isEmpty()) {
                return Genero.DESCONOCIDO;
            }
            Optional<String> primerGenero = generos.stream()
                    .map(g -> {
                        int index = g.indexOf("--");
                        return index != -1 ? g.substring(index + 2).trim() : null;
                    })
                    .filter(Objects::nonNull)
                    .findFirst();
            return primerGenero.map(Genero::fromString).orElse(Genero.DESCONOCIDO);
        }

        private String idiomaLibros(List<String> idiomas) {
            if (idiomas == null || idiomas.isEmpty()) {
                return "Desconocido";
            }
            return idiomas.get(0);
        }

        private String imagenLibros(Media media) {
            if (media == null || media.imagen().isEmpty()) {
                return "Sin imagen";
            }
            return media.imagen();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Genero getGenero() {
            return genero;
        }

        public void setGenero(Genero genero) {
            this.genero = genero;
        }

        public String getImagen() {
            return imagen;
        }

        public void setImagen(String imagen) {
            this.imagen = imagen;
        }


        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public Autor getAutores() {
            return autor;
        }

        public void setAutores(Autor autores) {
            this.autor = autores;
        }


        public String getIdioma() {
            return idioma;
        }

        public void setIdioma(String idioma) {
            this.idioma = idioma;
        }

        public Long getCantidadDescargas() {
            return cantidadDescargas;
        }

        public void setCantidadDescargas(Long cantidadDescargas) {
            this.cantidadDescargas = cantidadDescargas;
        }


        @Override
        public String toString() {
            return
                    "  \nid=" + id +
                            "  \nLibro id=" + id +
                            ", \ntitulo='" + titulo + '\'' +
                            ", \nauthors=" + (autor != null ? autor.getNombre() : "N/A")+
                            ", \ngenero=" + genero +
                            ", \nidioma=" + idioma +
                            ", \nimagen=" + imagen +
                            ", \ncantidadDescargas=" + cantidadDescargas;
        }
    }

