package nl.han.oose.jaimy.Playlists;

import nl.han.oose.jaimy.TrackOverview;

import javax.security.auth.login.AccountException;
import java.util.List;

public interface PlaylistService {
    TrackOverview getPlaylists(int id, String userToken) throws AccountException;

    List<Playlist> getPlaylists();
}
