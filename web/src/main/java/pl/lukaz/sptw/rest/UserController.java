package pl.lukaz.sptw.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lukaz.sptw.rest.model.user.LoginResult;
import pl.lukaz.sptw.rest.model.user.LogoutResult;
import pl.lukaz.sptw.rest.model.user.LoginRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lzenczuk 31/07/2015
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResult login(LoginRequest loginRequest) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUserName(),
                            loginRequest.getPassword()));

            if (authenticate != null && authenticate.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticate);
                return LoginResult.successfulLogin();
            } else {
                return LoginResult.incorrectLogin("Error");
            }

        } catch (DisabledException ex) {
            return LoginResult.incorrectLogin("Account disabled");
        } catch (LockedException ex) {
            return LoginResult.incorrectLogin("Account locked");
        } catch (BadCredentialsException ex) {
            return LoginResult.incorrectLogin("Incorrect user name or password");
        } catch (AuthenticationException ex) {
            return LoginResult.incorrectLogin("Error");
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public LogoutResult logout(HttpServletRequest request) {
        // this regenerate csrf
        /*try {
            request.logout();
            return LogoutResult.successfulLogout();
        } catch (ServletException e) {
            return LogoutResult.incorrectLogout("Error during logout");
        }*/

        SecurityContextHolder.clearContext();
        return LogoutResult.successfulLogout();
    }
}
