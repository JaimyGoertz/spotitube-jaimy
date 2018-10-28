package nl.han.oose.jaimy.services.track;

import nl.han.oose.jaimy.entity.account.UserToken;
import nl.han.oose.jaimy.entity.tracks.TrackOverview;
import nl.han.oose.jaimy.persistence.token.TokenDAO;
import nl.han.oose.jaimy.persistence.tracks.TrackDAO;
import nl.han.oose.jaimy.services.tracks.TrackServiceImpl;
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
public class TrackServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private TrackDAO trackDAO;

    @Mock
    private TokenDAO tokenDAO;

    @InjectMocks
    private TrackServiceImpl sut;

    //Test getTracksNotInPlaylist
    @Test
    public void testGetTracksNotInPlaylistReturnsTrackOverviewIfTokenIsCorrect() throws AuthenticationException {
        TrackOverview trackOverview = new TrackOverview();
        UserToken userToken = new UserToken("1234-1234-1234", "Nick");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(true);
        Mockito.when(trackDAO.getAllAvailableTracksForPlaylist(Mockito.anyInt())).thenReturn(trackOverview);

        assertEquals(trackOverview, sut.getAllAvailableTracksForPlaylist("123", 1));
    }

    @Test
    public void testGetTracksNotInPlaylistReturnsExceptionIfTokenIsIncorrect() throws AuthenticationException {
        UserToken userToken = new UserToken("1234-1234-1234", "Nick");

        thrown.expect(AuthenticationException.class);
        thrown.expectMessage("Usertoken incorrect");

        Mockito.when(tokenDAO.getUserToken(Mockito.any())).thenReturn(userToken);
        Mockito.when(tokenDAO.isTokenValid(Mockito.any(UserToken.class))).thenReturn(false);

        sut.getAllAvailableTracksForPlaylist("1234-1234-1234", 1);
    }
}
