package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import utils.appium.AppiumUtils;

public class ProfilePage extends BasePage {
    public ProfilePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Verify Profile page is opened")
    @Override
    public ProfilePage isPageOpened() {
        waitForElementToAppear(locationStrategy.getElement("followers"));
        return this;
    }

    @Step("Open FOLLOWERS list")
    public SearchPage openFollowersList() {
        locationStrategy.getElement("followers").click();
        return new SearchPage(driver);
    }

    @Step("Tap FOLLOW button")
    public ProfilePage clickFollowButton() {
        waitForElementToAppear(locationStrategy.getElement("followButton"));
        locationStrategy.getElement("followButton").click();
        return this;
    }

    @Step("Open first photo")
    public PostsPage openFirstPhoto() {
        boolean isFoundElement = !locationStrategy.getElementsList("photosList").isEmpty();
        while (!isFoundElement) {
            AppiumUtils.scrollDownByCoordinates(driver, locationStrategy.getElement("profilePageListView"), 0.9);
            isFoundElement = !locationStrategy.getElementsList("photosList").isEmpty();
        }
        waitForElementToAppear(locationStrategy.getElementsList("photosList").get(0));
        locationStrategy.getElementsList("photosList").get(0).click();
        return new PostsPage(driver);
    }

    @Step("Verify profile is not private")
    public Boolean isNotPrivate() {
        return locationStrategy.getElementsList("privateAccountTitleList").isEmpty();
    }

    @Step("Verify profile has photos")
    public Boolean hasPhotos() {
        waitForElementToAppear(locationStrategy.getElement("postsNumber"));
        return !locationStrategy.getElement("postsNumber").getText().equals("0");
    }
}
