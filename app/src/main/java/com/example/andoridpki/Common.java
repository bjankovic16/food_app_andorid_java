package com.example.andoridpki;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Common {
    public static String makeJsonFromObject(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static User makeUserFromJson(String json) {
        java.lang.reflect.Type listType = new TypeToken<User>() {}.getType();
        return new Gson().fromJson(json,listType);
    }

    public static void writeIntoFile(Context context, String fileName, Object obj) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(obj);
        try {
            File path = context.getFilesDir();
            File file = new File(path, fileName);
            FileOutputStream fileOutputStream = context.openFileOutput(file.getName(), Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(jsonString);
            outputStreamWriter.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean doesFileExist(Context context, String fileName){
        File path = context.getFilesDir();
        File file = new File(path, fileName);
        return file.exists();
    }

    public static ArrayList<User> getAllUsers(Context context, String fileName) {
        String readData = readFromFile(context, fileName);
        java.lang.reflect.Type listType = new TypeToken<List<User>>() {}.getType();
        ArrayList<User> personList = new Gson().fromJson(readData, listType);
        return personList==null ? new ArrayList<>() : personList;
    }

    public static ArrayList<Product> getAllProducts(Context context, String fileName) {
        String readData = readFromFile(context, fileName);
        java.lang.reflect.Type listType = new TypeToken<List<Product>>() {}.getType();
        ArrayList<Product> products = new Gson().fromJson(readData, listType);
        return products;
    }

    public static String readFromFile(Context context, String fileName) {
        File path = context.getFilesDir();
        File file = new File(path, fileName);
        StringBuilder jsonStringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
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
