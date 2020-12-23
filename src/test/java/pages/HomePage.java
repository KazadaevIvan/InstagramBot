package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.io.IOException;

public class HomePage extends BasePage {

    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Override
    public HomePage isPageOpened() throws IOException {
        waitForElementToAppear(locationStrategy.getElement("mainFeed"));
        return this;
    }
}
