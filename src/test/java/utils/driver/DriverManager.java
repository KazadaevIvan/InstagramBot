package utils.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;


public abstract class DriverManager {

    AppiumDriver<MobileElement> driver;
    DesiredCapabilities cap = new DesiredCapabilities();

    protected abstract AppiumDriver<MobileElement> createDriver(AppiumDriverLocalService service, String udid, String deviceName);

    public AppiumDriver<MobileElement> getDriver(AppiumDriverLocalService service, String deviceName, String platform, String udid) {
        if (null == driver) {
            cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            cap.setCapability("udid", udid);
            driver = createDriver(service, udid, deviceName);
        }
        return driver;
    }
}

    
   
