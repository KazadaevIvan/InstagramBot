package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

abstract public class BasePage {
    @iOSXCUITFindBy(accessibility = "explore-tab")
    @AndroidFindBy(accessibility = "Search and Explore")
    public MobileElement searchPageIcon;

    AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;
    private static final int TIMEOUT = 30;
    private static final int POLLING = 100;

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
    }

    abstract public BasePage isPageOpened();

    void initElements(BasePage page) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), page);
    }

    public SearchPage openSearchPage(){
        searchPageIcon.click();
        return new SearchPage(driver);
    }
}
