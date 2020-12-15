package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import utils.appium.AppiumUtils;

public class SearchPage extends BasePage {
    public static final String ACCOUNT_NAME_LOCATOR = "//android.widget.TextView[@text='%s' and @resource-id='com.instagram.android:id/row_search_user_username']";

    @iOSXCUITFindBy(accessibility = "search-text-input")
    @AndroidFindBy(id = "com.instagram.android:id/action_bar_search_edit_text")
    public MobileElement searchInput;

    @iOSXCUITFindBy(accessibility = "search-user")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ACCOUNTS' and @resource-id='com.instagram.android:id/tab_button_name_text']")
    public MobileElement accountsTab;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCollectionView")
    @AndroidFindBy(id = "android:id/list")
    public MobileElement listView;

    public SearchPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        initElements(this);
    }

    @Override
    public SearchPage isPageOpened() {
        searchInput.isDisplayed();
        return this;
    }

    public SearchPage typeSearchInfo(String info) {
        searchInput.click();
        searchInput.sendKeys(info);
        accountsTab.click();
        return this;
    }

    public SearchPage openSearchResult() {
        Boolean isFoundElement = driver.findElementsByXPath(String.format(ACCOUNT_NAME_LOCATOR, searchInput.getText())).size() > 0;
        while(!isFoundElement) {
            AppiumUtils.scrollByCoordinates(driver, listView, 0.9);
            isFoundElement = driver.findElementsByXPath(String.format(ACCOUNT_NAME_LOCATOR, searchInput.getText())).size() > 0;
        }
        driver.findElementByXPath(String.format(ACCOUNT_NAME_LOCATOR, searchInput.getText())).click();
        return this;
    }

}
