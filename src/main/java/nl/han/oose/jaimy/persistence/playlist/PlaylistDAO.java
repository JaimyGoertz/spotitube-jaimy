package nl.han.oose.jaimy.persistence.playlist;


import nl.han.oose.jaimy.entity.playlist.Playlist;
import nl.han.oose.jaimy.entity.playlist.PlaylistOverview;
import nl.han.oose.jaimy.entity.tracks.Track;
import nl.han.oose.jaimy.entity.tracks.TrackOverview;
import nl.han.oose.jaimy.persistence.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlaylistDAO {

    private ConnectionFactory connectionFactory;

    public PlaylistDAO() {
        connectionFactory = new ConnectionFactory();
    }

    public PlaylistOverview getAllPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        List<Track> tracks = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM playlist");
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Boolean owner = resultSet.getBoolean("owner");
                playlists.add(new Playlist(id, name, owner, tracks));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new PlaylistOverview(playlists);
    }

    public PlaylistOverview editPlaylistName(Playlist playlist) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE playlist SET name = ? WHERE id = ?");
        ) {
            statement.setString(1, playlist.getName());
            statement.setInt(2, playlist.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getAllPlaylists();
    }

    public PlaylistOverview deletePlaylist(int playlistId) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM playlist WHERE id = ?")
        ) {
            statement.setInt(1, playlistId);

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAllPlaylists();
    }

    public PlaylistOverview createPlaylist(Playlist playlist) {
        playlist.setId(4);
        playlist.setOwner(true);
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO playlist VALUES(?,?,?)");
        ) {
            statement.setInt(1, playlist.getId());
            statement.setString(2, playlist.getName());
            statement.setBoolean(3, playlist.getOwner());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getAllPlaylists();
    }

    public int getLengthOfPlaylist(Playlist playlist) {
        int Length = 0;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT SUM(duration) AS playlist_length\n" +
                                "    FROM track INNER JOIN playlist_tracks on track.id = playlist_tracks.track_id\n" +
                                "    WHERE playlist_tracks.playlist_id = ?")
        ) {

            preparedStatement.setInt(1, playlist.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Length += resultSet.getInt("playlist_length");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Length;
    }

    public TrackOverview getContentOfPlaylist(int playlistId) {
        TrackOverview trackOverview = new TrackOverview();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT track.id, track.title, " +
                        "track.performer,track.duration,track.album,track.playcount,track.publicationDate,track.description," +
                        "track.offlineAvailable\n" +
                        "FROM playlist_tracks INNER JOIN track ON playlist_tracks.track_id = track.id\n" +
                        "WHERE playlist_tracks.playlist_id = ?");
        ) {
            ResultSet resultSet = statement.executeQuery();
            statement.setInt(1, playlistId);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String performer = resultSet.getString("performer");
                int duration = resultSet.getInt("duration");
                String album = resultSet.getString("album");
                int playcount = resultSet.getInt("playcount");
                String publicationDate = null;

                if (!(null == (resultSet.getString("publication_date")))) {
                    SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = oldFormat.parse(resultSet.getString("publication_date"));
                    SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");

                    publicationDate = newFormat.format(date);
                }

                String description = resultSet.getString("description");
                boolean offlineAvailable = resultSet.getBoolean("offlineAvailable");
                trackOverview.getTracks().add(new Track(id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return trackOverview;

    }

    public void addTrackToPlaylist(int playlistId, Track track) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO playlist_tracks(playlist_id, track_id, offlineAvailable) VALUES(?,?,?)");
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setLong(2, track.getId());
            preparedStatement.setBoolean(3, track.getOfflineAvailable());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}