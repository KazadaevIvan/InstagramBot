package steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.SearchPage;

@Log4j2
public class SearchPageSteps {
    private SearchPage searchPage;

    public SearchPageSteps(AppiumDriver<MobileElement> driver) {
        searchPage = new SearchPage(driver);
    }

    @Step("Find and open profile '{profileName}'")
    public SearchPageSteps findProfile(String profileName) {
        log.info(String.format("Find and open profile '%s'", profileName));
        searchPage
                .isPageOpened()
                .typeSearchInfo(profileName)
                .openSearchResult();
        return this;
    }
}
