package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import utils.LocationStrategy;

import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;

@Log4j2
abstract public class BasePage {
    private static final int TIMEOUT = 21;
    private static final int POLLING = 1;
    private final FluentWait<AppiumDriver<MobileElement>> wait;
    String platform;
    AppiumDriver<MobileElement> driver;
    LocationStrategy locationStrategy;

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofSeconds(POLLING))
                .ignoring(StaleElementReferenceException.class, NoSuchElementException.class);
        platform = (String) driver.getCapabilities().getCapability("platformName");
        locationStrategy = new LocationStrategy(driver);
    }

    boolean waitForElementToAppear(String elementName) {
        try {
            wait.until(ExpectedConditions.visibilityOf(locationStrategy.getElement(elementName)));
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            log.error(e.getLocalizedMessage());
            Assert.fail("Could not find element with locator " + locationStrategy.getElementValueFromFile(locationStrategy.getFileContent(), elementName));
            return false;
        }
    }

    boolean waitForElementToAppear(MobileElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            log.error(e.getLocalizedMessage());
            return false;
        }
    }

    abstract public BasePage isPageOpened() throws IOException;

    @Step("Open Search page")
    public SearchPage openSearchPage() {
        waitForElementToAppear("searchPageIcon");
        locationStrategy.getElement("searchPageIcon").click();
        return new SearchPage(driver);
    }
}
