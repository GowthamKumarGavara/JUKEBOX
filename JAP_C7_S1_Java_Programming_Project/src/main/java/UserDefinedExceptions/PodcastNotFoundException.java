package UserDefinedExceptions;

public class PodcastNotFoundException extends Exception{

    public PodcastNotFoundException(String message){
        super(message);
    }
}
