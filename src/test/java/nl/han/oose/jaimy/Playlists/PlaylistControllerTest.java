package nl.han.oose.jaimy.Playlists;

import nl.han.oose.jaimy.controllers.playlist.PlaylistController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistControllerTest {

    @Mock
//    private PlaylistServiceImpl playlistService;

    @InjectMocks
    private PlaylistController sut;

//    @Test
//    public void testStatusOKOnPlaylistRequest() throws AccountException {
//        PlaylistOverview playlistOverview = new PlaylistOverview(playlistService.playlistList);
//        Mockito.when(playlistService.getPlaylists(Mockito.any(), Mockito.any())).thenReturn(playlistOverview);
//
//        Response playlistResponse = sut.getPlaylist("1234-1234-1234");
//
//        assertEquals(Response.Status.OK.getStatusCode(), playlistResponse.getStatus());
//        assertEquals(playlistOverview, playlistResponse.getEntity());
//    }

    @Test
    public void TestIfUsertokenIsRight() {

    }


}