package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

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
        mainFeed.isDisplayed();
        return this;
    }
}
