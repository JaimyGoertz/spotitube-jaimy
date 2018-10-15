package nl.han.oose.jaimy.services.playlist;

import nl.han.oose.jaimy.entity.playlist.Playlist;
import nl.han.oose.jaimy.entity.tracks.Track;
import nl.han.oose.jaimy.entity.tracks.TrackOverview;

import javax.enterprise.inject.Default;
import javax.security.auth.login.AccountException;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistServiceImpl implements PlaylistService {

    List<Playlist> playlistList = new ArrayList<>();
    List<Track> tracks1 = new ArrayList<>();
    List<Track> tracks2 = new ArrayList<>();

    public PlaylistServiceImpl() {
        tracks1.add(new Track(1, "Song for someone", "The Frames", 350, "The Cost", 0, null, null, false));
        tracks1.add(new Track(2, "The Cost", "The Frames", 423, null, 37, "10-01-2015", "Title song from the album The Cost", true));

        playlistList.add(new Playlist(1, "Death metal", true, tracks1));
        playlistList.add(new Playlist(2, "Pop", true, tracks2));
    }

    @Override
    public TrackOverview getPlaylists(int id, String userToken) throws AccountException {
        for (Playlist playlist : playlistList) {

            if (id == playlist.getId()) {
                if ("1234-1234-1234".equals(userToken)) {
                    return new TrackOverview(playlist.getTrack());
                } else {
                    throw new AccountException();
                }
            } else {
                return new TrackOverview();
            }
        }
        return null;
    }


    @Override
    public List<Playlist> getPlaylists() {
        return playlistList;
    }
}
