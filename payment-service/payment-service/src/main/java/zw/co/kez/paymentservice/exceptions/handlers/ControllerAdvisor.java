package zw.co.kez.paymentservice.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import zw.co.kez.paymentservice.dtos.ErrorResponse;
import zw.co.kez.paymentservice.exceptions.PaymentServiceException;

public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PaymentServiceException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(PaymentServiceException exception) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build(),
                HttpStatus.valueOf(exception.getStatus()));
    }
}
