package steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import pages.HomePage;
import pages.SignInPage;
import pages.SignUpPage;

import java.io.IOException;

public class LoginSteps {
    private SignUpPage signUpPage;
    private SignInPage signInPage;
    private HomePage homePage;

    public LoginSteps(AppiumDriver<MobileElement> driver) {
        signUpPage = new SignUpPage(driver);
        signInPage = new SignInPage(driver);
        homePage = new HomePage(driver);
    }

    @Step("Login with email '{email}' and password '{password}'")
    public LoginSteps login(String email, String password) throws IOException {
        if (signUpPage.isSignUpPage()) {
            signUpPage
                    .isPageOpened()
                    .clickSignIn();
            signInPage
                    .isPageOpened()
                    .typeEmail(email)
                    .typePassword(password)
                    .clickLogInButton();
        } else if (signInPage.isSignInPage()) {
            signInPage
                    .isPageOpened()
                    .typeEmail(email)
                    .typePassword(password)
                    .clickLogInButton();
        }
        homePage
                .isPageOpened();
        return this;
    }
}
