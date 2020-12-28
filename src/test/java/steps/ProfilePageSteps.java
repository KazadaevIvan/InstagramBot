package steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.PostsPage;
import pages.ProfileFollowsPage;
import pages.ProfilePage;

import java.util.List;

@Log4j2
public class ProfilePageSteps {
    private ProfilePage profilePage;
    private PostsPage postsPage;
    private ProfileFollowsPage profileFollowsPage;

    public ProfilePageSteps(AppiumDriver<MobileElement> driver) {
        profilePage = new ProfilePage(driver);
        postsPage = new PostsPage(driver);
        profileFollowsPage = new ProfileFollowsPage(driver);
    }

    @Step("Open first photo and set Like")
    public ProfilePageSteps attemptToSetLikeToTheFirstPhoto() {
        log.info("Open first photo and set Like");
        profilePage
                .isPageOpened();
        if (profilePage.isNotPrivate()) {
            if (profilePage.hasPhotos()) {
                profilePage
                        .openFirstPhoto();
                postsPage
                        .isPageOpened()
                        .clickLikeButton()
                        .clickBackButton();
            }
        }
        return this;
    }

    @Step("Follow profile")
    public ProfilePageSteps followProfile() {
        log.info("Follow profile");
        profilePage
                .isPageOpened()
                .clickFollowButton();
        return this;
    }

    @Step("Get list of '{numberOfFollowers}' accounts which follow this profile")
    public List<String> getFollowers(int numberOfFollowers) {
        log.info(String.format("Get list of '%s' accounts which follow this profile", numberOfFollowers));
        profilePage
                .isPageOpened()
                .openFollowersList();
        return profileFollowsPage
                .isPageOpened()
                .getListOfProfilesToFollow(numberOfFollowers);
    }
}
