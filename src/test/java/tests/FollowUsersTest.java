package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class FollowUsersTest extends BaseTest {
    @BeforeMethod(description = "Login")
    public void login() {
        loginSteps
                .login(email, password);
    }

    @Test(description = "Get some followers of profile and follow them")
    public void usersShouldBeFollowed() {
        homePage
                .openSearchPage();
        searchPageSteps
                .findProfile(profile);
        for (String profile : profilePageSteps
                .getFollowers(numberOfProfilesToFollow)) {
            profileFollowsPage
                    .openSearchPage();
            searchPageSteps
                    .findProfile(profile);
            profilePageSteps
                    .attemptToSetLikeToTheFirstPhoto()
                    .followProfile();
        }
    }
}
