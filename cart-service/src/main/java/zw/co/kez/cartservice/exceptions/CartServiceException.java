package zw.co.kez.cartservice.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CartServiceException extends RuntimeException{

    private final String errorCode;
    private final int status;

    public CartServiceException(String message, String errorCode, int status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}
