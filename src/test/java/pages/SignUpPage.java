package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SignUpPage extends BasePage {

    public SignUpPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Override
    public SignUpPage isPageOpened() {
        waitForElementToAppear(locationStrategy.getElement("signUpButton"));
        return this;
    }

    public SignInPage clickSignIn() {
        locationStrategy.getElement("signUpButton").click();
        return new SignInPage(driver);
    }

    public Boolean isSignUpPage() {
        return locationStrategy.getElementsList("signUpWithEmailButtonsList").size() > 0;
    }
}
