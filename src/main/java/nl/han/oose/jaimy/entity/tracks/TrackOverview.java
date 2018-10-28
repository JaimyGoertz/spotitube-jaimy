package nl.han.oose.jaimy.entity.tracks;

import java.util.ArrayList;
import java.util.List;

public class TrackOverview {

    private List<Track> tracks = new ArrayList<>();


    public TrackOverview() {
//        tracks.add(new Track(1, "Song for someone", "The Frames", 350, "The Cost", 0, null, null, false));
//        tracks.add(new Track(2, "The Cost", "The Frames", 423, null, 37, "10-01-2015", "Title song from the album The Cost", true));
    }

    public TrackOverview(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
