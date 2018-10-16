package nl.han.oose.jaimy.services.playlist;

import nl.han.oose.jaimy.entity.playlist.Playlist;
import nl.han.oose.jaimy.entity.tracks.Track;
import nl.han.oose.jaimy.entity.tracks.TrackOverview;
import nl.han.oose.jaimy.persistence.playlist.PlaylistDAO;
import nl.han.oose.jaimy.persistence.tracks.TrackDAO;

import javax.enterprise.inject.Default;
import javax.security.auth.login.AccountException;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistServiceImpl implements PlaylistService {

    List<Playlist> playlistList = new ArrayList<>();
    List<Track> tracks1 = new ArrayList<>();
    List<Track> tracks2 = new ArrayList<>();
    PlaylistDAO playlistDAO = new PlaylistDAO();
    TrackDAO trackDAO = new TrackDAO();

    public PlaylistServiceImpl() {
    }

    @Override
    public TrackOverview getPlaylists(int id, String userToken) throws AccountException {
        List<Track> tracks = trackDAO.getAllTracksFromPlaylist(id);
        if ("1234-1234-1234".equals(userToken)) {
            return new TrackOverview(tracks);
        } else {
            throw new AccountException();
        }

    }


    @Override
    public List<Playlist> getPlaylists() {
        return playlistDAO.getAllPlaylists();
    }
}
