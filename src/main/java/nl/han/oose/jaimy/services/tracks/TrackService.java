package nl.han.oose.jaimy.services.tracks;

import nl.han.oose.jaimy.entity.tracks.TrackOverview;

import javax.naming.AuthenticationException;

public interface TrackService {

    TrackOverview getAllAvailableTracksForPlaylist(String token, int playlistId) throws AuthenticationException;

}
