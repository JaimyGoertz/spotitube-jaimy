package nl.han.oose.jaimy.Playlists;


import nl.han.oose.jaimy.Track;
import nl.han.oose.jaimy.TrackOverview;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/playlists")
public class PlaylistController {
    List<Playlist> playlists = new ArrayList<>();
    TrackOverview trackOverview = new TrackOverview();

    public PlaylistController() {
        playlists.add(new Playlist(1, "Death metal", true, new Track()));
        playlists.add(new Playlist(2, "Pop", true, new Track()));
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylist() {
        return Response.ok().entity(new PlaylistOverview(playlists)).build();
    }

    @GET
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksInPlaylist(@PathParam("id") final String id) {
        return Response.ok().entity(new TrackOverview(trackOverview.getTracks())).build();
    }


}
