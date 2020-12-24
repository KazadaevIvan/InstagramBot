package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.io.IOException;

public class SignInPage extends BasePage {

    public SignInPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Override
    public SignInPage isPageOpened() throws IOException {
        waitForElementToAppear(locationStrategy.getElement("loginButton"));
        return this;
    }

    public SignInPage typeEmail(String email) {
        locationStrategy.getElement("userNameInput").clear();
        locationStrategy.getElement("userNameInput").sendKeys(email);
        return this;
    }

    public SignInPage typePassword(String password) {
        locationStrategy.getElement("passwordInput").clear();
        locationStrategy.getElement("passwordInput").sendKeys(password);
        return this;
    }

    public HomePage clickLogInButton() {
        locationStrategy.getElement("loginButton").click();
        return new HomePage(driver);
    }

    public Boolean isSignInPage() {
        return locationStrategy.getElementsList("loginButtonsList").size() > 0;
    }
}
