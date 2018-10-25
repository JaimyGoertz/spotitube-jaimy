package nl.han.oose.jaimy.services.playlist;

import nl.han.oose.jaimy.entity.account.UserToken;
import nl.han.oose.jaimy.entity.playlist.Playlist;
import nl.han.oose.jaimy.entity.playlist.PlaylistOverview;
import nl.han.oose.jaimy.entity.tracks.Track;
import nl.han.oose.jaimy.entity.tracks.TrackOverview;
import nl.han.oose.jaimy.persistence.playlist.PlaylistDAO;
import nl.han.oose.jaimy.persistence.token.TokenDAO;
import nl.han.oose.jaimy.persistence.tracks.TrackDAO;

import javax.enterprise.inject.Default;
import javax.naming.AuthenticationException;
import javax.security.auth.login.AccountException;

@Default
public class PlaylistServiceImpl implements PlaylistService {

    PlaylistDAO playlistDAO = new PlaylistDAO();
    TrackDAO trackDAO = new TrackDAO();
    TokenDAO tokenDAO = new TokenDAO();

    public PlaylistServiceImpl() {
    }

    @Override
    public TrackOverview getPlaylistTracks(int id, String Token) throws AccountException {
        TrackOverview tracks = trackDAO.getAllTracksFromPlaylist(id);
        UserToken userToken = tokenDAO.getUserToken(Token);
        if (tokenDAO.isTokenValid(userToken)) {
            return tracks;
        } else {
            throw new AccountException();
        }

    }

    @Override
    public PlaylistOverview getPlaylists() {
        return playlistDAO.getAllPlaylists();
    }

    public PlaylistOverview editPlaylistName(Playlist playlist, String token) throws Exception {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            return playlistDAO.editPlaylistName(playlist);
        } else {
            throw new Exception();
        }
    }

    @Override
    public PlaylistOverview deletePlaylist(int playlistId, String token) throws Exception {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            return playlistDAO.deletePlaylist(playlistId);
        } else {
            throw new Exception();
        }
    }

    @Override
    public PlaylistOverview createPlaylist(Playlist playlist, String token) throws Exception {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            playlistDAO.createPlaylist(playlist);
            return playlistDAO.getAllPlaylists();
        } else {
            throw new Exception();
        }
    }

    @Override
    public TrackOverview addTrackToPlaylist(String token, int playlistId, Track track) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            playlistDAO.addTrackToPlaylist(playlistId, track);
            return trackDAO.getAllAvailableTracksForPlaylist(playlistId);
        } else {
            throw new AuthenticationException("Token incorrect");
        }
    }

    @Override
    public TrackOverview deleteTrack(String token, int playlistId, int trackId) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            trackDAO.deleteTrack(playlistId, trackId);
            return trackDAO.getAllAvailableTracksForPlaylist(playlistId);
        } else {
            throw new AuthenticationException("Token incorrect");
        }
    }

}
