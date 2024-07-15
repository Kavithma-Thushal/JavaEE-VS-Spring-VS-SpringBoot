package lk.ijse.gdse66.spring.advisor;

import lk.ijse.gdse66.spring.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : Kavithma Thushal
 * @project : Spring-POS
 **/
@RestControllerAdvice
@CrossOrigin
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({RuntimeException.class})
    public ResponseUtil handleMyExceptions(RuntimeException e) {
        return new ResponseUtil("Error", e.getMessage(), null);
    }
}
