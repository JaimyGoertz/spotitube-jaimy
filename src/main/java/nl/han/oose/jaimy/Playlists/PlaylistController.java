package nl.han.oose.jaimy.Playlists;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/playlists")
public class PlaylistController {


    @Inject
    PlaylistService playlistService;

    public PlaylistController() {
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylist() {
        return Response.ok().entity(new PlaylistOverview(playlistService.getPlaylists())).build();
    }

    @GET
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksInPlaylist(@PathParam("id") final int id, @QueryParam("token") String userToken) {
        try {
            System.out.println(userToken);
            return Response.ok().entity(playlistService.getPlaylists(playlistService.getPlaylists(), id, userToken)).build();

        } catch (NotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).build();

        }
    }
}
