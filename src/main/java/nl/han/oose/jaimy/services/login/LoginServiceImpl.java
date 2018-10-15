package nl.han.oose.jaimy.services.login;


import nl.han.oose.jaimy.entity.account.Account;
import nl.han.oose.jaimy.entity.account.UserToken;
import nl.han.oose.jaimy.persistence.AccountDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;

@Default
public class LoginServiceImpl implements LoginService {

    @Inject
    AccountDAO accountDAO = new AccountDAO();

    @Override
    public UserToken login(Account user) throws LoginException {
        for (Account account : accountDAO.getAllAccounts()) {
            if (user.getPassword().equals(account.getPassword()) && user.getUser().equals(account.getUser())) {
                return new UserToken("Jaimy", "1234-1234-1234");
            } else {
            }
        }
        return null;
    }

}
