package App;

import UserDefinedExceptions.AlbumNotFoundException;
import UserDefinedExceptions.SongNotFoundException;
import model.Song;
import Connection.ConnectDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class SongImpl {

    public SongImpl() {
    }


    public void showAllSongs(List<Song> list) {
        List<Song> songlist = new ArrayList<>();
        display(list);
    }

    public void display(List<Song> list) {
        try {
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%4s %15s %15s %15s %15s %15s %15s", "SONG ID", "SONGNAME", "ARTISTNAME ", "ALBUMNAME", "GENERE", "SONGPATH", "DURATION");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            for (Song s : list) {
                System.out.printf("%4s %15s %15s %15s %8s %25s %2s", s.getSongid(), s.getSongname(), s.getArtistname(), s.getAlbumname(), s.getGenere(), s.getSongpath(), s.getDuration());
                System.out.println("");
            }

            if (list.isEmpty()) {
                throw new SongNotFoundException("Song not found");
            }
        } catch (SongNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Song> listBySongname(List<Song> list, String songName) {
        List<Song> listbysongname = new ArrayList<>();
        try {
            listbysongname = (list.stream().filter(p1 -> p1.getSongname().equalsIgnoreCase(songName)).collect(Collectors.toList()));
            display(listbysongname);
            if (list.isEmpty()) {
                throw new SongNotFoundException("Song not found");
            }
        } catch (SongNotFoundException e) {
            e.printStackTrace();
        }
        return listbysongname;
    }


    public List<Song> listByAlbum(List<Song> list, String albumName) {
        List<Song> listbyalbum = new ArrayList<>();
        try {
            listbyalbum = (list.stream().filter(p1 -> p1.getAlbumname().equalsIgnoreCase(albumName)).sorted((o1, o2) -> (o1.getSongname().compareToIgnoreCase(o2.getSongname()))).collect(Collectors.toList()));
            display(listbyalbum);
            if (list.isEmpty()) {
                throw new AlbumNotFoundException("Album not found");
            }
        } catch (AlbumNotFoundException e) {
            e.printStackTrace();
        }
        return listbyalbum;
    }

    public List<Song> listByGenre(List<Song> list, String genre) {
        List<Song> listbygenere = new ArrayList<>();
        try {
            listbygenere = list.stream().filter(p1 -> p1.getGenere().equalsIgnoreCase(genre)).sorted((o1, o2) -> o1.getSongname().compareToIgnoreCase(o2.getSongname())).collect(Collectors.toList());
            display(listbygenere);
            if (list.isEmpty()) {
                throw new SongNotFoundException("Song not found");
            }
        } catch (SongNotFoundException e) {
            e.printStackTrace();
        }
        return listbygenere;
    }


    public List<Song> listByArtist(List<Song> list, String artistname) {
        List<Song> listbyartist = new ArrayList<>();
        try {
            listbyartist = list.stream().filter(p1 -> p1.getArtistname().equalsIgnoreCase(artistname)).sorted((o1, o2) -> o1.getSongname().compareToIgnoreCase(o2.getSongname())).collect(Collectors.toList());
            display(listbyartist);
            if (list.isEmpty()) {
                throw new SongNotFoundException("Song not found");
            }
        } catch (SongNotFoundException e) {
            e.printStackTrace();
        }
        return listbyartist;
    }

    public List<Song> searchBySongId(List<Song> list, int songId) {
        List<Song> listbyId = new ArrayList<>();
        try {
            listbyId = list.stream().filter(p1 -> p1.getSongid() == songId).collect(Collectors.toList());
            display(listbyId);
            if (list.isEmpty()) {
                throw new SongNotFoundException("Song not found");
            }
        } catch (SongNotFoundException e) {
            e.printStackTrace();
        }
        return listbyId;
    }
}






