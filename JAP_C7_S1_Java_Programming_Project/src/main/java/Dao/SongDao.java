package Dao;

import model.Song;
import Connection.ConnectDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongDao {
    public List<Song> readfromdatabase() throws SQLException, ClassNotFoundException {
        List<Song> songlist = new ArrayList<>();
        Connection con = ConnectDataBase.connectToSql();

        PreparedStatement ps = con.prepareStatement("select * from songs");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int songid = rs.getInt(1);
            String songName = rs.getString(2);
            String artistname = rs.getString(3);
            String albumname = rs.getString(4);
            String genere = rs.getString(5);
            String songpath = rs.getString(6);
            String duration = rs.getString(7);
            Song song = new Song(songid, songName, artistname, albumname, genere, songpath, duration);
            songlist.add(song);
        }
        return songlist;
    }
}