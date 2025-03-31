package es.uji.garcia.exceptions;

public class LikedItemNotFoundException extends Exception {
    public LikedItemNotFoundException(String item) {
        super("El ítem '" + item + "' no se encuentra en la lista de recomendaciones.");
    }

}
