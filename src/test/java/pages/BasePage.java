package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import utils.LocationStrategy;

import java.io.IOException;

abstract public class BasePage {
    public String platform;
    public AppiumDriver<MobileElement> driver;
    LocationStrategy locationStrategy;

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        platform = (String) driver.getCapabilities().getCapability("platformName");
        locationStrategy = new LocationStrategy(driver);
    }

    abstract public BasePage isPageOpened() throws IOException;

    public SearchPage openSearchPage() throws IOException {
        locationStrategy.getElement("searchPageIcon").click();
        return new SearchPage(driver);
    }
}
