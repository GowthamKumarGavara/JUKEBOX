import App.SongImpl;
import model.Song;
import org.junit.jupiter.api.Test;
import Dao.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SongImpITEstCase {
    SongImpl songImpl;

    public SongImpITEstCase() throws SQLException, ClassNotFoundException {
    }


    SongDao s = new SongDao();
    List<Song> songlist = s.readfromdatabase();
    @Test
    public void givenSongNameGetSongDetails() {
        assertEquals(1, songImpl.listBySongname (songlist, "Idhe Kadha Nee Katha"));
        assertEquals("Maharshi", songImpl.listBySongname(songlist,"Idhe Kadha Nee Katha").get(0).getAlbumname());    //get album
        assertEquals("action", songImpl.listBySongname(songlist, "Kaanunna Kalyanam").get(0).getGenere()); // get genere
    }

    public void givenSongArtistGetSongDetails() {
        assertEquals(2,songImpl.listByArtist(songlist,"sita").get(0).getSongid());
        assertEquals("Oh Sita Hey Rama", songImpl.listByArtist(songlist,"sita").get(0).getSongname());
        assertEquals("emotional", songImpl.listByArtist(songlist,"sita").get(0).getGenere());
        assertEquals("3:50", songImpl.listByArtist(songlist,"baruto").get(0).getDuration());
    }

    public void givenSongGenreGetSongDetails() {
        assertEquals("3:30", songImpl.listBySongname(songlist,"looser").get(0).getDuration());
        assertEquals("action", songImpl.listBySongname(songlist,"looser").get(0).getGenere());
        assertEquals("sitaram", songImpl.listBySongname(songlist,"Kaanunna Kalyanam").get(0).getAlbumname());
        assertEquals("3:50", songImpl.listBySongname(songlist,"Oh Sita Hey Rama").get(0).getDuration());

    }

}
