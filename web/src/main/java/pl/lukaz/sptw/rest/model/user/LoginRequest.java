package pl.lukaz.sptw.rest.model.user;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @author lzenczuk 04/08/2015
 */
public class LoginRequest {
    private String userName;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
