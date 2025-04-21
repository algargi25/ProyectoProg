package es.uji.garcia.model.exceptions;

public class LikedItemNotFoundException extends Exception {
    public LikedItemNotFoundException(String item) {
        super("El ítem '" + item + "' no se encuentra en la lista de recomendaciones.");
    }

}
