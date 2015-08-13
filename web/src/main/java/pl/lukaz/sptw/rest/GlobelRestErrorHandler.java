package pl.lukaz.sptw.rest;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.lukaz.sptw.rest.model.RestResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lzenczuk 10/08/2015
 */

@ControllerAdvice("pl.lukaz.sptw.rest")
public class GlobelRestErrorHandler {

    // 403 - no access rights
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public RestResult handleAccessException(HttpServletRequest request, Exception e){
        System.out.println("------------------> Access error: " + e.toString());
        return RestResult.fail(e.getMessage());
    }

    // 500 - unknown internal error
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RestResult handleException(HttpServletRequest request, Exception e){
        System.out.println("------------------> Error: " + e.toString());
        return RestResult.fail(e.getMessage());
    }

}
