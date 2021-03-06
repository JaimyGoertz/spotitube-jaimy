package nl.han.oose.jaimy.controllers.playlist;


import nl.han.oose.jaimy.entity.playlist.Playlist;
import nl.han.oose.jaimy.entity.tracks.Track;
import nl.han.oose.jaimy.services.playlist.PlaylistService;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
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
    public Response getPlaylists(@QueryParam("token") String userToken) {
        try {
            return Response.ok().entity(playlistService.getAllPlaylists(userToken)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @GET
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksInPlaylist(@PathParam("id") final int id, @QueryParam("token") String userToken) {
        try {
            return Response.ok().entity(playlistService.getPlaylistTracks(id, userToken)).build();

        } catch (AuthenticationException exception) {
            return Response.status(Response.Status.UNAUTHORIZED).build();

        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylistName(Playlist playlist, @QueryParam("token") String userToken) {
        try {
            return Response.ok().entity(playlistService.editPlaylistName(playlist, userToken)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") final int id, @QueryParam("token") String userToken) {
        try {
            return Response.ok().entity(playlistService.deletePlaylist(id, userToken)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPlaylist(Playlist playlist, @QueryParam("token") String userToken) {
        try {
            return Response.ok().entity(playlistService.createPlaylist(playlist, userToken)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, Track track) {
        try {
            return Response.status(Response.Status.OK).entity(playlistService.addTrackToPlaylist(token, playlistId, track)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @DELETE
    @Path("/{playlistId}/tracks/{trackId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrack(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId) {
        try {
            return Response.status(Response.Status.OK).entity(playlistService.deleteTrack(token, playlistId, trackId)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
