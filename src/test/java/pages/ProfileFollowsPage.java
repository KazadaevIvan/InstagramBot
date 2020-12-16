package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import utils.appium.AppiumUtils;

import java.util.List;

public class ProfileFollowsPage extends BasePage {
    public static final String ACCOUNT_LOCATOR_IOS = "//XCUIElementTypeButton[not(contains(@name, 'Following'))]/../XCUIElementTypeOther";
    public static final String ACCOUNT_LOCATOR_ANDROID = "//android.widget.Button[@text='Follow']/../android.widget.LinearLayout";
    public static final String SEARCH_INPUT_LOCATOR_IOS = "search-text-input";
    public static final String SEARCH_INPUT_LOCATOR_ANDROID = "com.instagram.android:id/row_search_edit_text";
    public static final String LIST_VIEW_LOCATOR_IOS = "follow-list";
    public static final String LIST_VIEW_LOCATOR_ANDROID = "android:id/list";
    public MobileElement account;
    public List<MobileElement> accountList;
    public MobileElement searchInput;
    public MobileElement listView;

    public ProfileFollowsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public MobileElement getListView() {
        switch (platform) {
            case ("iOS"):
                listView = driver.findElementByAccessibilityId(LIST_VIEW_LOCATOR_IOS);
                break;
            case ("Android"):
                listView = driver.findElementById(LIST_VIEW_LOCATOR_ANDROID);
        }
        return listView;
    }

    public List<MobileElement> getAccountList() {
        switch (platform) {
            case ("iOS"):
                accountList = driver.findElementsByXPath(ACCOUNT_LOCATOR_IOS);
                break;
            case ("Android"):
                accountList = driver.findElementsByXPath(ACCOUNT_LOCATOR_ANDROID);
        }
        return accountList;
    }

    public MobileElement getAccount() {
        switch (platform) {
            case ("iOS"):
                account = driver.findElementByXPath(ACCOUNT_LOCATOR_IOS);
                break;
            case ("Android"):
                account = driver.findElementByXPath(ACCOUNT_LOCATOR_ANDROID);
        }
        return account;
    }

    @Override
    public ProfileFollowsPage isPageOpened() {
        switch (platform) {
            case ("iOS"):
                searchInput = driver.findElementByAccessibilityId(SEARCH_INPUT_LOCATOR_IOS);
                break;
            case ("Android"):
                searchInput = driver.findElementById(SEARCH_INPUT_LOCATOR_ANDROID);
        }
        searchInput.isDisplayed();
        return this;
    }

    public ProfilePage openProfile() {
        Boolean isFoundElement = getAccountList().size() > 0;
        while (!isFoundElement) {
            AppiumUtils.scrollByCoordinates(driver, getListView(), 0.6);
            isFoundElement = getAccountList().size() > 0;
        }
        getAccount().click();
        return new ProfilePage(driver);
    }
}
