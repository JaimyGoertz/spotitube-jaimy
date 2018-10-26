package nl.han.oose.jaimy.persistence.track;


import nl.han.oose.jaimy.entity.track.Track;
import nl.han.oose.jaimy.entity.track.TrackOverview;
import nl.han.oose.jaimy.persistence.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackDAO {

    private TrackOverview trackOverview = new TrackOverview();

    private ConnectionFactory connectionFactory;

    public TrackDAO() {
        connectionFactory = new ConnectionFactory();
    }

    public TrackOverview getAllTracksFromPlaylist(int id) {
        TrackOverview trackOverview = new TrackOverview();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM TRACK INNER JOIN playlist_tracks pt on track.id = pt.track_id WHERE playlist_id = ?")
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                try {
                    trackOverview = trackInfo(resultSet);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trackOverview;
    }

    public TrackOverview getAllAvailableTracksForPlaylist(int playlistId) {
        TrackOverview trackOverview = new TrackOverview();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM TRACK WHERE id NOT IN(SELECT track_id FROM playlist_tracks WHERE playlist_id = ?)")
        ) {
            statement.setInt(1, playlistId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                try {
                    trackOverview = trackInfo(resultSet);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trackOverview;
    }

    private TrackOverview trackInfo(ResultSet resultSet) throws Exception {
        int id = resultSet.getByte("id");
        String title = resultSet.getString("title");
        String performer = resultSet.getString("performer");
        int duration = resultSet.getInt("duration");
        String album = resultSet.getString("album");
        int playcount = resultSet.getInt("playcount");
        String publicationDate = resultSet.getString("publicationDate");
        String description = resultSet.getString("description");
        Boolean offlineAvailable = resultSet.getBoolean("offlineAvailable");

        trackOverview.getTracks().add(new Track(id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable));
        return trackOverview;
    }

    public void deleteTrack(int playlistId, int trackId) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM playlist_tracks WHERE playlist_id = ? AND track_id = ?");
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, trackId);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}