package aluracursos.App_literatura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("name")
    private String nombre;
    @JsonProperty("birth_year")
    private Integer fechaNacimiento;
    @JsonProperty("death_year")
    private Integer fechaFallecimiento;

    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;


    public Autor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

//    public void setFechaNacimiento(int fechaNacimiento){
//        this.fechaNacimiento = fechaNacimiento;
//    }

//    public void setFechaFallecimiento(int fechaNacimiento){
//        this.fechaFallecimiento = fechaFallecimiento;
//    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Autor(DatosAutor autor) {
        this.nombre = autor.nombre();
        this.fechaNacimiento = autor.fechaNacimiento();
        this.fechaFallecimiento = autor.fechaFallecimiento();
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimient='" + fechaNacimiento + '\'' +
                ", fechaFallecimiento='" + fechaFallecimiento + '\'' +
                '}';

    }
}

