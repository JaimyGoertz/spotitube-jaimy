package nl.han.oose.jaimy.services.playlist;

import nl.han.oose.jaimy.entity.account.UserToken;
import nl.han.oose.jaimy.entity.playlist.Playlist;
import nl.han.oose.jaimy.entity.tracks.Track;
import nl.han.oose.jaimy.entity.tracks.TrackOverview;
import nl.han.oose.jaimy.persistence.playlist.PlaylistDAO;
import nl.han.oose.jaimy.persistence.token.TokenDAO;
import nl.han.oose.jaimy.persistence.tracks.TrackDAO;

import javax.enterprise.inject.Default;
import javax.security.auth.login.AccountException;
import java.util.List;

@Default
public class PlaylistServiceImpl implements PlaylistService {

    PlaylistDAO playlistDAO = new PlaylistDAO();
    TrackDAO trackDAO = new TrackDAO();
    TokenDAO tokenDAO = new TokenDAO();

    public PlaylistServiceImpl() {
    }

    @Override
    public TrackOverview getPlaylistTracks(int id, String Token) throws AccountException {
        List<Track> tracks = trackDAO.getAllTracksFromPlaylist(id);
        UserToken userToken = tokenDAO.getUserToken(Token);
        if (tokenDAO.isTokenValid(userToken)) {
            return new TrackOverview(tracks);
        } else {
            throw new AccountException();
        }

    }

    @Override
    public List<Playlist> getPlaylists() {
        return playlistDAO.getAllPlaylists();
    }

    public List<Playlist> editPlaylistName(Playlist playlist, String token) throws Exception {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            return playlistDAO.editPlaylistName(playlist);
        } else {
            throw new Exception();
        }
    }

    @Override
    public List<Playlist> deletePlaylist(Playlist playlist, String token) throws Exception {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            return playlistDAO.deletePlaylist(playlist);
        } else {
            throw new Exception();
        }
    }

    @Override
    public List<Playlist> createPlaylist(int id, Boolean owner, Playlist playlist, String token) throws Exception {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            return playlistDAO.createPlaylist(id, owner, playlist);
        } else {
            throw new Exception();
        }
    }
}
