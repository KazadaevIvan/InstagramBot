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
        locationStrategy.getElement("searchInput").isDisplayed();
        return this;
    }

    public SearchPage typeSearchInfo(String info) throws IOException {
        locationStrategy.getElement("searchInput").click();
        locationStrategy.getElement("searchInput").clear();
        locationStrategy.getElement("searchInput").sendKeys(info);
        locationStrategy.getElement("accountTab").click();
        return this;
    }

    public SearchPage openSearchResult() throws IOException {
        boolean isFoundElement = locationStrategy.getElementsList("accountName", locationStrategy.getElement("searchInput").getText()).size() > 0;
        while (!isFoundElement) {
            AppiumUtils.scrollDownByCoordinates(driver, locationStrategy.getElement("searchPageListView"), 0.9);
            isFoundElement = locationStrategy.getElementsList("accountName", locationStrategy.getElement("searchInput").getText()).size() > 0;
        }
        locationStrategy.getElement("accountName", locationStrategy.getElement("searchInput").getText()).click();
        return this;
    }
}
