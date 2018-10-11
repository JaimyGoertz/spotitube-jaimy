package nl.han.oose.jaimy.Playlists;

import nl.han.oose.jaimy.TrackOverview;

import javax.ws.rs.NotFoundException;
import java.util.List;

public interface PlaylistService {
    TrackOverview getPlaylists(List<Playlist> playlistList, int id, String userToken) throws NotFoundException;

    List<Playlist> getPlaylists();
}
