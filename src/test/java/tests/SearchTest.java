package tests;

import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class SearchTest extends BaseTest {
    @BeforeMethod
    public void login() throws IOException, ParseException {
        if (locationStrategy.getElementsList("signUpWithEmailButtonsList").size() > 0) {
            signUpPage
                    .isPageOpened()
                    .clickSignIn();
            signInPage
                    .isPageOpened()
                    .typeEmail(email)
                    .typePassword(password)
                    .clickLogInButton();
        } else if (locationStrategy.getElementsList("loginButtonsList").size() > 0) {
            signInPage
                    .isPageOpened()
                    .typeEmail(email)
                    .typePassword(password)
                    .clickLogInButton();
        }
        homePage
                .isPageOpened();
    }

    @Test
    public void searchResultsShouldBeShown() throws IOException, ParseException {
        homePage
                .openSearchPage();
        searchPage
                .isPageOpened()
                .typeSearchInfo(profile)
                .openSearchResult();
        profilePage
                .isPageOpened()
                .openFollowersList();
        List<String> listOfProfileToFollow = profileFollowsPage
                .isPageOpened()
                .getListOfProfilesToFollow(numberOfProfilesToFollow);

        for (String profile : listOfProfileToFollow) {
            profileFollowsPage
                    .openSearchPage();
            searchPage
                    .isPageOpened()
                    .typeSearchInfo(profile)
                    .openSearchResult();
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
                    .clickFollowButton();
        }
    }
}
