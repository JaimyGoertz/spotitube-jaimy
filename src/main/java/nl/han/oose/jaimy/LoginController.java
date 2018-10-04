package nl.han.oose.jaimy;


import javax.security.auth.login.LoginException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    private LoginService loginService = new LoginService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Account user) {
        try {
            return Response.ok().entity(loginService.login(user)).build();
        } catch (LoginException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
