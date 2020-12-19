package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class HomePage extends BasePage {
    public static final String MAIN_FEED_LOCATOR_IOS = "Instagram main feed";
    public static final String MAIN_FEED_LOCATOR_ANDROID = "Scroll to top";
    public MobileElement mainFeed;

    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Override
    public HomePage isPageOpened() {
        switch (platform) {
            case ("iOS"):
                mainFeed = driver.findElementByAccessibilityId(MAIN_FEED_LOCATOR_IOS);
                break;
            case ("Android"):
                mainFeed = driver.findElementByAccessibilityId(MAIN_FEED_LOCATOR_ANDROID);
                break;
        }
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(21))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(StaleElementReferenceException.class)
                .until(driver -> mainFeed.isDisplayed());
        return this;
    }
}
