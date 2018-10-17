package nl.han.oose.jaimy.entity.playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistOverview {

    private List<Playlist> playlists = new ArrayList<>();
    private int length;

    public PlaylistOverview() {

    }

    public PlaylistOverview(List<Playlist> playlists, int length) {
        this.playlists = playlists;
        this.length = 100;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
