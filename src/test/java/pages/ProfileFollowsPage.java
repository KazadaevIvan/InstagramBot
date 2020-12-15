package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utils.appium.AppiumUtils;

public class ProfileFollowsPage extends BasePage {
    public static final String ACCOUNT = "//android.widget.Button[@text='Follow']/../android.widget.LinearLayout";

    @AndroidFindBy(id = "com.instagram.android:id/row_search_edit_text")
    public MobileElement searchInput;

    @AndroidFindBy(id = "android:id/list")
    public MobileElement listView;

    public ProfileFollowsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        initElements(this);
    }

    @Override
    public ProfileFollowsPage isPageOpened() {
        searchInput.isDisplayed();
        return this;
    }

    public ProfilePage openProfile() {
        Boolean isFoundElement = driver.findElementsByXPath(ACCOUNT).size() > 0;
        while(!isFoundElement) {
            AppiumUtils.scrollByCoordinates(driver, listView, 0.6);
            isFoundElement = driver.findElementsByXPath(ACCOUNT).size() > 0;
        }
        driver.findElementByXPath(ACCOUNT).click();
        return new ProfilePage(driver);
    }
}
