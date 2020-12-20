package tests;

import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void userShouldBeLoggedInWithEmail() throws IOException {
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
}
