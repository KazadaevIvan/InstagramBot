package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

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
        }
        searchPageIcon.click();
        return new SearchPage(driver);
    }
}
