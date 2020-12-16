package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PostsPage extends BasePage {
    public static final String LIKE_BUTTON_LOCATOR_IOS = "like-button";
    public static final String LIKE_BUTTON_LOCATOR_ANDROID = "com.instagram.android:id/row_feed_button_like";
    public static final String BACK_BUTTON_LOCATOR_IOS = "//XCUIElementTypeNavigationBar[@name='navigation-bar']/XCUIElementTypeButton[1]";
    public static final String BACK_BUTTON_LOCATOR_ANDROID = "com.instagram.android:id/action_bar_button_back";
    public MobileElement likeButton;
    public MobileElement backButton;

    public PostsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public MobileElement getLikeButton() {
        switch (platform) {
            case ("iOS"):
                likeButton = driver.findElementByAccessibilityId(LIKE_BUTTON_LOCATOR_IOS);
                break;
            case ("Android"):
                likeButton = driver.findElementById(LIKE_BUTTON_LOCATOR_ANDROID);
                break;
        }
        return likeButton;
    }

    @Override
    public PostsPage isPageOpened() {
        getLikeButton().isDisplayed();
        return this;
    }

    public PostsPage clickLikeButton() {
        getLikeButton().click();
        return this;
    }

    public ProfilePage clickBackButton() {
        switch (platform) {
            case ("iOS"):
                backButton = driver.findElementByXPath(BACK_BUTTON_LOCATOR_IOS);
                break;
            case ("Android"):
                backButton = driver.findElementById(BACK_BUTTON_LOCATOR_ANDROID);
        }
        backButton.click();
        return new ProfilePage(driver);
    }
}
