package nl.han.oose.jaimy.Playlists;

import nl.han.oose.jaimy.TrackOverview;

import javax.ws.rs.NotFoundException;
import java.util.List;

public class PlaylistService {

    public TrackOverview service(List<Playlist> playlistList, int id, String usertToken) throws NotFoundException {
        for (Playlist playlist : playlistList) {

            if (id == playlist.getId() && "1234-1234-1234".equals(usertToken)) {
                return new TrackOverview(playlist.getTrack());
            } else {
                return new TrackOverview();
            }
        }
        throw new NotFoundException("Track failed");
    }
}
