package nl.han.oose.jaimy.services.track;

import nl.han.oose.jaimy.entity.account.UserToken;
import nl.han.oose.jaimy.entity.track.TrackOverview;
import nl.han.oose.jaimy.persistence.token.TokenDAO;
import nl.han.oose.jaimy.persistence.track.TrackDAO;

import javax.inject.Inject;
import javax.naming.AuthenticationException;

public class TrackServiceImpl implements TrackService {

    @Inject
    private TokenDAO tokenDAO;

    @Inject
    private TrackDAO trackDAO;

    @Override
    public TrackOverview getAllAvailableTracksForPlaylist(String token, int playlistId) throws AuthenticationException {
        UserToken userToken = tokenDAO.getUserToken(token);
        if (tokenDAO.isTokenValid(userToken)) {
            return trackDAO.getAllAvailableTracksForPlaylist(playlistId);
        } else {
            throw new AuthenticationException("Usertoken incorrect");
        }
    }
}
