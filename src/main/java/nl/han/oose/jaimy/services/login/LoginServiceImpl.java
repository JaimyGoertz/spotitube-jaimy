package nl.han.oose.jaimy.services.login;


import nl.han.oose.jaimy.entity.account.Account;
import nl.han.oose.jaimy.entity.account.UserToken;
import nl.han.oose.jaimy.persistence.account.AccountDAO;
import nl.han.oose.jaimy.persistence.token.TokenDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import java.util.Random;

@Default
public class LoginServiceImpl implements LoginService {

    @Inject
    AccountDAO accountDAO = new AccountDAO();
    TokenDAO tokenDAO = new TokenDAO();

    @Override
    public UserToken login(Account user) throws LoginException {
        for (Account account : accountDAO.getAllAccounts()) {
            if (user.getPassword().equals(account.getPassword()) && user.getUser().equals(account.getUser())) {
                return tokenDAO.createUserToken(generateUserToken(), user);
            }
        }
        throw new LoginException("Login failed");
    }

    public String generateUserToken() {
        Random rand = new Random();
        int random1 = rand.nextInt((9999 - 1000) + 1) + 1000;
        int random2 = rand.nextInt((9999 - 1000) + 1) + 1000;
        int random3 = rand.nextInt((9999 - 1000) + 1) + 1000;
        return random1 + "-" + random2 + "-" + random3;
    }

}
