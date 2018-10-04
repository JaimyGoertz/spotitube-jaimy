package nl.han.oose.jaimy;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/playlists")
public class PlaylistController {
    List<Playlist> playlists = new ArrayList<>();

    public PlaylistController() {
        playlists.add(new Playlist(1, "Death metal", true, new Tracks()));
        playlists.add(new Playlist(1, "Pop", true, new Tracks()));
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylist() {
        return Response.ok().entity(new PlaylistOverview(playlists)).build();
    }

}
