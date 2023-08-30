package zw.co.kez.productservice.exceptions;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class ResourceNotFoundException extends RuntimeException {
   public ResourceNotFoundException(String message){
       super(message);
   }
}
