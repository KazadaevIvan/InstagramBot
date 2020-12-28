package steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.HomePage;
import pages.SignInPage;
import pages.SignUpPage;

@Log4j2
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
    public LoginSteps login(String email, String password) {
        log.info(String.format("Login with email '%s' and password '%s'", email, password));
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
