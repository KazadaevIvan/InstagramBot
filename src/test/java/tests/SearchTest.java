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
                .isPageOpened()
                .openProfile(1);
        profilePage
                .isPageOpened()
                .clickFollowButton()
                .openFirstPhoto();
        postsPage
                .isPageOpened()
                .clickLikeButton()
                .clickBackButton();
        profilePage
                .clickBackButton();
        profileFollowsPage
                .isPageOpened();
    }
}

