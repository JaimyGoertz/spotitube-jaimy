package nl.han.oose.jaimy.services.login;

import nl.han.oose.jaimy.persistence.account.AccountDAO;
import nl.han.oose.jaimy.persistence.token.TokenDAO;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class LoginServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private AccountDAO accountDAOMock;

    @Mock
    private TokenDAO tokenDAOMock;

    @InjectMocks
    private LoginServiceImpl sut;

//    @Test
//    public void testTokenIsCreatedWhenLoginIsCorrect() throws LoginException {
//        Account account = new Account("Jaimy", "pass");
//        UserToken userToken = new UserToken("Jaimy","1234-1234-1234");
//        Mockito.when(accountDAOMock.getAccount(Mockito.any())).thenReturn(account);
//        Mockito.when(tokenDAOMock.createUserToken(Mockito.any(), Mockito.any())).thenReturn(userToken);
//        sut.login(account);
//
//        assertEquals("1234-1234-1234", userToken.getToken());
//        assertEquals("Jaimy", userToken.getUser());
//    }
//
//    @Test
//    public void testExceptionIsThrownWhenLoginIsIncorrect() throws LoginException {
//        thrown.expect(LoginException.class);
//        thrown.expectMessage("Login failed");
//
//        Account badAccount = new Account("Jaimy", "wrongPass");
//        Account goodAccount = new Account("Jaimy", "pass");
//        Mockito.when(accountDAOMock.getAccount(Mockito.any())).thenReturn(goodAccount);
//        sut.login(badAccount);
//    }
}