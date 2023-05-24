package org.example;

import org.example.service.ConvertService;

import java.io.IOException;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        ConvertService service = new ConvertService();
        BigInteger digits[] = {new BigInteger("1000"),
                new BigInteger("255894"),
                new BigInteger(String.valueOf(Integer.MAX_VALUE))};

        for (BigInteger value : digits) {
            try {
                System.out.println(service.convertToString(value));
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }
}