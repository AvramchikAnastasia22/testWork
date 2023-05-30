package org.example.service;


import org.example.constant.Constant;

public class NumberService {

    public Integer checkNumberModule(String number) {
        return Math.abs(Integer.parseInt(number)) % 100;
    }

    public String addMultiplicateString(String digit) {
        for (int i = 0; i < digit.length() % 3; i++) {
            digit = Constant.ZERO + digit;
        }
        return digit;
    }
    public Integer selectTypeNumber(int digit) {

        if (digit > 10 && digit < 20) return 2;
        if ((digit % 10) > 1 && (digit % 10) < 5) return 1;
        if ((digit % 10) == 1) return 0;
        return 2;
    }
}
