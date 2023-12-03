package com.example.andoridpki;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Common {
    public static void writeIntoFile(String fileName, Object obj){

        Gson gson = new Gson();
        String jsonString = gson.toJson(obj);

        try (FileWriter fileWriter = new FileWriter(fileName,false)) {
            fileWriter.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFromFile(String filePath){
        StringBuilder jsonStringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonStringBuilder.toString();
    }

}
