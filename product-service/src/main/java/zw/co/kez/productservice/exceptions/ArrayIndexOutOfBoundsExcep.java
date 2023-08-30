package zw.co.kez.productservice.exceptions;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class ArrayIndexOutOfBoundsExcep extends RuntimeException{
    public ArrayIndexOutOfBoundsExcep(String message){
        super(message);
    }
}
