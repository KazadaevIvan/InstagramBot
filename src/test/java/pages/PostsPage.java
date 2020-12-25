package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PostsPage extends BasePage {

    public PostsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Override
    public PostsPage isPageOpened() {
        waitForElementToAppear(locationStrategy.getElement("likeButton"));
        return this;
    }

    public PostsPage clickLikeButton() {
        locationStrategy.getElement("likeButton").click();
        return this;
    }

    public ProfilePage clickBackButton() {
        locationStrategy.getElement("backButton").click();
        return new ProfilePage(driver);
    }
}
