package nl.han.oose.jaimy.services.playlist;


import nl.han.oose.jaimy.entity.account.UserToken;
import nl.han.oose.jaimy.entity.playlist.Playlist;
import nl.han.oose.jaimy.entity.playlist.PlaylistOverview;
import nl.han.oose.jaimy.entity.tracks.Track;
import nl.han.oose.jaimy.entity.tracks.TrackOverview;
import nl.han.oose.jaimy.persistence.playlist.PlaylistDAO;
import nl.han.oose.jaimy.persistence.token.TokenDAO;
import nl.han.oose.jaimy.persistence.tracks.TrackDAO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.naming.AuthenticationException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private TrackDAO trackDAO;

    @Mock
    private PlaylistDAO playlistDAO;

    @Mock
    private TokenDAO tokenDAO;

    @InjectMocks
    private PlaylistServiceImpl sut;

    //Tests getAllTracksFromPlaylist
    @Test
    public void testGetTracksFromPlaylistReturnsTracksOverviewIfTokenIsCorrect() throws AuthenticationException {
        UserToken userToken = new UserToken("Jaimy", "1234-1234-1234");
        TrackOverview trackOverview = new TrackOverview();

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(trackDAO.getAllTracksFromPlaylist(Mockito.anyInt())).thenReturn(trackOverview);

        assertEquals(trackOverview, sut.getPlaylistTracks(1, "1234-1234-1234"));
    }

    @Test
    public void testGetTracksFromPlaylistReturnsExceptionIfTokenIsIncorrect() throws AuthenticationException {
        UserToken userToken = new UserToken("Jaimy", "1234-1234-1234");

        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.getPlaylistTracks(1, "1234-1234-1234");
    }

    //Tests getAllPlaylists
    @Test
    public void testGetPlaylistsReturnsPlaylistOverviewIfTokenIsCorrect() throws AuthenticationException {
        PlaylistOverview playlistOverview = new PlaylistOverview();
        UserToken userToken = new UserToken("Jaimy", "1234-1234-1234");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAO.getAllPlaylists()).thenReturn(playlistOverview);

        assertEquals(playlistOverview, sut.getAllPlaylists("1234-1234-12342"));
    }

    @Test
    public void testGetPlaylistsReturnsExceptionIfTokenIsIncorrect() throws AuthenticationException {
        UserToken userToken = new UserToken("Jaimy", "1234-1234-1234");

        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.getAllPlaylists("1234-1234-1234");
    }

    //Tests editPlaylist
    @Test
    public void testEditPlaylistReturnsPlaylistOverviewIfTokenIsCorrect() throws AuthenticationException {
        PlaylistOverview playlistOverview = new PlaylistOverview();
        UserToken userToken = new UserToken("1234-1234-1234", "Nick");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAO.getAllPlaylists()).thenReturn(playlistOverview);

        assertEquals(playlistOverview, sut.editPlaylistName(new Playlist(), "1234-1234-1234"));
    }

    @Test
    public void testEditPlaylistReturnsExceptionIfTokenIsIncorrect() throws AuthenticationException {
        UserToken userToken = new UserToken("Jaimy", "1234-1234-1234");

        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.editPlaylistName(new Playlist(), "1234-1234-1234");
    }

    //Tests deletePlaylist
    @Test
    public void testDeletePlaylistReturnsPlaylistOverviewIfTokenIsCorrect() throws AuthenticationException {
        PlaylistOverview playlistOverview = new PlaylistOverview();
        UserToken token = new UserToken("Jaimy", "1234-1234-1234");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(token);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAO.getAllPlaylists()).thenReturn(playlistOverview);

        assertEquals(playlistOverview, sut.deletePlaylist(1, "1234-1234-1234"));
    }

    @Test
    public void testDeletePlaylistReturnsExceptionIfTokenIsIncorrect() throws AuthenticationException {
        UserToken token = new UserToken("Jaimy", "1234-1234-1234");

        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(token);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.deletePlaylist(1, "1234-1234-1234");
    }

    //Tests addPlaylist
    @Test
    public void testAddPlaylistReturnsPlaylistOverviewIfTokenIsCorrect() throws AuthenticationException {
        PlaylistOverview playlistOverview = new PlaylistOverview();
        UserToken token = new UserToken("Jaimy", "1234-1234-1234");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(token);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(playlistDAO.getAllPlaylists()).thenReturn(playlistOverview);

        assertEquals(playlistOverview, sut.createPlaylist(new Playlist(), "1234-1234-1234"));
    }

    @Test
    public void testAddPlaylistReturnsExceptionIfTokenIsIncorrect() throws AuthenticationException {
        UserToken userToken = new UserToken("Jaimy", "1234-1234-1234");

        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.createPlaylist(new Playlist(), "1234-1234-1234");
    }

    //Tests addTrackToPlaylist
    @Test
    public void testAddTrackToPlaylistReturnsTrackOverviewIfTokenIsCorrect() throws AuthenticationException {
        TrackOverview trackOverview = new TrackOverview();
        UserToken userToken = new UserToken("Jaimy", "1234-1234-1234");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(trackDAO.getAllTracksFromPlaylist(Mockito.anyInt())).thenReturn(trackOverview);

        assertEquals(trackOverview, sut.addTrackToPlaylist("1234-1234-1234", 1, new Track()));
    }

    @Test
    public void testAddTrackToPlaylistReturnsExceptionIfTokenIsIncorrect() throws AuthenticationException {
        UserToken userToken = new UserToken("Jaimy", "1234-1234-1234");

        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.addTrackToPlaylist("1234-1234-1234", 1, new Track());
    }

    //Tests deleteTrack
    @Test
    public void testDeleteTrackReturnsTrackOverviewIfTokenIsCorrect() throws AuthenticationException {
        TrackOverview trackOverview = new TrackOverview();
        UserToken userToken = new UserToken("Jaimy", "1234-1234-1234");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(trackDAO.getAllTracksFromPlaylist(Mockito.anyInt())).thenReturn(trackOverview);

        assertEquals(trackOverview, sut.deleteTrack("1234-1234-1234", 1, 1));
    }

    @Test
    public void testDeleteTrackReturnsExceptionIfTokenIsincorrect() throws AuthenticationException {
        UserToken userToken = new UserToken("Jaimy", "1234-1234-1234");

        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.deleteTrack("1234-1234-1234", 1, 1);
    }
}
