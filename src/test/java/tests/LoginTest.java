package tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void userShouldBeLoggedInWithEmail() {
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
