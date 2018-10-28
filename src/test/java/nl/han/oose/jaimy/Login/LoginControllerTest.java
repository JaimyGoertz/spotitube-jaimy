package nl.han.oose.jaimy.Login;

import nl.han.oose.jaimy.controllers.login.LoginController;
import nl.han.oose.jaimy.entity.account.Account;
import nl.han.oose.jaimy.entity.account.UserToken;
import nl.han.oose.jaimy.services.login.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController sut;

    @Test
    public void testStatusOKOnSuccessfulLogin() throws LoginException {
        UserToken userToken = new UserToken("", "");
        Mockito.when(loginService.login(Mockito.any())).thenReturn(userToken);

        Account account = new Account("", "");
        Response loginResponse = sut.login(account);

        assertEquals(Response.Status.OK.getStatusCode(), loginResponse.getStatus());
        assertEquals(userToken, loginResponse.getEntity());
    }

    @Test
    public void testStatusUnauthorizedOnFailedLogin() throws LoginException {
        Mockito.when(loginService.login(Mockito.any())).thenThrow(LoginException.class);

        Account account = new Account("", "");
        Response loginResponse = sut.login(account);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), loginResponse.getStatus());
    }
}