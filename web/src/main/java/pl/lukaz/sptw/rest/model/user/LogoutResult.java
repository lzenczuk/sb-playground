package pl.lukaz.sptw.rest.model.user;

/**
 * @author lzenczuk 04/08/2015
 */
public class LogoutResult {
    private final boolean success;
    private final String errorMessage;

    private LogoutResult(boolean success, String errorMessage) {
        this.errorMessage = errorMessage;
        this.success = success;
    }

    public static LogoutResult successfulLogout(){
        return new LogoutResult(true, null);
    }

    public static LogoutResult incorrectLogout(String msg){
        return new LogoutResult(false, msg);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }
}
