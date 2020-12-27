package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import utils.appium.AppiumUtils;

public class SearchPage extends BasePage {

    public SearchPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Verify Search page is opened")
    @Override
    public SearchPage isPageOpened() {
        waitForElementToAppear(locationStrategy.getElement("searchInput"));
        return this;
    }

    @Step("Input '{info}' into search input, switch to Accounts tab")
    public SearchPage typeSearchInfo(String info) {
        locationStrategy.getElement("searchInput").click();
        locationStrategy.getElement("searchInput").clear();
        locationStrategy.getElement("searchInput").sendKeys(info);
        locationStrategy.getElement("accountTab").click();
        return this;
    }

    @Step("Open profile")
    public SearchPage openSearchResult() {
        boolean isFoundElement = locationStrategy.getElementsList("accountName", locationStrategy.getElement("searchInput").getText()).size() > 0;
        while (!isFoundElement) {
            AppiumUtils.scrollDownByCoordinates(driver, locationStrategy.getElement("searchPageListView"), 0.9);
            isFoundElement = locationStrategy.getElementsList("accountName", locationStrategy.getElement("searchInput").getText()).size() > 0;
        }
        locationStrategy.getElement("accountName", locationStrategy.getElement("searchInput").getText()).click();
        return this;
    }
}
