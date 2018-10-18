package nl.han.oose.jaimy.services.playlist;

import nl.han.oose.jaimy.entity.playlist.Playlist;
import nl.han.oose.jaimy.entity.playlist.PlaylistOverview;
import nl.han.oose.jaimy.entity.tracks.Track;
import nl.han.oose.jaimy.entity.tracks.TrackOverview;

import javax.naming.AuthenticationException;
import javax.security.auth.login.AccountException;

public interface PlaylistService {

    TrackOverview getPlaylistTracks(int id, String userToken) throws AccountException;

    PlaylistOverview getPlaylists();

    PlaylistOverview editPlaylistName(Playlist playlist, String token) throws Exception;

    PlaylistOverview deletePlaylist(int id, String token) throws Exception;

    PlaylistOverview createPlaylist(Playlist playlist, String userToken) throws Exception;

    TrackOverview addTrackToPlaylist(String token, int playlistId, Track track) throws AuthenticationException;
}
