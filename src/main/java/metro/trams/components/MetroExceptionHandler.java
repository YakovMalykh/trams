package metro.trams.components;

import lombok.extern.slf4j.Slf4j;
import metro.trams.exceptions.NoSuchRouteException;
import metro.trams.exceptions.RouteAlreadyExists;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class MetroExceptionHandler {

    @ExceptionHandler(NoSuchRouteException.class)
    public ResponseEntity<Object> notFoundRouteHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RouteAlreadyExists.class)
    public ResponseEntity<Object> alreadyExistsRouteHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
