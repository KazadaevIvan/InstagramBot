package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;

public class SignUpPage extends BasePage {

    public SignUpPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Verify Sign Up page is opened")
    @Override
    public SignUpPage isPageOpened() {
        waitForElementToAppear(locationStrategy.getElement("signUpButton"));
        return this;
    }

    @Step("Tap Already Have An Account")
    public SignInPage clickSignIn() {
        locationStrategy.getElement("signUpButton").click();
        return new SignInPage(driver);
    }

    public Boolean isSignUpPage() {
        return locationStrategy.getElementsList("signUpWithEmailButtonsList").size() > 0;
    }
}
