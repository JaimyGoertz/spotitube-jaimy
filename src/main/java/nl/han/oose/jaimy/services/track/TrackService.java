package nl.han.oose.jaimy.services.track;

import nl.han.oose.jaimy.entity.track.TrackOverview;

import javax.naming.AuthenticationException;

public interface TrackService {

    TrackOverview getAllAvailableTracksForPlaylist(String token, int playlistId) throws AuthenticationException;

}
