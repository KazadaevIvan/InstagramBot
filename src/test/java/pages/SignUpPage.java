package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.io.IOException;

public class SignUpPage extends BasePage {

    public SignUpPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Override
    public SignUpPage isPageOpened() throws IOException{
        locationStrategy.getElement("signUpButton").isDisplayed();
        return this;
    }

    public SignInPage clickSignIn() throws IOException {
        locationStrategy.getElement("signUpButton").click();
        return new SignInPage(driver);
    }
}
