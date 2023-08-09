package model;

public class Song {
   // songid | songname             | artistname | albumname | genere  | songpath
    int songid;
    String songname;
    String artistname;
    String albumname;
    String genere;
    String songpath;
    String duration;

    // no Aurgument constructor
       public Song() {}

    // parameterized constructor
    public Song(int songid, String songname, String artistname, String albumname, String genere, String songpath ,String duration) {
        this.songid = songid;
        this.songname = songname;
        this.artistname = artistname;
        this.albumname = albumname;
        this.genere = genere;
        this.songpath = songpath;
        this.duration = duration;
    }

    //Getter and Setter

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getArtistname() {
        return artistname;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getSongpath() {
        return songpath;
    }

    public void setSongpath(String songpath) {
        this.songpath = songpath;
    }

    public String getDuration() {return duration;}

    public void setDuration(String duration) {this.duration = duration;}

    //To String Method


    @Override
    public String toString() {
        return "Song{" +
                "songid=" + songid +
                ", songname='" + songname + '\'' +
                ", artistname='" + artistname + '\'' +
                ", albumname='" + albumname + '\'' +
                ", genere='" + genere + '\'' +
                ", songpath='" + songpath + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
