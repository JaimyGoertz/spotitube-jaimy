package nl.han.oose.jaimy.Playlists;

import nl.han.oose.jaimy.TrackOverview;

import javax.ws.rs.NotFoundException;
import java.util.List;

public class PlaylistService {

    public TrackOverview service(List<Playlist> playlistList, int id) throws NotFoundException {
        for (Playlist playlist : playlistList) {

            if (id == playlist.getId()) {
                return new TrackOverview(playlist.getTrack());
            } else {
                return new TrackOverview();
            }
        }
        throw new NotFoundException("Track failed");
    }
}
