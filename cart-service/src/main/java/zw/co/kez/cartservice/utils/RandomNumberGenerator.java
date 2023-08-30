package zw.co.kez.cartservice.utils;

import java.util.UUID;

public class RandomNumberGenerator {
    private RandomNumberGenerator(){}
    static String referenceNumber = UUID.randomUUID().toString();
    public static String generateCode(){
        return "OS-"+referenceNumber;
    }
}
