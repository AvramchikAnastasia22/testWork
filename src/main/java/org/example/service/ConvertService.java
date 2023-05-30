package org.example.service;



import org.example.constant.Constant;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;
import java.util.Objects;

public class ConvertService {
    FileService fileService = new FileService();
    NumberService numberService = new NumberService();



    private Map<Integer, String> digitsMap;
    private Map<Integer, String> categoryMap;



    public String convertToString(BigInteger number) throws IOException {
        String stringNumber = number.toString();
        String word = Constant.EMPTY_STRING;
        if(!Constant.FLAG){
        categoryMap = fileService.addValuesFromFile(Constant.PATH_CATEGORY);
        digitsMap = fileService.addValuesFromFile(Constant.PATH_DIGITS);
        Constant.FLAG=true;
        }
        if (stringNumber.equals(Constant.ZERO)) return digitsMap.get(0);

        stringNumber = numberService.addMultiplicateString(stringNumber);

        word = addNumToWord(stringNumber);

        return word.trim();
    }



    private String addNumToWord(String number) throws IOException{
        String word = "";
        try {


        for (int i = 0; i < number.length() / 3; i++) {
            Integer categoruNumber = number.length() / 3 - i -1;
            Integer beginIndex = i * 3;
            Integer endIndex = beginIndex + 3;
            if (categoruNumber == 1)
                word += convertDigitOfNumber(number.substring(beginIndex, endIndex), Constant.FEMALE) +
                        getType(categoruNumber, number.substring(beginIndex, endIndex));
            else
                word += convertDigitOfNumber(number.substring(beginIndex, endIndex), Constant.MALE) +
                        getType(categoruNumber, number.substring(beginIndex, endIndex));
        }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(e);
        }
        return word;
    }

    private String convertDigitOfNumber(String triad, int gender) {
        if (triad.equals(Constant.THREE_ZERO)) return Constant.EMPTY_STRING;
        int hundreds =Character.getNumericValue(triad.charAt(0));
        int dozens =Character.getNumericValue(triad.charAt(1));
        int units = Character.getNumericValue(triad.charAt(2));

        String words = Constant.EMPTY_STRING;

        if (hundreds != 0)
            words += digitsMap.get(hundreds * 100) + Constant.PLACEHOLDER;
        if (dozens == 1 && units <=9 && units >=0)
            words += digitsMap.get(dozens * 10 + units) + Constant.PLACEHOLDER;
        else {
            if (dozens != 0)
                words += digitsMap.get(dozens * 10) + Constant.PLACEHOLDER;
            if (units != 0 && units > 2)
                words += digitsMap.get(units) + Constant.PLACEHOLDER;

            else if (units != 0 && units <=2)
                words += digitsMap.get(units * gender) + Constant.PLACEHOLDER;
        }
        return words;
    }


    private String getType(int categoruNumber, String number) {
        if (Objects.equals(number,Constant.THREE_ZERO)) return Constant.EMPTY_STRING;
        if (categoruNumber == 1) return categoryMap.get(categoruNumber) +
                Constant.STRING_ENDING[0][numberService.selectTypeNumber(numberService.checkNumberModule(number))] + Constant.PLACEHOLDER;
        if (categoruNumber > 1) return categoryMap.get(categoruNumber) +
                Constant.STRING_ENDING[1][numberService.selectTypeNumber(numberService.checkNumberModule(number))] + Constant.PLACEHOLDER;
        if (digitsMap.get(categoruNumber) == null && categoruNumber != 0)
            throw new NullPointerException("Не существует такого разряда!");
        else
            return Constant.EMPTY_STRING;

    }



}
