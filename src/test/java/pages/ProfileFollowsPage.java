package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

public class ProfileFollowsPage extends BasePage {
    @AndroidFindBy(id = "com.instagram.android:id/row_search_edit_text")
    public MobileElement searchInput;

    @AndroidFindBy(id = "com.instagram.android:id/follow_list_container")
    public List<MobileElement> account;

    public ProfileFollowsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        initElements(this);
    }

    @Override
    public ProfileFollowsPage isPageOpened() {
        searchInput.isDisplayed();
        return this;
    }

    public ProfilePage openProfile(int number) {
        account.get(number).click();
        return new ProfilePage(driver);
    }
}
