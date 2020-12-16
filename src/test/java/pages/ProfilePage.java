package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import utils.appium.AppiumUtils;

import java.util.List;

public class ProfilePage extends BasePage {
    public static final String FOLLOWERS_LOCATOR_IOS = "user-detail-header-followers";
    public static final String FOLLOWERS_LOCATOR_ANDROID = "com.instagram.android:id/row_profile_header_followers_container";
    public static final String FOLLOW_BUTTON_LOCATOR_IOS = "user-detail-header-follow-button";
    public static final String FOLLOW_BUTTON_LOCATOR_ANDROID = "//android.widget.Button[@text='Follow']";
    public static final String LIST_VIEW_LOCATOR_IOS = "//XCUIElementTypeCollectionView[@name='profile']";
    public static final String LIST_VIEW_LOCATOR_ANDROID = "com.instagram.android:id/layout_container_main";
    public static final String BACK_BUTTON_LOCATOR_IOS = "//XCUIElementTypeNavigationBar/XCUIElementTypeButton[1]";
    public static final String BACK_BUTTON_LOCATOR_ANDROID = "com.instagram.android:id/action_bar_button_back";
    public static final String PHOTO_LIST_LOCATOR_IOS = "//XCUIElementTypeButton[@name='media-thumbnail-cell']";
    public static final String PHOTO_LIST_LOCATOR_ANDROID = "//androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout/android.widget.ImageView";
    public MobileElement followers;
    public MobileElement followButton;
    public MobileElement listView;
    public MobileElement backButton;
    public List<MobileElement> photoList;

    public ProfilePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public MobileElement getFollowers() {
        switch (platform) {
            case ("iOS"):
                followers = driver.findElementByAccessibilityId(FOLLOWERS_LOCATOR_IOS);
                break;
            case ("Android"):
                followers = driver.findElementById(FOLLOWERS_LOCATOR_ANDROID);
        }
        return followers;
    }

    public MobileElement getListView() {
        switch (platform) {
            case ("iOS"):
                listView = driver.findElementByXPath(LIST_VIEW_LOCATOR_IOS);
                break;
            case ("Android"):
                listView = driver.findElementById(LIST_VIEW_LOCATOR_ANDROID);
        }
        return listView;
    }

    public List<MobileElement> getPhotoList() {
        switch (platform) {
            case ("iOS"):
                photoList = driver.findElementsByXPath(PHOTO_LIST_LOCATOR_IOS);
                break;
            case ("Android"):
                photoList = driver.findElementsByXPath(PHOTO_LIST_LOCATOR_ANDROID);
        }
        return photoList;
    }

    @Override
    public ProfilePage isPageOpened() {
        getFollowers().isDisplayed();
        return this;
    }

    public SearchPage openFollowersList() {
        getFollowers().click();
        return new SearchPage(driver);
    }

    public ProfilePage clickFollowButton() {
        switch (platform) {
            case ("iOS"):
                followButton = driver.findElementByAccessibilityId(FOLLOW_BUTTON_LOCATOR_IOS);
                break;
            case ("Android"):
                followButton = driver.findElementByXPath(FOLLOW_BUTTON_LOCATOR_ANDROID);
        }
        followButton.click();
        return this;
    }

    public PostsPage openFirstPhoto() {
        Boolean isFoundElement = getPhotoList().size() > 0;
        while (!isFoundElement) {
            AppiumUtils.scrollByCoordinates(driver, getListView(), 0.9);
            isFoundElement = getPhotoList().size() > 0;
        }
        getPhotoList().get(0).click();
        return new PostsPage(driver);
    }

    public ProfileFollowsPage clickBackButton() {
        switch (platform) {
            case ("iOS"):
                backButton = driver.findElementByXPath(BACK_BUTTON_LOCATOR_IOS);
                break;
            case ("Android"):
                backButton = driver.findElementById(BACK_BUTTON_LOCATOR_ANDROID);
        }
        backButton.click();
        return new ProfileFollowsPage(driver);
    }
}
