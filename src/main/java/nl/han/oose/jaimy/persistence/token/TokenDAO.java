package nl.han.oose.jaimy.persistence.token;

import nl.han.oose.jaimy.entity.account.UserToken;
import nl.han.oose.jaimy.persistence.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenDAO {


    private ConnectionFactory connectionFactory;

    public TokenDAO() {
        connectionFactory = new ConnectionFactory();
    }

    public UserToken getUserToken(String inputToken) {
        UserToken usertoken = null;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_token WHERE token = ?");
        ) {
            statement.setString(1, inputToken);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("user");
                String token = resultSet.getString("token");
                usertoken = new UserToken(name, token);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usertoken;
    }

    public boolean isTokenValid(UserToken userToken) {

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM user_token WHERE token = ? AND user = ?");
        ) {
            preparedStatement.setString(1, userToken.getToken());
            preparedStatement.setString(2, userToken.getUser());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("user");
                String token = resultSet.getString("token");
                if (token.equals(userToken.getToken())) {
                    return true;
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

//    public UserToken createNewTokenForUser(String user) {
//        UserToken userToken;
//
//        try (
//                Connection connection = connectionFactory.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(
//                        "INSERT INTO user_token (user, token, expiry_date) VALUES (?,?,?)");
//        ) {
//            String token = UUID.randomUUID().toString();
//            LocalDateTime expiryDate = LocalDateTime.now().plusHours(24);
//
//            preparedStatement.setString(1, token);
//            preparedStatement.setString(2, user);
//            preparedStatement.setString(3, expiryDate.toString());
//            preparedStatement.execute();
//
//            userToken = new UserToken(token, user);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return userToken;
//    }
}
