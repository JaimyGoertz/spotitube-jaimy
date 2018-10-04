package nl.han.oose.jaimy;


import javax.security.auth.login.LoginException;

public class LoginService {

    public UserToken login(Account user) throws LoginException {
        if ("Jaimy".equals(user.getUser()) && "wachtwoord".equals(user.getPassword())) {
            return new UserToken("Jaimy Goertz", "1234-1234-1234");
        } else {
            throw new LoginException("Login failed");
        }
    }
}
