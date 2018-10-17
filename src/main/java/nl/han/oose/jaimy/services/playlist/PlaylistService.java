package nl.han.oose.jaimy.services.playlist;

import nl.han.oose.jaimy.entity.playlist.Playlist;
import nl.han.oose.jaimy.entity.tracks.TrackOverview;

import javax.security.auth.login.AccountException;
import java.util.List;

public interface PlaylistService {

    TrackOverview getPlaylistTracks(int id, String userToken) throws AccountException;

    List<Playlist> getPlaylists();

    List<Playlist> editPlaylistName(Playlist playlist, String token) throws Exception;

    List<Playlist> deletePlaylist(Playlist playlist, String token) throws Exception;

    List<Playlist> createPlaylist(int id, Boolean owner, Playlist playlist, String userToken) throws Exception;
}
