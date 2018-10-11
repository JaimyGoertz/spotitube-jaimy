package nl.han.oose.jaimy.Login;

import nl.han.oose.jaimy.Account;
import nl.han.oose.jaimy.UserToken;

import javax.security.auth.login.LoginException;

public interface LoginService {
    UserToken login(Account user) throws LoginException;
}
