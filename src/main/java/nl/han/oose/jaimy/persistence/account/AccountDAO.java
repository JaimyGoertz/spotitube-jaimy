package nl.han.oose.jaimy.persistence.account;


import nl.han.oose.jaimy.entity.account.Account;
import nl.han.oose.jaimy.persistence.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    private ConnectionFactory connectionFactory;

    public AccountDAO() {
        connectionFactory = new ConnectionFactory();
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM user");
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String user = resultSet.getString("user");
                String password = resultSet.getString("password");
                accounts.add(new Account(user, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    public Account getAccount(String username) {
        Account account = null;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement getAccountStatement = connection.prepareStatement("SELECT * FROM user us WHERE us.user  = ?");
        ) {
            getAccountStatement.setString(1, username);
            ResultSet accountResult = getAccountStatement.executeQuery();

            while (accountResult.next()) {
                String user = accountResult.getString("user");
                String password = accountResult.getString("password");

                account = new Account(user, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }


}
