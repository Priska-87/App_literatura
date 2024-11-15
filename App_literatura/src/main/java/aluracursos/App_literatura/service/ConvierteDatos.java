package aluracursos.App_literatura.service;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos {

    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, clase);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
