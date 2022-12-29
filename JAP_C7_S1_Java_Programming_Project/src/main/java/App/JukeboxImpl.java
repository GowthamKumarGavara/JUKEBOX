package App;
import Connection.ConnectDataBase;
import Dao.PodcastDao;
import Dao.SongDao;
import Dao.UserDao;
import model.Audio;
import model.Playlist;
import model.Podcast;
import model.Song;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JukeboxImpl {
    public JukeboxImpl() {
    }

    public void jukeBoxOperation()throws ParseException, UnsupportedAudioFileException, LineUnavailableException, IOException, SQLException, ClassNotFoundException {
        SongImpl si = new SongImpl();
        PodcastImpl pi = new PodcastImpl();
        PlaylistImpl pli = new PlaylistImpl();
        SongDao sd = new SongDao();
        PodcastDao pd = new PodcastDao();
        UserDao uD = new UserDao();
        Scanner sc = new Scanner(System.in);
        PlayMusic pm = new PlayMusic();
        Connection conn= ConnectDataBase.connectToSql();

        int choice, choice1, choice2, choice3, choice4;

        System.out.println("..........................Welcome To The JUKEBOX..............................");
        int userId = uD.userAction();

        System.out.println("These are the currently available Audios in the JukeBox");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("                                             Songs                                                        ");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        List<Song> songList = new ArrayList<>();
        songList = sd.readfromdatabase();
        si.showAllSongs(songList);

        System.out.println("\n\n\n");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("                                            Podcasts                                                       ");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        List<Podcast> podList = new ArrayList<>();
        podList=pd.readAllpodcast();
        pi.displayPodCastList(podList);


        do {
            System.out.println("Main Menu ");
            System.out.println("Choose an option from Below:-\n1.Songs\n2.Podcasts\n3.Playlists\n4.PlayMusic\n5.Exit");
            choice = sc.nextInt();
            switch (choice) {
                       case 1:
                            do {
                        System.out.println("Search a song by:\n1.Name\n2.Artist\n3.Genre\n4.SongId\n5.Album\n6.Display All Songs\n7.Back Menu");
                        choice1 = sc.nextInt();
                        switch (choice1) {
                            case 1:
                                System.out.println("Enter name of song");
                                String songName = sc.next();
                                si.listBySongname(songList, songName);
                                break;
                            case 2:
                                System.out.println("Enter name of artist");
                                String artist = sc.next();
                                si.listByArtist(songList, artist);
                                break;
                            case 3:
                                System.out.println("Enter genre");
                                String genre = sc.next();
                                si.listByGenre(songList, genre);
                                break;
                            case 4:
                                System.out.println("Enter SongId");
                                int songId = sc.nextInt();
                                si.searchBySongId(songList,songId);
                                break;
                            case 5:
                                System.out.println("Enter Album name");
                                String album = sc.next();
                                si.listByAlbum(songList, album);
                                break;
                            case 6:
                                si.display(songList);
                                break;
                            case 7:
                                break;
                            default:
                                System.out.println("Incorrect choice!");
                                break;
                        }
                         } while (choice1 != 7);


                        case 2:
                          do {
                        System.out.println("Search a Podcast by:\n1.Name\n2.Celebrity\n3.PodcastId\n4.Date\n5.Display all Podcasts\n6.Back Menu");
                        choice2 = sc.nextInt();
                        switch (choice2) {
                            case 1:
                                System.out.println("Enter name of podcast");
                                String podcastName = sc.next();
                                pi.displaypodcastbypodname(podList, podcastName);
                                break;
                            case 2:
                                System.out.println("Enter name of Celebrity");
                                String celebrity = sc.next();
                                pi.displaypodcastbycelebrityName(podList, celebrity);
                                break;
                            case 3:
                                System.out.println("Enter PodcastId");
                                int podId = sc.nextInt();
                                pi.displaypodcastbypodId(podList, podId);
                                break;
                            case 4:
                                System.out.println("Enter date(YYYY-MM-DD):");
                                String str = sc.next();
                                Date date = Date.valueOf(str);
                                pi.displaypodcastbydate(podList, date);
                                break;
                            case 5:
                                pi.displaypod(podList);
                            case 6:
                                break;
                            default:
                                System.out.println("Incorrect choice!");
                                break;
                        }
                        } while (choice2 != 6);


                       case 3:
                          do {
                        System.out.println("1.Create a Playlist\n2.Insert a song into Playlist\n3.Insert a Podcast into a Playlist\n" +
                                "4.Insert an Album into a Playlist\n5.Delete a Playlist\n6.Display my Playlists\n7.Display Playlist Content\n" +
                                "8.Back Menu");
                        choice3 = sc.nextInt();
                        switch (choice3) {
                            case 1:
                                System.out.println("Enter playlist name: ");
                                String name=sc.next();
                                pli.creatPlaylist(name,userId);
                                break;
                            case 2:
                                si.display(songList);
                                System.out.println("Enter SongId: ");
                                int songId=sc.nextInt();
                                System.out.println("Please enter Playlist Id to insert a Song: ");
                                pli.displayPlayLists(userId);
                                int playlistId=sc.nextInt();
                                pli.insertSong(playlistId,songId);
                                break;
                            case 3:
                                pi.displaypod(podList);
                                System.out.println("Enter PodcastId: ");
                                int podId= sc.nextInt();
                                System.out.println("Please enter Playlist Id to insert a Podcast: ");
                                pli.displayPlayLists(userId);
                                int playlistId1=sc.nextInt();
                                pli.insertPodCast(playlistId1,podId);
                                break;
                            case 4:
                                si.display(songList);
                                System.out.println("Enter the Album name:");
                                String album=sc.next();
                                System.out.println("Please enter Playlist Id to insert an album: ");
                                pli.displayPlayLists(userId);
                                int playlistId2=sc.nextInt();
                                pli.insertAlbum(playlistId2,album);
                                break;
                            case 5:
                                pli.displayPlayLists(userId);
                                System.out.println("Enter the Playlist Id to delete: ");
                                int playlistId3=sc.nextInt();
                                pli.deletePlayListContent(playlistId3);
                                break;
                            case 6:
                                pli.displayPlayLists(userId);
                                break;
                            case 7:
                                pli.displayPlayLists(userId);
                                System.out.println("Enter the Playlist Id to Display: ");
                                int playlistId4=sc.nextInt();
                                pli.displayPlayListContent(playlistId4);
                                break;
                            case 8:break;
                            default:
                                System.out.println("Incorrect choice!");
                                break;
                        }
                    } while (choice3 != 8);


                case 4:
                      do {
                          System.out.println("Choose an option to Play Music:-\n1.Playlists\n2.songs\n3.podcasts\n4.Exit");
                          choice4 = sc.nextInt();
                          switch (choice4) {
                              case 1:
                                  pli.displayPlayLists(userId);
                                  System.out.println("\nThese are available playlists \nEnter playlist id to play music:\n");
                                  int playId = sc.nextInt();
                                  int songId, podId;
                                  List<Audio> audioList = new ArrayList<>();
                                  Statement s = conn.createStatement();
                                  Statement s1 = conn.createStatement();
                                  ResultSet rs = s.executeQuery("select songid,podid from playlistdata where playlistid=" + playId+"\n");
                                  while (rs.next()) {
                                      songId = rs.getInt(1);
                                      podId = rs.getInt(2);
                                      if (songId > 0) {
                                          ResultSet rs1 = s1.executeQuery("select songname,songpath from songs where songid=" + songId);
                                          while (rs1.next()) {
                                              audioList.add(new Audio(rs1.getString(1), rs1.getString(2)));
                                          }
                                      } else {
                                          ResultSet rs1 = s1.executeQuery("select podname,podpath from podcasts where poidid=" + podId);
                                          while (rs1.next()) {
                                              audioList.add(new Audio(rs1.getString(1), rs1.getString(2)));
                                          }
                                      }
                                  }
                                  pm.playerOperation(audioList);
                                  break;

                              case 2:
                                  List<Audio> audiolist1 = new ArrayList<>(0);
                                  Statement st = conn.createStatement();
                                  si.showAllSongs(songList);
                                  int i = 0;
                                  do {
                                      System.out.println("Enter songId add queue\nEnter 404 to stop adding");
                                      i=sc.nextInt();
                                      if(i!=404){
                                          ResultSet rs1 = st.executeQuery("select songname,songpath from songs where songid="+i);
                                          while (rs1.next()){
                                              audiolist1.add(new Audio(rs1.getString(1),rs1.getString(2)));
                                          }
                                      }
                                  } while (i != 404);
                                   pm.playerOperation(audiolist1);
                                   break;
                              case 3:
                                  List<Audio> audiolist2 = new ArrayList<>();
                                  Statement st1 = conn.createStatement();
                                  pi.displayPodCastList(podList);
                                  int j = 0;
                                  do {
                                      System.out.println("Enter podId add quueue\nEnter404 to stop adding");
                                      j=sc.nextInt();
                                      if(j!=404){
                                          ResultSet rs1 = st1.executeQuery("select podname,podpath from podcasts where poidid="+j);
                                          while (rs1.next()){
                                              audiolist2.add(new Audio(rs1.getString(1),rs1.getString(2)));
                                          }
                                      }
                                  } while (j != 404);
                                  pm.playerOperation(audiolist2);
                                  break;
                          }

                      }while (choice4!=4);

                case 5:
                    break;
            }
        }while (choice!=5);

    }
}

