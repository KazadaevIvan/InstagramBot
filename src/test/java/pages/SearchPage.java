package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import utils.appium.AppiumUtils;

import java.io.IOException;

public class SearchPage extends BasePage {

    public SearchPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Override
    public SearchPage isPageOpened() throws IOException {
        waitForElementToAppear(locationStrategy.getElement("searchInput"));
        return this;
    }

    public SearchPage typeSearchInfo(String info) {
        locationStrategy.getElement("searchInput").click();
        locationStrategy.getElement("searchInput").clear();
        locationStrategy.getElement("searchInput").sendKeys(info);
        locationStrategy.getElement("accountTab").click();
        return this;
    }

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
