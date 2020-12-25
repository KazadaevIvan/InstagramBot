package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.io.IOException;

public class FollowUsersTest extends BaseTest {
    @BeforeMethod(description = "Login")
    public void login() throws IOException {
        loginSteps
                .login(email, password);
    }

    @Test(description = "Get some followers of profile and follow them")
    public void usersShouldBeFollowed() throws IOException {
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
