package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;

public class HomePage extends BasePage {

    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Verify Home page is opened")
    @Override
    public HomePage isPageOpened() {
        waitForElementToAppear(locationStrategy.getElement("mainFeed"));
        return this;
    }
}
