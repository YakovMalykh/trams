package metro.trams.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchRouteException extends NoSuchElementException {
    /**
     * Constructs a {@code NoSuchElementException}, saving a reference
     * to the error message string {@code s} for later retrieval by the
     * {@code getMessage} method.
     *
     * @param s the detail message.
     */
    public NoSuchRouteException(String s) {
        super(s);
    }
}
