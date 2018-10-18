package nl.han.oose.jaimy.controllers.playlist;


import nl.han.oose.jaimy.entity.playlist.Playlist;
import nl.han.oose.jaimy.entity.tracks.Track;
import nl.han.oose.jaimy.services.playlist.PlaylistService;

import javax.inject.Inject;
import javax.security.auth.login.AccountException;
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
        return Response.ok().entity(playlistService.getPlaylists()).build();
    }

    @GET
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksInPlaylist(@PathParam("id") final int id, @QueryParam("token") String userToken) {
        try {
            return Response.ok().entity(playlistService.getPlaylistTracks(id, userToken)).build();

        } catch (AccountException exception) {
            return Response.status(Response.Status.UNAUTHORIZED).build();

        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylistName(@PathParam("id") final int id, Playlist playlist, @QueryParam("token") String userToken) {
        try {
            return Response.ok().entity(playlistService.editPlaylistName(playlist, userToken)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") final int id, @QueryParam("token") String userToken) {
        try {
            return Response.ok().entity(playlistService.deletePlaylist(id, userToken)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPlaylist(Playlist playlist, @QueryParam("token") String userToken) {
        try {
            return Response.ok().entity(playlistService.createPlaylist(playlist, userToken)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String userToken, @PathParam("id") int playlistId, Track track) {
        try {
            return Response.ok().entity(playlistService.addTrackToPlaylist(userToken, playlistId, track)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
