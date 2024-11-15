package aluracursos.App_literatura.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String Json, Class<T> clase );
}
