package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import utils.appium.AppiumUtils;

import java.util.List;

public class SearchPage extends BasePage {
    public static final String ACCOUNT_NAME_LOCATOR_IOS = "(//XCUIElementTypeStaticText[@name='%s'])[1]";
    public static final String ACCOUNT_NAME_LOCATOR_ANDROID = "//android.widget.TextView[@text='%s' and @resource-id='com.instagram.android:id/row_search_user_username']";
    public static final String SEARCH_INPUT_LOCATOR_IOS = "search-text-input";
    public static final String SEARCH_INPUT_LOCATOR_ANDROID = "com.instagram.android:id/action_bar_search_edit_text";
    public static final String ACCOUNT_TAB_LOCATOR_IOS = "search-user";
    public static final String ACCOUNT_TAB_LOCATOR_ANDROID = "//android.widget.TextView[@text='ACCOUNTS' and @resource-id='com.instagram.android:id/tab_button_name_text']";
    public static final String LIST_VIEW_LOCATOR_IOS = "//XCUIElementTypeCollectionView";
    public static final String LIST_VIEW_LOCATOR_ANDROID = "android:id/list";
    public List<MobileElement> accountNameList;
    public MobileElement account;
    public MobileElement searchInput;
    public MobileElement accountsTab;
    public MobileElement listView;

    public SearchPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public MobileElement getListView() {
        switch (platform) {
            case ("iOS"):
                listView = driver.findElementByXPath(LIST_VIEW_LOCATOR_IOS);
                break;
            case ("Android"):
                listView = driver.findElementById(LIST_VIEW_LOCATOR_ANDROID);
                break;
        }
        return listView;
    }

    public MobileElement getSearchInput() {
        switch (platform) {
            case ("iOS"):
                searchInput = driver.findElementByAccessibilityId(SEARCH_INPUT_LOCATOR_IOS);
                break;
            case ("Android"):
                searchInput = driver.findElementById(SEARCH_INPUT_LOCATOR_ANDROID);
                break;
        }
        return searchInput;
    }

    public MobileElement getAccount(String name) {
        switch (platform) {
            case ("iOS"):
                account = driver.findElementByXPath(String.format(ACCOUNT_NAME_LOCATOR_IOS, name));
                break;
            case ("Android"):
                account = driver.findElementByXPath(String.format(ACCOUNT_NAME_LOCATOR_ANDROID, name));
                break;
        }
        return account;
    }

    public List<MobileElement> getAccountNameList(String name) {
        switch (platform) {
            case ("iOS"):
                accountNameList = driver.findElementsByXPath(String.format(ACCOUNT_NAME_LOCATOR_IOS, name));
                break;
            case ("Android"):
                accountNameList = driver.findElementsByXPath(String.format(ACCOUNT_NAME_LOCATOR_ANDROID, name));
                break;
        }
        return accountNameList;
    }

    @Override
    public SearchPage isPageOpened() {
        getSearchInput().isDisplayed();
        return this;
    }

    public SearchPage typeSearchInfo(String info) {
        getSearchInput().click();
        getSearchInput().sendKeys(info);
        switch (platform) {
            case ("iOS"):
                accountsTab = driver.findElementByAccessibilityId(ACCOUNT_TAB_LOCATOR_IOS);
                break;
            case ("Android"):
                accountsTab = driver.findElementByXPath(ACCOUNT_TAB_LOCATOR_ANDROID);
                break;
        }
        accountsTab.click();
        return this;
    }

    public SearchPage openSearchResult() {
        boolean isFoundElement = getAccountNameList(getSearchInput().getText()).size() > 0;
        while (!isFoundElement) {
            AppiumUtils.scrollByCoordinates(driver, getListView(), 0.9);
            isFoundElement = getAccountNameList(getSearchInput().getText()).size() > 0;
        }
        getAccount(getSearchInput().getText()).click();
        return this;
    }

}
