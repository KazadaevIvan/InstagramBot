package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SignInPage extends BasePage {
    @iOSXCUITFindBy(accessibility = "username-field")
    @AndroidFindBy(id = "com.instagram.android:id/log_in_button")
    public MobileElement userNameInput;

    @iOSXCUITFindBy(accessibility = "password-field")
    @AndroidFindBy(id = "com.instagram.android:id/log_in_button")
    public MobileElement passwordInput;

    @iOSXCUITFindBy(accessibility = "login-button")
    @AndroidFindBy(id = "com.instagram.android:id/log_in_button")
    public MobileElement logInButton;

    public SignInPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        initElements(this);
    }

    @Override
    public SignInPage isPageOpened() {
        return this;
    }

    public SignInPage typeEmail(String email) {
        userNameInput.clear();
        userNameInput.sendKeys(email);
        return this;
    }

    public SignInPage typePassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public HomePage clickLogInButton() {
        logInButton.click();
        return new HomePage(driver);
    }
}
