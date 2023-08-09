package App;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class JukeBoxMain {
    public static void main(String[] args) throws UnsupportedAudioFileException, SQLException, LineUnavailableException, ParseException, IOException, ClassNotFoundException {
        JukeboxImpl juke = new JukeboxImpl();
        juke.jukeBoxOperation();
    }
}
