package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import utils.LocationStrategy;

import java.io.IOException;
import java.time.Duration;

abstract public class BasePage {
    private static final int TIMEOUT = 33;
    private static final int POLLING = 3;
    private final FluentWait<AppiumDriver<MobileElement>> wait;
    String platform;
    AppiumDriver<MobileElement> driver;
    LocationStrategy locationStrategy;

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofSeconds(POLLING))
                .ignoring(StaleElementReferenceException.class);
        platform = (String) driver.getCapabilities().getCapability("platformName");
        locationStrategy = new LocationStrategy(driver);
    }

    boolean waitForElementToAppear(MobileElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    abstract public BasePage isPageOpened() throws IOException;

    public SearchPage openSearchPage() {
        waitForElementToAppear(locationStrategy.getElement("searchPageIcon"));
        locationStrategy.getElement("searchPageIcon").click();
        return new SearchPage(driver);
    }
}
