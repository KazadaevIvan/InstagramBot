package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.util.List;

public class SignInPage extends BasePage {
    public static final String USER_NAME_INPUT_LOCATOR_IOS = "username-field";
    public static final String USER_NAME_INPUT_LOCATOR_ANDROID = "com.instagram.android:id/login_username";
    public static final String PASSWORD_INPUT_LOCATOR_IOS = "password-field";
    public static final String PASSWORD_INPUT_LOCATOR_ANDROID = "com.instagram.android:id/password";
    public static final String LOG_IN_BUTTON_LOCATOR_IOS = "login-button";
    public static final String LOG_IN_BUTTON_LOCATOR_ANDROID = "com.instagram.android:id/next_button";
    public MobileElement userNameInput;
    public MobileElement passwordInput;
    public MobileElement logInButton;

    List<MobileElement> loginButtonList;

    public SignInPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public MobileElement getLogInButton() {
        switch (platform) {
            case ("iOS"):
                logInButton = driver.findElementByAccessibilityId(LOG_IN_BUTTON_LOCATOR_IOS);
                break;
            case ("Android"):
                logInButton = driver.findElementById(LOG_IN_BUTTON_LOCATOR_ANDROID);
                break;
        }
        return logInButton;
    }

    public List<MobileElement> getLogInButtonList() {
        switch (platform) {
            case ("iOS"):
                loginButtonList = driver.findElementsByAccessibilityId(LOG_IN_BUTTON_LOCATOR_IOS);
                break;
            case ("Android"):
                loginButtonList = driver.findElementsById(LOG_IN_BUTTON_LOCATOR_ANDROID);
                break;
        }
        return loginButtonList;
    }

    @Override
    public SignInPage isPageOpened() {
        getLogInButton().isDisplayed();
        return this;
    }

    public SignInPage typeEmail(String email) {
        switch (platform) {
            case ("iOS"):
                userNameInput = driver.findElementByAccessibilityId(USER_NAME_INPUT_LOCATOR_IOS);
                break;
            case ("Android"):
                userNameInput = driver.findElementById(USER_NAME_INPUT_LOCATOR_ANDROID);
                break;
        }
        userNameInput.clear();
        userNameInput.sendKeys(email);
        return this;
    }

    public SignInPage typePassword(String password) {
        switch (platform) {
            case ("iOS"):
                passwordInput = driver.findElementByAccessibilityId(PASSWORD_INPUT_LOCATOR_IOS);
                break;
            case ("Android"):
                passwordInput = driver.findElementById(PASSWORD_INPUT_LOCATOR_ANDROID);
                break;
        }
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public HomePage clickLogInButton() {
        getLogInButton().click();
        return new HomePage(driver);
    }
}
