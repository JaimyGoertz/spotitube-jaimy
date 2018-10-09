package nl.han.oose.jaimy.Playlists;


import nl.han.oose.jaimy.Track;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/playlists")
public class PlaylistController {
    List<Playlist> playlistList = new ArrayList<>();
    List<Track> tracks1 = new ArrayList<>();
    List<Track> tracks2 = new ArrayList<>();
    PlaylistService playlistService = new PlaylistService();

    public PlaylistController() {
        tracks1.add(new Track(1, "Song for someone", "The Frames", 350, "The Cost", 0, null, null, false));
        tracks1.add(new Track(2, "The Cost", "The Frames", 423, null, 37, "10-01-2015", "Title song from the album The Cost", true));

        playlistList.add(new Playlist(1, "Death metal", true, tracks1));
        playlistList.add(new Playlist(2, "Pop", true, tracks2));
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylist() {
        return Response.ok().entity(new PlaylistOverview(playlistList)).build();
    }

    @GET
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksInPlaylist(@PathParam("id") final int id, @QueryParam("token") String userToken) {
        try {
            System.out.println(userToken);
            return Response.ok().entity(playlistService.service(playlistList, id, userToken)).build();

        } catch (NotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).build();

        }
    }
}
