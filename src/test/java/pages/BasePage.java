package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

abstract public class BasePage {
    public static final String SEARCH_PAGE_LOCATOR_IOS = "explore-tab";
    public static final String SEARCH_PAGE_LOCATOR_ANDROID = "Search and Explore";
    private static final int TIMEOUT = 33;
    private static final int POLLING = 3;
    public MobileElement searchPageIcon;
    String platform;
    AppiumDriver<MobileElement> driver;
    private FluentWait<AppiumDriver<MobileElement>> wait;

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofSeconds(POLLING))
                .ignoring(StaleElementReferenceException.class);
        platform = (String) driver.getCapabilities().getCapability("platformName");
    }

    abstract public BasePage isPageOpened();

    boolean waitForElementToAppear(MobileElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public SearchPage openSearchPage() {
        switch (platform) {
            case ("iOS"):
                searchPageIcon = driver.findElementByAccessibilityId(SEARCH_PAGE_LOCATOR_IOS);
                break;
            case ("Android"):
                searchPageIcon = driver.findElementByAccessibilityId(SEARCH_PAGE_LOCATOR_ANDROID);
                break;
        }
        waitForElementToAppear(searchPageIcon);
        searchPageIcon.click();
        return new SearchPage(driver);
    }
}
