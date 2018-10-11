package nl.han.oose.jaimy.Login;


import nl.han.oose.jaimy.Account;
import nl.han.oose.jaimy.UserToken;

import javax.enterprise.inject.Default;
import javax.security.auth.login.LoginException;

@Default
public class LoginServiceImpl implements LoginService {

    @Override
    public UserToken login(Account user) throws LoginException {
        if ("Jaimy".equals(user.getUser()) && "wachtwoord".equals(user.getPassword())) {
            return new UserToken("Jaimy Goertz", "1234-1234-1234");
        } else {
            throw new LoginException("Login failed");
        }
    }
}
