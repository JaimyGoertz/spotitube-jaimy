package nl.han.oose.jaimy.Playlists;


import nl.han.oose.jaimy.Track;

public class Playlist {
    private int id;
    private String name;
    private boolean owner;
    private Track track;

    public Playlist() {

    }

    public Playlist(int id, String name, boolean owner, Track track) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.track = track;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}
