package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import utils.appium.AppiumUtils;

import java.io.IOException;

public class ProfilePage extends BasePage {
    public ProfilePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Override
    public ProfilePage isPageOpened() throws IOException {
        waitForElementToAppear(locationStrategy.getElement("followers"));
        return this;
    }

    public SearchPage openFollowersList() throws IOException {
        locationStrategy.getElement("followers").click();
        return new SearchPage(driver);
    }

    public ProfilePage clickFollowButton() throws IOException {
        waitForElementToAppear(locationStrategy.getElement("followButton"));
        locationStrategy.getElement("followButton").click();
        return this;
    }

    public PostsPage openFirstPhoto() throws IOException {
        boolean isFoundElement = locationStrategy.getElementsList("photosList").size() > 0;
        while (!isFoundElement) {
            AppiumUtils.scrollDownByCoordinates(driver, locationStrategy.getElement("profilePageListView"), 0.9);
            isFoundElement = locationStrategy.getElementsList("photosList").size() > 0;
        }
        locationStrategy.getElementsList("photosList").get(0).click();
        return new PostsPage(driver);
    }

    public Boolean isPrivate() throws IOException {
        return locationStrategy.getElementsList("privateAccountTitleList").size() > 0;
    }

    public Boolean hasPhotos() throws IOException {
        switch (platform) {
            case ("iOS"):
                return Integer.parseInt(locationStrategy.getElement("postsNumber").getText().substring(0, locationStrategy.getElement("postsNumber").getText().indexOf(" "))) > 0;
            case ("Android"):
                return Integer.parseInt(locationStrategy.getElement("postsNumber").getText()) > 0;
            default:
                return null;
        }
    }
}
