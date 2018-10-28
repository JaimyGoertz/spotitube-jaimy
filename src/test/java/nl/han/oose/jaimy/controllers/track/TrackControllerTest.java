package nl.han.oose.jaimy.controllers.track;


import nl.han.oose.jaimy.controllers.tracks.TrackController;
import nl.han.oose.jaimy.entity.tracks.TrackOverview;
import nl.han.oose.jaimy.services.tracks.TrackService;
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
public class TrackControllerTest {

    @Mock
    private TrackService trackServiceMock;

    @InjectMocks
    private TrackController sut;

    //Tests for getAllAvailableTracksForPlaylist
    @Test
    public void testStatusOkAndTracklistEntityOnCorrectUsertokenGetAllAvailableTracksForPlaylist() throws AuthenticationException {
        TrackOverview trackOverview = new TrackOverview();

        Mockito.when(trackServiceMock.getAllAvailableTracksForPlaylist(Mockito.any(), Mockito.anyInt())).thenReturn(trackOverview);
        Response response = sut.getAllAvailableTracksForPlaylist("321321", 1);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(trackOverview, response.getEntity());
    }

    @Test
    public void testStatusForbiddenOnIncorrectUsertokengetAllAvailableTracksForPlaylist() throws AuthenticationException {
        Mockito.when(trackServiceMock.getAllAvailableTracksForPlaylist(Mockito.any(), Mockito.anyInt())).thenThrow(new AuthenticationException("Usertoken incorrect"));
        Response response = sut.getAllAvailableTracksForPlaylist("321321", 1);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }
}