package nl.han.oose.jaimy.persistence.tracks;


import nl.han.oose.jaimy.entity.tracks.Track;
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

public class TrackDAO {

    private ConnectionFactory connectionFactory;

    public TrackDAO() {
        connectionFactory = new ConnectionFactory();
    }

    public List<Track> getAllTracksFromPlaylist(int id) {
        List<Track> tracks = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT id,title,performer,duration,album,playcount,publicationDate,description,offlineAvailable FROM track INNER JOIN playlist_tracks ON track.id = playlist_tracks.track_id WHERE playlist_tracks.playlist_id = ?");
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int trackid = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String performer = resultSet.getString("performer");
                int duration = resultSet.getInt("duration");
                String album = resultSet.getString("album");
                int playcount = resultSet.getInt("playcount");

                SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = oldFormat.parse(resultSet.getString("publicationDate"));
                SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");

                String publicationDate = newFormat.format(date);
                String description = resultSet.getString("description");
                Boolean offlineAvailable = resultSet.getBoolean("offlineAvailable");
                tracks.add(new Track(trackid, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return tracks;
    }

//    public void persistAccount(Account account) {
//        try (
//                Connection connection = connectionFactory.getConnection();
//                PreparedStatement statement = connection.prepareStatement(
//                        "INSERT INTO USER (user,password) VALUES (?,?)");
//        ) {
//            statement.setString(1, account.getUser());
//            statement.setString(2, account.getPassword());
//            statement.execute();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


}