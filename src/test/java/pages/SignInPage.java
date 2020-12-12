package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SignInPage extends BasePage {

    public SignInPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        initElements(this);
    }

    @Override
    public SignInPage isPageOpened() {
        return this;
    }

    public SignInPage typeEmail(String email) {
        return this;
    }

    public SignInPage typePassword(String password) {
        return this;
    }

    public HomePage clickNextButton() {
        return new HomePage(driver);
    }
}
