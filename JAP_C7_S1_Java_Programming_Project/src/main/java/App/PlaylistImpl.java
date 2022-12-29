package App;

import Connection.ConnectDataBase;
import UserDefinedExceptions.PlaylistNotFoundException;

import java.sql.*;

public class PlaylistImpl{
    Connection con = ConnectDataBase.connectToSql();

    public PlaylistImpl() throws SQLException, ClassNotFoundException {
    }

    //Zero Parameterized Constructor (throwsExceptionBecasuseofabovesyntax)
  //  public PlaylistImpl() throws SQLException, ClassNotFoundException {}


    public void creatPlaylist(String playlistName, int userId) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into playlists(playlistname,userid) values(?,?)");
            ps.setString(1, playlistName);
            ps.setInt(2, userId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException   e) {
            e.printStackTrace();
        }
    }


    public void insertSong(int playlistId, int songId) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into playlistdata (playlistId,songId) values (?,?)");
            ps.setInt(1, playlistId);
            ps.setInt(2, songId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPodCast(int playlistId, int podid) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into playlistdata(playlistId,podId) values (?,?)");
            ps.setInt(1, playlistId);
            ps.setInt(2, podid);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void insertAlbum(int playlistId, String albumName) {
        try {
            PreparedStatement ps = con.prepareStatement("select * from songs where albumname=?");
            ps.setString(1, albumName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                insertSong(playlistId, rs.getInt(1));   //Method call
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deletePlayListContent(int playlistId) { // notWorking
        {
            try {
                PreparedStatement ps = con.prepareStatement("delete from playLists where playlistid=?");
                ps.setInt(1, playlistId);
                int count = ps.executeUpdate();
                if (count > 0) {
                    System.out.println("Song deleted successfully :)");
                } else {
                    throw new PlaylistNotFoundException("Playlist Not Found :(\n");
                }
            } catch (SQLException | PlaylistNotFoundException se) {
                se.printStackTrace();
            }
        }
    }

        public void displayPlayLists ( int userId){
            int playListId;
            try {  //   PreparedStatement ps = con.prepareStatement("select playlistId,playlistName from playlists where userid=?");// ps.setInt();

                Statement s = con.createStatement();
                ResultSet rs = s.executeQuery("select playlistId,playlistName from playlists where userid=" + userId);
                System.out.printf("%10s %10s", "playListId", "PlayListName\n");
                System.out.print("...........................................................................................................\n");
                // System.out.println(rs.getInt(1)+"   "+rs.getString(3));
                while (rs.next()) {
                    playListId = rs.getInt(1);
                    String PlayListName = rs.getString(2);
                    System.out.printf("%10s %10s", playListId, PlayListName);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public void displayPlayListContent(int playlistId) {
        System.out.println("ALL SONGS IN PLAYLIST\n");
        System.out.printf("%5s %10s\n", "songId", "songName");
        try {
            Statement s1 = con.createStatement();
            ResultSet rs1 = s1.executeQuery("select distinct songs.songid,songs.songname from songs join playlistdata on songs.songid=playlistdata.songid join playlists on playlistdata.playlistid=" + playlistId);
            while (rs1.next()) {
                System.out.printf("%5s %10s\n", rs1.getInt(1), rs1.getString(2));
            }

            System.out.println("ALL PODCASTS IN PLAYLIST\n");
            System.out.printf("%5s %10s\n", "podcastId", "podcastName");

            Statement s2 = con.createStatement();
            ResultSet rs2 = s2.executeQuery("select distinct podcasts.poidid,podcasts.podname from podcasts join playlistdata on podcasts.poidid=playlistdata.podid join playlists on playlistdata.playlistid=" + playlistId);
            while (rs2.next()) {
                System.out.printf("%5s %10s\n", rs2.getInt(1), rs2.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}



