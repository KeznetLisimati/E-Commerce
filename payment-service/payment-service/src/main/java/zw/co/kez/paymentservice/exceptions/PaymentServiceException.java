package zw.co.kez.paymentservice.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PaymentServiceException extends RuntimeException{

    private final String errorCode;
    private final int status;

    public PaymentServiceException(String message, String errorCode, int status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}
