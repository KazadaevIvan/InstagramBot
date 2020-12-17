package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {
    @BeforeMethod
    public void login() {
        signUpPage
                .isPageOpened()
                .clickSignIn();
        signInPage
                .isPageOpened()
                .typeEmail(email)
                .typePassword(password)
                .clickLogInButton();
        homePage
                .isPageOpened();
    }

    @Test
    public void searchResultsShouldBeShown() {
        homePage
                .openSearchPage();
        searchPage
                .isPageOpened()
                .typeSearchInfo(profile)
                .openSearchResult();
        profilePage
                .isPageOpened()
                .openFollowersList();
        profileFollowsPage
                .isPageOpened();
        for (int i = 0; i < numberOfProfilesToFollow; i++) {
            profileFollowsPage
                    .openProfile();
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
            profilePage
                    .isPageOpened()
                    .clickFollowButton()
                    .clickBackButton();
            profileFollowsPage
                    .isPageOpened();
        }
    }
}
