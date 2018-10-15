package nl.han.oose.jaimy.entity.account;

public class Account {
    private String user;
    private String password;


    public Account() {

    }

    public Account(String user, String password) {
        this.password = password;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
