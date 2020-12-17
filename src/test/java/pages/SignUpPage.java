package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.util.List;

public class SignUpPage extends BasePage {
    public static final String LOG_IN_BUTTON_LOCATOR_IOS = "//XCUIElementTypeStaticText[@name='Sign In']";
    public static final String LOG_IN_BUTTON_LOCATOR_ANDROID = "com.instagram.android:id/log_in_button";
    public MobileElement logInButton;


    public static final String SIGN_UP_WITH_EMAIL_BUTTON_LOCATOR_IOS = "//XCUIElementTypeStaticText[@name='Sign up with Phone or Email']";
    public static final String SIGN_UP_WITH_EMAIL_BUTTON_LOCATOR_ANDROID = "com.instagram.android:id/sign_up_with_email_or_phone";
    List<MobileElement> signUpWithEmailButtonList;

    public SignUpPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public List<MobileElement> getSignUpWithEmailButtonList() {
        switch (platform) {
            case ("iOS"):
                signUpWithEmailButtonList = driver.findElementsByXPath(SIGN_UP_WITH_EMAIL_BUTTON_LOCATOR_IOS);
                break;
            case ("Android"):
                signUpWithEmailButtonList = driver.findElementsById(SIGN_UP_WITH_EMAIL_BUTTON_LOCATOR_ANDROID);
                break;
        }
        return signUpWithEmailButtonList;
    }

    public MobileElement getLogInButton() {
        switch (platform) {
            case ("iOS"):
                logInButton = driver.findElementByXPath(LOG_IN_BUTTON_LOCATOR_IOS);
                break;
            case ("Android"):
                logInButton = driver.findElementById(LOG_IN_BUTTON_LOCATOR_ANDROID);
                break;
        }
        return logInButton;
    }

    @Override
    public SignUpPage isPageOpened() {
        getLogInButton().isDisplayed();
        return this;
    }

    public SignInPage clickSignIn() {
        getLogInButton().click();
        return new SignInPage(driver);
    }
}
