package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class PostsPage extends BasePage {
    @AndroidFindBy(id = "com.instagram.android:id/row_feed_button_like")
    public MobileElement likeButton;

    @AndroidFindBy(id = "com.instagram.android:id/action_bar_button_back")
    public MobileElement backButton;

    public PostsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        initElements(this);
    }

    @Override
    public PostsPage isPageOpened() {
        likeButton.isDisplayed();
        return this;
    }

    public PostsPage clickLikeButton() {
        likeButton.click();
        return this;
    }

    public ProfilePage clickBackButton() {
        backButton.click();
        return new ProfilePage(driver);
    }
}
