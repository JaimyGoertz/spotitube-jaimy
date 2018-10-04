package nl.han.oose.jaimy;

public class UserToken {
    private String user;
    private String token;

    public UserToken() {

    }

    public UserToken(String user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
