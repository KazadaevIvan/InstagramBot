package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    private static final Object lock = new Object();
    private static PropertyManager instance;
    private static String propertyFilePath;
    private static Properties prop;

    public static PropertyManager getInstance() {
        propertyFilePath = System.getProperty("user.dir") + "/src/test/resources/application.properties";
        if (instance == null) {
            synchronized (lock) {
                instance = new PropertyManager();
                instance.loadData();
            }
        }
        return instance;
    }


    private void loadData() {
        prop = new Properties();
        try {
            prop.load(new FileInputStream(propertyFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String propertyName) {
        return prop.getProperty(propertyName);
    }
}

