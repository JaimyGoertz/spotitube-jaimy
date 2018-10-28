package nl.han.oose.jaimy.services.login;

import nl.han.oose.jaimy.entity.account.Account;
import nl.han.oose.jaimy.entity.account.UserToken;
import nl.han.oose.jaimy.persistence.account.AccountDAO;
import nl.han.oose.jaimy.persistence.token.TokenDAO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private AccountDAO accountDAO;

    @Mock
    private TokenDAO tokenDAO;

    @InjectMocks
    private LoginServiceImpl sut;

    //Tests login
    @Test
    public void testTokenIsCreatedWhenLoginIsCorrect() throws LoginException {
        Account account = new Account("Jaimy", "pass");
        UserToken userToken = new UserToken("Jaimy", "1234-1234-1234");
        Mockito.when(accountDAO.getAccount(Mockito.any())).thenReturn(account);
        Mockito.when(tokenDAO.createUserToken(Mockito.any())).thenReturn(userToken);


        assertEquals("1234-1234-1234", userToken.getToken());
        assertEquals("Jaimy", userToken.getUser());
    }

    @Test
    public void testExceptionIsThrownWhenLoginIsIncorrect() throws LoginException {
        thrown.expect(LoginException.class);
        thrown.expectMessage("Login failed");

        Account badAccount = new Account("Jaimy", "wrongPass");
        Account goodAccount = new Account("Jaimy", "pass");
        Mockito.when(accountDAO.getAccount(Mockito.any())).thenReturn(goodAccount);
        sut.login(badAccount);
    }
}
