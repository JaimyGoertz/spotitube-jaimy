package nl.han.oose.jaimy.persistence.token;

import nl.han.oose.jaimy.entity.account.UserToken;
import nl.han.oose.jaimy.persistence.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

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

    public UserToken createUserToken(String user) {
        deleteUserToken(user);
        UserToken userToken;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO user_token (user,token,expiry_date) VALUES (?,?,?)");
        ) {
            String token = UUID.randomUUID().toString();
            LocalDateTime expiryDate = LocalDateTime.now().plusHours(24);
            statement.setString(1, user);
            statement.setString(2, token);
            statement.setString(3, expiryDate.toString());
            statement.execute();

            userToken = new UserToken(user, token);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userToken;
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
            LocalDateTime currentDateTime = LocalDateTime.now();
            while (resultSet.next()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime expiryDate = LocalDateTime.parse(resultSet.getString("expiry_date"), formatter);
                if (expiryDate.isAfter(currentDateTime)) {
                    return true;
                } else {
                    deleteUserToken(userToken);
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public void deleteUserToken(UserToken userToken) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM user_token WHERE user = ?");
        ) {
            statement.setString(1, userToken.getUser());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUserToken(String user) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM user_token WHERE user = ?");
        ) {
            statement.setString(1, user);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
