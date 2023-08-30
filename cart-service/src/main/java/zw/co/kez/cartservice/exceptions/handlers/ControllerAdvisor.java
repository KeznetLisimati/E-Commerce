package zw.co.kez.cartservice.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import zw.co.kez.cartservice.dtos.ErrorResponse;
import zw.co.kez.cartservice.exceptions.CartServiceException;

public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CartServiceException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CartServiceException exception) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build(),
                HttpStatus.valueOf(exception.getStatus()));
    }
}
