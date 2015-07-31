package pl.lukaz.sptw.rest.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author lzenczuk 31/07/2015
 */
public class RestResult<T> {
    private final boolean succesfull;
    private final List<String> errors;
    private final T value;

    private RestResult(boolean succesfull, List<String> errors, T value) {
        this.succesfull = succesfull;
        this.errors = errors;
        this.value = value;
    }

    public static <T> RestResult<T> success(T value){
        return new RestResult<>(true, Collections.<String>emptyList(), value);
    }

    public static <T> RestResult<T> fail(){
        return new RestResult<>(false, Collections.<String>emptyList(), null);
    }

    public static <T> RestResult<T> fail(String... errors){
        return new RestResult<>(false, Arrays.asList(errors), null);
    }

    public boolean isSuccesfull() {
        return succesfull;
    }

    public List<String> getErrors() {
        return errors;
    }

    public T getValue() {
        return value;
    }
}
