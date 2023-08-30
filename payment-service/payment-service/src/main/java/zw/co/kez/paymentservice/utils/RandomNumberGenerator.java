package zw.co.kez.paymentservice.utils;

import java.util.UUID;

public class RandomNumberGenerator {
    private RandomNumberGenerator(){}
    static String paymentReference = UUID.randomUUID().toString();
    public static String generateCode(){
        return "KL-"+paymentReference;
    }
}
