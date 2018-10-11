package nl.han.oose.jaimy.persistence;

import nl.han.oose.jaimy.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    private ConnectionFactory connectionFactory;

    public AccountDAO() {
        ConnectionFactory connectionfactory = new ConnectionFactory();
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT");
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
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

    public void persistAccount(Account account) {
        try {
            try (
                    Connection connection = connectionFactory.getConnection();
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO ACCOUNT (user,password) VALUES(?,?)")
            ) {
                statement.setString(1, account.getUser());
                statement.setString(1, account.getPassword());

                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
