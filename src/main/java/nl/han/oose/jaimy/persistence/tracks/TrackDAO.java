package nl.han.oose.jaimy.persistence.tracks;


import nl.han.oose.jaimy.persistence.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO {

    private ConnectionFactory connectionFactory;

    public TrackDAO() {
        connectionFactory = new ConnectionFactory();
    }

    public List<Integer> getAllTracksFromPlaylist(int id) {
        List<Integer> trackIds = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT track_id FROM playlist_tracks WHERE playlist_tracks.playlist_id = ?");
        ) {
            ResultSet resultSet = statement.executeQuery();
            statement.setInt(1, id);
            while (resultSet.next()) {
                int trackid = resultSet.getInt("id");
                trackIds.add(trackid);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return trackIds;
    }

//public int getTrackids(){
//    List<Track> tracks = new ArrayList<>();
//    try (
//            Connection connection = connectionFactory.getConnection();
//            PreparedStatement statement = connection.prepareStatement("SELECT track_id FROM playlist_tracks WHERE playlist_tracks.playlist_id = ?");
//    ) {
//        ResultSet resultSet = statement.executeQuery();
//        statement.setInt(1, id);
//        while (resultSet.next()) {
//            int trackid = resultSet.getInt("id");
//        }
//    } catch (SQLException e) {
//        throw new RuntimeException(e);
//    }
//
//    return tracks;
//}
//
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