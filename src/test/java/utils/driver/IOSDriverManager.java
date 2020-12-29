package utils.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import lombok.extern.log4j.Log4j2;
import utils.PropertyManager;

import java.util.concurrent.TimeUnit;

@Log4j2
public class IOSDriverManager extends DriverManager {

    @Override
    public AppiumDriver<MobileElement> createDriver(AppiumDriverLocalService service, String udid, String deviceName) {
        cap.setCapability("bundleId", PropertyManager.getInstance().get("ios.application.bundle.id"));
        cap.setCapability("automationName", "XCUITest");
        cap.setCapability("autoAcceptAlerts", true);

        driver = new IOSDriver<>(service, cap);
        int implicitlyWaitTimer = 10;
        log.debug(String.format("Set implicit wait for %s seconds", implicitlyWaitTimer));
        driver.manage().timeouts().implicitlyWait(implicitlyWaitTimer, TimeUnit.SECONDS);
        return driver;
    }
}
