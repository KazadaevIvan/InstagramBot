package utils;

import com.jayway.jsonpath.JsonPath;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LocationStrategy {
    AppiumDriver<MobileElement> driver;
    String platform;

    public LocationStrategy(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        platform = (String) driver.getCapabilities().getCapability("platformName");
    }

    public static String fileReader(String filePath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }

    public MobileElement getElement(String name) throws IOException {
        switch (getElementTypeFromFile(getFileContent(), name).get(0)) {
            case ("xpath"):
                return driver.findElementByXPath(getElementValueFromFile(getFileContent(), name).get(0));
            case ("id"):
                return driver.findElementById(getElementValueFromFile(getFileContent(), name).get(0));
            case ("accessibilityId"):
                return driver.findElementByAccessibilityId(getElementValueFromFile(getFileContent(), name).get(0));
            default:
                return null;
        }
    }

    public List<MobileElement> getElementsList(String name) throws IOException {
        switch (getElementTypeFromFile(getFileContent(), name).get(0)) {
            case ("xpathList"):
                return driver.findElementsByXPath(getElementValueFromFile(getFileContent(), name).get(0));
            case ("accessibilityIdList"):
                return driver.findElementsByAccessibilityId(getElementValueFromFile(getFileContent(), name).get(0));
            case ("idList"):
                return driver.findElementsById(getElementValueFromFile(getFileContent(), name).get(0));
            default:
                return null;
        }
    }

    public MobileElement getElement(String name, String parameter) throws IOException {
        return driver.findElementByXPath(String.format(getElementValueFromFile(getFileContent(), name).get(0), parameter));
    }

    public List<MobileElement> getElementsList(String name, String parameter) throws IOException {
        return driver.findElementsByXPath(String.format(getElementValueFromFile(getFileContent(), name).get(0), parameter));
    }

    public String getFileContent() throws IOException {
        switch (platform) {
            case ("iOS"):
                return fileReader("src/test/resources/iOSLocators.json");
            case ("Android"):
                return fileReader("src/test/resources/androidLocators.json");
            default:
                return null;
        }
    }

    public List<String> getElementValueFromFile(String fileContent, String elementName) {
        return JsonPath.read(fileContent, String.format("$.[?(@.name=='%s')].value", elementName));
    }

    public List<String> getElementTypeFromFile(String fileContent, String elementName) {
        return JsonPath.read(fileContent, String.format("$.[?(@.name=='%s')].type", elementName));
    }
}
