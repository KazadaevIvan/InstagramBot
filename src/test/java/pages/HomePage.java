package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class HomePage extends BasePage {

    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Override
    public HomePage isPageOpened() throws IOException, ParseException {
        locationStrategy.getElement("mainFeed").isDisplayed();
        return this;
    }
}
