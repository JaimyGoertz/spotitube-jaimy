package nl.han.oose.jaimy.services.login;

import nl.han.oose.jaimy.entity.account.Account;
import nl.han.oose.jaimy.entity.account.UserToken;

import javax.security.auth.login.LoginException;

public interface LoginService {
    UserToken login(Account user) throws LoginException;
}
