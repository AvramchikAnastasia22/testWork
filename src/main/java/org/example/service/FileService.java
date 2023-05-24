package org.example.service;



import org.example.constant.Constant;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class FileService {
    public Map<Integer, String> addValuesFromFile(String path) throws IOException {
        String temp;
        String Category[];
        Map<Integer, String> recipient = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            while ((temp = bufferedReader.readLine()) != null) {
                if (!temp.equals(Constant.EMPTY_STRING)) {
                    Category = temp.split(Constant.PLACEHOLDER);
                    recipient.put(Integer.valueOf(Category[0]), Category[1]);
                }
            }
        } catch (IOException e) {
            throw e;
        }
        return recipient;
    }
}
