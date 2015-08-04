package pl.lukaz.sptw.rest.model.user;

/**
 * @author lzenczuk 04/08/2015
 */
public class LoginResult {
    private final boolean success;
    private final String errorMessage;

    private LoginResult(boolean success, String errorMessage) {
        this.errorMessage = errorMessage;
        this.success = success;
    }

    public static LoginResult successfulLogin(){
        return new LoginResult(true, null);
    }

    public static LoginResult incorrectLogin(String msg){
        return new LoginResult(false, msg);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }
}
