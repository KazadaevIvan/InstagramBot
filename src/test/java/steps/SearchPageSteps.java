package steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import pages.SearchPage;

public class SearchPageSteps {
    private SearchPage searchPage;

    public SearchPageSteps(AppiumDriver<MobileElement> driver) {
        searchPage = new SearchPage(driver);
    }

    @Step("Find and open profile '{profileName}'")
    public SearchPageSteps findProfile(String profileName) {
        searchPage
                .isPageOpened()
                .typeSearchInfo(profileName)
                .openSearchResult();
        return this;
    }
}
