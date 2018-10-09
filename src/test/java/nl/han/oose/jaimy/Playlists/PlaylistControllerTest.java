package nl.han.oose.jaimy.Playlists;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistControllerTest {


    @InjectMocks
    private PlaylistController sut;

    @Test
    public void testStatusOKOnPlaylistRequest() {
        sut.playlistList.add(new Playlist(1, "test", false, sut.tracks1));

        Response playlistResponse = sut.getPlaylist();

        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
    }


}