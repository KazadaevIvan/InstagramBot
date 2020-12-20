package tests;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void userShouldBeLoggedInWithEmail() throws IOException, ParseException {
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
