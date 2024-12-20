package org.BM2306913;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONHandler {
    static String studentDataFilepath = "studentDataJSON.json";
    static String moduleCodeFilepath = "moduleCodeJSON.json";

    public static StudentData[] loadAllStudentData(){
        // Loads and returns all StudentData[] objects from studentDataFilePath
        StudentData[] studentData = readFromJSON(studentDataFilepath, StudentData[].class);
        return studentData;
    }

    public static String[][] loadAllModuleCodes(){
        // Loads and returns all module codes as a String[][] array
        String[][] moduleCodes = readFromJSON(moduleCodeFilepath, String[][].class);
        return moduleCodes;
    }

    private static <T> T readFromJSON(String filePath, Class<T> classOfT){
        // Static method to read a specific object type from a JSON file
        Gson gson = new Gson();
        try(FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, classOfT);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void writeStudentDataToJSON(Object studentData){
        // Currently unused. Future expansion includes the ability to save student and module data to file
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(studentDataFilepath)){
            gson.toJson(studentData, writer);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
