package nl.han.oose.jaimy.controllers.track;

import nl.han.oose.jaimy.services.track.TrackService;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/track")
public class TrackController {

    @Inject
    private TrackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksNotInPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") int playlistId) {
        try {
            return Response.status(Response.Status.OK).entity(trackService.getAllAvailableTracksForPlaylist(token, playlistId)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
