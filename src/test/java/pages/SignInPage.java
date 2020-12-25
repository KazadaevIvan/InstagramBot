package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;

public class SignInPage extends BasePage {

    public SignInPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Verify Sign In page is opened")
    @Override
    public SignInPage isPageOpened() {
        waitForElementToAppear(locationStrategy.getElement("loginButton"));
        return this;
    }

    @Step("Input email '{email}'")
    public SignInPage typeEmail(String email) {
        locationStrategy.getElement("userNameInput").clear();
        locationStrategy.getElement("userNameInput").sendKeys(email);
        return this;
    }

    @Step("Input password '{password}'")
    public SignInPage typePassword(String password) {
        locationStrategy.getElement("passwordInput").clear();
        locationStrategy.getElement("passwordInput").sendKeys(password);
        return this;
    }

    @Step("Tap LOG IN button")
    public HomePage clickLogInButton() {
        locationStrategy.getElement("loginButton").click();
        return new HomePage(driver);
    }

    public Boolean isSignInPage() {
        return locationStrategy.getElementsList("loginButtonsList").size() > 0;
    }
}
