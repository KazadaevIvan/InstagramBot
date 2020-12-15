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
                .typeEmail("")
                .typePassword("")
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
                .typeSearchInfo("")
                .openSearchResult();
        profilePage
                .isPageOpened()
                .openFollowersList();
        profileFollowsPage
                .isPageOpened();
        for (int i = 0; i < 2; i++) {
            profileFollowsPage
                    .openProfile();
            profilePage
                    .isPageOpened()
                    .openFirstPhoto();
            postsPage
                    .isPageOpened()
                    .clickLikeButton()
                    .clickBackButton();
            profilePage
                    .isPageOpened()
                    .clickFollowButton()
                    .clickBackButton();
            profileFollowsPage
                    .isPageOpened();
        }
    }
}
