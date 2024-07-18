package sangwon.solve_it.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sangwon.solve_it.exception.CustomException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


        @ExceptionHandler(CustomException.class)
        public ResponseEntity<String> handleCustomException(CustomException customException) {
            log.info("customException: " + customException.getMessage());
            return new ResponseEntity<String>(customException.getMessage(), customException.getHttpStatus());
        }

}
