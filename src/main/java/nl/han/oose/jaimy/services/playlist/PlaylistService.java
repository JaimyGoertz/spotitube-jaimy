package nl.han.oose.jaimy.services.playlist;

import nl.han.oose.jaimy.entity.playlist.Playlist;
import nl.han.oose.jaimy.entity.tracks.TrackOverview;

import javax.security.auth.login.AccountException;
import java.util.List;

public interface PlaylistService {

    TrackOverview getPlaylists(int id, String userToken) throws AccountException;

    List<Playlist> getPlaylists();

}
