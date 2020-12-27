package utils.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import utils.PropertyManager;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class AndroidDriverManager extends DriverManager {

    @Override
    public AppiumDriver<MobileElement> createDriver(AppiumDriverLocalService service, String udid, String deviceName) {
        cap.setCapability("appActivity", PropertyManager.getInstance().get("application.activity.name"));
        cap.setCapability("appPackage", PropertyManager.getInstance().get("application.package.name"));
        cap.setCapability("app", new File(PropertyManager.getInstance().get("application.path")).getAbsolutePath());
        cap.setCapability("udid", udid);
        cap.setCapability("autoGrantPermissions", true);

        if (udid.contains("emulator")) {
            cap.setCapability("avd", deviceName);
            cap.setCapability("avdLaunchTimeout", 300000);
            cap.setCapability("avdReadyTimeout", 300000);
        }
        driver = new AndroidDriver<>(service, cap);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
    }
}
