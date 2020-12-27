package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;

public class PostsPage extends BasePage {

    public PostsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Verify Posts page is opened")
    @Override
    public PostsPage isPageOpened() {
        waitForElementToAppear(locationStrategy.getElement("likeButton"));
        return this;
    }

    @Step("Tap LIKE button")
    public PostsPage clickLikeButton() {
        locationStrategy.getElement("likeButton").click();
        return this;
    }

    @Step("Tap BACK button")
    public ProfilePage clickBackButton() {
        locationStrategy.getElement("backButton").click();
        return new ProfilePage(driver);
    }
}
