package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

abstract public class BasePage {
    public static final String SEARCH_PAGE_LOCATOR_IOS = "explore-tab";
    public static final String SEARCH_PAGE_LOCATOR_ANDROID = "Search and Explore";
    public MobileElement searchPageIcon;

    String platform;

    AppiumDriver<MobileElement> driver;

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        platform = (String) driver.getCapabilities().getCapability("platformName");
    }

    abstract public BasePage isPageOpened();

    public SearchPage openSearchPage() {
        switch (platform) {
            case ("iOS"):
                searchPageIcon = driver.findElementByAccessibilityId(SEARCH_PAGE_LOCATOR_IOS);
                break;
            case ("Android"):
                searchPageIcon = driver.findElementByAccessibilityId(SEARCH_PAGE_LOCATOR_ANDROID);
                break;
        }
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(21))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(StaleElementReferenceException.class)
                .until(driver -> searchPageIcon.isDisplayed());
        searchPageIcon.click();
        return new SearchPage(driver);
    }
}
