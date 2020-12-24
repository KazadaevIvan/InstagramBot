package steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import pages.PostsPage;
import pages.ProfileFollowsPage;
import pages.ProfilePage;

import java.io.IOException;
import java.util.List;

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
    public ProfilePageSteps attemptToSetLikeToTheFirstPhoto() throws IOException {
        profilePage
                .isPageOpened();
        if (!profilePage.isPrivate()) {
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
    public ProfilePageSteps followProfile() throws IOException {
        profilePage
                .isPageOpened()
                .clickFollowButton();
        return this;
    }

    @Step("Get list of '{numberOfFollowers}' accounts which follow this profile")
    public List<String> getFollowers(int numberOfFollowers) throws IOException {
        profilePage
                .isPageOpened()
                .openFollowersList();
        return profileFollowsPage
                .isPageOpened()
                .getListOfProfilesToFollow(numberOfFollowers);
    }
}
