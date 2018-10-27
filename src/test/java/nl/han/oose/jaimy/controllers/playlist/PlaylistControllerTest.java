package nl.han.oose.jaimy.controllers.playlist;

import nl.han.oose.jaimy.entity.playlist.Playlist;
import nl.han.oose.jaimy.entity.playlist.PlaylistOverview;
import nl.han.oose.jaimy.entity.track.Track;
import nl.han.oose.jaimy.entity.track.TrackOverview;
import nl.han.oose.jaimy.services.playlist.PlaylistService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.naming.AuthenticationException;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistControllerTest {

    @Mock
    private PlaylistService playlistServiceMock;

    @InjectMocks
    private PlaylistController sut;

    //Tests for getPlaylists
    @Test
    public void testStatusGetPlaylistsOkOnCorrectToken() throws AuthenticationException {
        PlaylistOverview playlistOverview = new PlaylistOverview();

        Mockito.when(playlistServiceMock.getAllPlaylists(Mockito.any())).thenReturn(playlistOverview);
        Response response = sut.getPlaylists("");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlistOverview, response.getEntity());
    }

    @Test
    public void testStatusGetPlaylistsUnauthorizedOnIncorrectToken() throws AuthenticationException {
        Mockito.when(playlistServiceMock.getAllPlaylists(Mockito.any())).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.getPlaylists("1234-1234-1234");

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    // Tests for getTracksInPlaylist

    @Test
    public void testStatusGetContentInPlaylistOkOnCorrectToken() throws AuthenticationException {
        TrackOverview tracksOverview = new TrackOverview();

        Mockito.when(playlistServiceMock.getPlaylistTracks(Mockito.anyInt(), Mockito.any())).thenReturn(tracksOverview);
        Response response = sut.getTracksInPlaylist(1, "1234-1234-1234");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracksOverview, response.getEntity());
    }

    @Test
    public void testStatusGetTracksInPlaylistUnauthorizedOnIncorrectToken() throws AuthenticationException {
        Mockito.when(playlistServiceMock.getPlaylistTracks(Mockito.anyInt(), Mockito.any())).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.getTracksInPlaylist(1, "1234-1234-1234");

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    //Tests for deletePlaylist
    @Test
    public void testStatusDeletePlaylistNotFoundOnIncorrectToken() throws AuthenticationException {
        Mockito.when(playlistServiceMock.deletePlaylist(Mockito.anyInt(), Mockito.any())).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.deletePlaylist(1, "1234-1234-1234");

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testStatusDeletePlaylistOkOnCorrectToken() throws Exception {
        PlaylistOverview playlistOverview = new PlaylistOverview();

        Mockito.when(playlistServiceMock.deletePlaylist(Mockito.anyInt(), Mockito.any())).thenReturn(playlistOverview);
        Response response = sut.deletePlaylist(1, "1234-1234-1234");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlistOverview, response.getEntity());
    }

    //Tests for editPlaylistName
    @Test
    public void testStatusEditPlaylistNameOkOnCorrectToken() throws AuthenticationException {
        PlaylistOverview playlistOverview = new PlaylistOverview();

        Mockito.when(playlistServiceMock.editPlaylistName(Mockito.any(Playlist.class), Mockito.any())).thenReturn(playlistOverview);
        Response response = sut.editPlaylistName(new Playlist(), "1234-1234-1234");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlistOverview, response.getEntity());
    }

    @Test
    public void testStatusEditPlaylistNameUnauthorizedOnIncorrectToken() throws AuthenticationException {
        Mockito.when(playlistServiceMock.editPlaylistName(Mockito.any(Playlist.class), Mockito.any())).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.editPlaylistName(new Playlist(), "1234-1234-1234");

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    //Tests for deleteTrack
    @Test
    public void testStatusDeleteTrackOkOnCorrectToken() throws AuthenticationException {
        TrackOverview tracksOverview = new TrackOverview();

        Mockito.when(playlistServiceMock.deleteTrack(Mockito.any(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(tracksOverview);
        Response response = sut.deleteTrack("1234-1234-1234", 1, 1);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracksOverview, response.getEntity());
    }

    @Test
    public void testStatusDeleteTrackUnauthorizedOnIncorrectToken() throws AuthenticationException {
        Mockito.when(playlistServiceMock.deleteTrack(Mockito.any(), Mockito.anyInt(), Mockito.anyInt())).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.deleteTrack("1234-1234-1234", 1, 1);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    //Tests for createPlaylist
    @Test
    public void testStatusCreatePlaylistOkOnCorrectToken() throws AuthenticationException {
        PlaylistOverview playlistOverview = new PlaylistOverview();

        Mockito.when(playlistServiceMock.createPlaylist(Mockito.any(Playlist.class), Mockito.any())).thenReturn(playlistOverview);
        Response response = sut.createPlaylist(new Playlist(), "1234-1234-1234");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlistOverview, response.getEntity());
    }

    @Test
    public void testStatusCreatePlaylistUnauthorizedOnIncorrectToken() throws AuthenticationException {
        Mockito.when(playlistServiceMock.createPlaylist(Mockito.any(Playlist.class), Mockito.any())).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.createPlaylist(new Playlist(), "1234-1234-1234");

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }


    //Tests for addTrackToPlaylist
    @Test
    public void testStatusAddTrackToPlaylistOkOnCorrectToken() throws AuthenticationException {
        TrackOverview tracksOverview = new TrackOverview();

        Mockito.when(playlistServiceMock.addTrackToPlaylist(Mockito.any(), Mockito.anyInt(), Mockito.any(Track.class))).thenReturn(tracksOverview);
        Response response = sut.addTrackToPlaylist("1234-1234-1234", 1, new Track());

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracksOverview, response.getEntity());
    }

    @Test
    public void testStatusAddTrackToPlaylistUnauthorizedOnIncorrectToken() throws AuthenticationException {
        Mockito.when(playlistServiceMock.addTrackToPlaylist(Mockito.any(), Mockito.anyInt(), Mockito.any(Track.class))).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.addTrackToPlaylist("1234-1234-1234", 1, new Track());

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }


}