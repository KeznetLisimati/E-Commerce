package zw.co.kez.productservice.exceptions.handlers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import zw.co.kez.productservice.dtos.ErrorMessage;
import zw.co.kez.productservice.exceptions.ArrayIndexOutOfBoundsExcep;
import zw.co.kez.productservice.exceptions.ResourceNotFoundException;

@ControllerAdvice
class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity<>(ErrorMessage.builder()
                .description("Resource not found exception!")
                .message(exception.getMessage())
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsExcep.class)
    public ResponseEntity<ErrorMessage> arrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsExcep exception) {
        return new ResponseEntity<>(ErrorMessage.builder()
                .description("Array index out of bounce exception!")
                .message(exception.getMessage())
                .build(),
                HttpStatus.BAD_REQUEST);
    }

}
