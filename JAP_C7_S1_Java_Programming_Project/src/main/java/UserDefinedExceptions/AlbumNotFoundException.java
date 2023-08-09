package UserDefinedExceptions;

public class AlbumNotFoundException extends Exception{
     public AlbumNotFoundException(String message){
         super(message);
     }
}
