package nl.han.oose.jaimy.persistence.playlist;


import nl.han.oose.jaimy.entity.playlist.Playlist;
import nl.han.oose.jaimy.entity.tracks.Track;
import nl.han.oose.jaimy.persistence.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    private ConnectionFactory connectionFactory;

    public PlaylistDAO() {
        connectionFactory = new ConnectionFactory();
    }

    public List<Playlist> getAllPlaylists() {
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
        return playlists;
    }

    public List<Playlist> editPlaylistName(Playlist playlist) {
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

    public List<Playlist> deletePlaylist(Playlist playlist) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM playlist WHERE id = ?");
        ) {
            statement.setInt(1, playlist.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getAllPlaylists();
    }

    public List<Playlist> createPlaylist(int id, Boolean owner, Playlist playlist) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO playlist VALUES(?,?,?)");
        ) {
            statement.setInt(1, id);
            statement.setString(2, playlist.getName());
            statement.setBoolean(3, owner);
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


}