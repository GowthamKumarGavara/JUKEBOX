package model;

public class Playlist {
    int playlistId;
    String playlistName;
    String userEmailId;

    public Playlist() {}

    public Playlist(int playlistId, String playlistName, String userEmailId) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.userEmailId = userEmailId;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistId=" + playlistId +
                ", playlistName='" + playlistName + '\'' +
                ", userEmailId='" + userEmailId + '\'' +
                '}';
    }
}
