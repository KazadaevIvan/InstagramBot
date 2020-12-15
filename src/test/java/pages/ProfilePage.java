package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utils.appium.AppiumUtils;

public class ProfilePage extends BasePage {
    @AndroidFindBy(id = "com.instagram.android:id/row_profile_header_followers_container")
    public MobileElement followers;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Follow']")
    public MobileElement followButton;

    @AndroidFindBy(id = "com.instagram.android:id/layout_container_main")
    public MobileElement listView;

    @AndroidFindBy(id = "com.instagram.android:id/action_bar_button_back")
    public MobileElement backButton;

    public ProfilePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        initElements(this);
    }

    @Override
    public ProfilePage isPageOpened() {
        followers.isDisplayed();
        return this;
    }

    public SearchPage openFollowersList() {
        followers.click();
        return new SearchPage(driver);
    }

    public ProfilePage clickFollowButton() {
        followButton.click();
        return this;
    }

    public PostsPage openFirstPhoto() {
        Boolean isFoundElement = driver.findElementsByXPath("//androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout/android.widget.ImageView").size() > 0;
        while(!isFoundElement) {
            AppiumUtils.scrollByCoordinates(driver, listView, 0.9);
            isFoundElement = driver.findElementsByXPath("//androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout/android.widget.ImageView").size() > 0;
        }
        driver.findElementsByXPath("//androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout/android.widget.ImageView").get(0).click();
        return new PostsPage(driver);
    }

    public ProfileFollowsPage clickBackButton() {
        backButton.click();
        return new ProfileFollowsPage(driver);
    }
}
