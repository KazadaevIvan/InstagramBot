package utils.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import utils.PropertyManager;

import java.util.concurrent.TimeUnit;

public class IOSDriverManager extends DriverManager {

    @Override
    public AppiumDriver createDriver(AppiumDriverLocalService service, String udid, String deviceName) {
        cap.setCapability("bundleId", PropertyManager.getInstance().get("ios.application.bundle.id"));
        cap.setCapability("automationName", PropertyManager.getInstance().get("ios.automation.name"));

        driver = new IOSDriver(service, cap);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
    }
}
