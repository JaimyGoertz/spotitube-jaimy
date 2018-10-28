package nl.han.oose.jaimy.services.playlist;

import nl.han.oose.jaimy.entity.playlist.Playlist;
import nl.han.oose.jaimy.entity.playlist.PlaylistOverview;
import nl.han.oose.jaimy.entity.tracks.Track;
import nl.han.oose.jaimy.entity.tracks.TrackOverview;

import javax.naming.AuthenticationException;

public interface PlaylistService {

    TrackOverview getPlaylistTracks(int id, String userToken) throws AuthenticationException;

    PlaylistOverview editPlaylistName(Playlist playlist, String token) throws AuthenticationException;

    PlaylistOverview deletePlaylist(int id, String token) throws AuthenticationException;

    PlaylistOverview createPlaylist(Playlist playlist, String userToken) throws AuthenticationException;

    TrackOverview addTrackToPlaylist(String token, int playlistId, Track track) throws AuthenticationException;

    TrackOverview deleteTrack(String token, int playlistId, int trackId) throws AuthenticationException;

    PlaylistOverview getAllPlaylists(String token) throws AuthenticationException;
}
