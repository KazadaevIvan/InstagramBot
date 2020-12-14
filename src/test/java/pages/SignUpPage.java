package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SignUpPage extends BasePage {
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Sign In']")
    @AndroidFindBy(id = "com.instagram.android:id/log_in_button")
    public MobileElement loginButton;

    public SignUpPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        initElements(this);
    }

    @Override
    public SignUpPage isPageOpened() {
        loginButton.isDisplayed();
        return this;
    }

    public SignInPage clickSignIn() {
        loginButton.click();
        return new SignInPage(driver);
    }
}
