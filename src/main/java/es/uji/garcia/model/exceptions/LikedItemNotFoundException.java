package es.uji.garcia.model.exceptions;

public class LikedItemNotFoundException extends Exception {
    private String nameLikedItem;

    public LikedItemNotFoundException(String message, String nameLikedItem){
        super(message);
        this.nameLikedItem = nameLikedItem;
    }

    public String getNameLikedItem(){
        return nameLikedItem;
    }

}
