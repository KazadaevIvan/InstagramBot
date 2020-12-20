package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class PostsPage extends BasePage {

    public PostsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Override
    public PostsPage isPageOpened() throws IOException, ParseException {
        locationStrategy.getElement("likeButton").isDisplayed();
        return this;
    }

    public PostsPage clickLikeButton() throws IOException {
        locationStrategy.getElement("likeButton").click();
        return this;
    }

    public ProfilePage clickBackButton() throws IOException {
        locationStrategy.getElement("backButton").click();
        return new ProfilePage(driver);
    }
}
