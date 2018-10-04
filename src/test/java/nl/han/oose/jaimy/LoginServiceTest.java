package nl.han.oose.jaimy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.security.auth.login.LoginException;

import static org.junit.Assert.assertEquals;

public class LoginServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private LoginService sut;

    @Before
    public void setUp() throws Exception {
        sut = new LoginService();
    }

    @Test
    public void testSuccessfullyLogin() throws LoginException {
        Account account = new Account("Jaimy", "wachtwoord");
        UserToken login = sut.login(account);

        assertEquals("Jaimy Goertz", login.getUser());
        assertEquals("1234-1234-1234", login.getToken());
    }

    @Test
    public void testFailedLogin() throws LoginException {
        thrown.expect(LoginException.class);
        thrown.expectMessage("Login failed");

        Account account = new Account("Jaimy", "wrongPassword");
        sut.login(account);
    }
}