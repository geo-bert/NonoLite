package com.nonolite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class SaveFileController {
    private String _directory = "out/";
    private final String _fileType = ".save";
    
    public SaveFileController() {
    }
    
    public SaveFileController(String directory) {
        _directory = directory;
    }
    
    public String loadState() {
        String filePath = _directory + "state" + _fileType;
        StringBuilder input = new StringBuilder();
        
        try (BufferedReader inputStream = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = inputStream.readLine()) != null) {
                input.append(line.concat("\r\n"));
            }
        }
        catch (IOException exception) {
            return "";
        }
        
        return input.toString();
    }
    
    public void saveState(String state) {
        String filePath = _directory + "state" + _fileType;
        
        try (BufferedWriter outputStream = new BufferedWriter(new FileWriter(filePath))) {
            outputStream.write(state);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    
    public String loadSetting(String key) {
        return loadSetting(key, "");
    }
    
    public String loadSetting(String key, String defaultValue) {
        String filePath = _directory + "settings" + _fileType;
        Properties properties = new Properties();
        
        try (InputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
        }
        catch (IOException exception) {
            exception.printStackTrace();
            return "";
        }
        
        return properties.getProperty(key, defaultValue);
    }
    
    public void saveSetting(String key, String value) {
        String filePath = _directory + "settings" + _fileType;
        Properties properties = new Properties();
        
        properties.setProperty(key, value);
        
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            properties.store(outputStream, "Setting properties");
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
