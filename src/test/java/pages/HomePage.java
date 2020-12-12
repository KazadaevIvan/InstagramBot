package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomePage extends BasePage {
    @iOSXCUITFindBy(accessibility = "Instagram main feed")
    @AndroidFindBy(accessibility = "Scroll to top")
    public MobileElement mainFeed;

    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        initElements(this);
    }

    @Override
    public HomePage isPageOpened() {
        mainFeed.isDisplayed();
        return this;
    }
}
