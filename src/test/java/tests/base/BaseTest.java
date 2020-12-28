package tests.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.*;
import pages.HomePage;
import pages.ProfileFollowsPage;
import steps.LoginSteps;
import steps.ProfilePageSteps;
import steps.SearchPageSteps;
import utils.LocationStrategy;
import utils.appium.AppiumServerJava;
import utils.driver.AndroidDriverManager;
import utils.driver.DriverManager;
import utils.driver.IOSDriverManager;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {
    public String email = System.getProperty("email");
    public String password = System.getProperty("password");
    public String profile = System.getProperty("profile");
    public int numberOfProfilesToFollow = Integer.parseInt(System.getProperty("numberofprofilestofollow"));

    public AppiumDriverLocalService appiumServer;
    public AppiumDriver<MobileElement> driver;
    public LocationStrategy locationStrategy;
    public HomePage homePage;
    public ProfileFollowsPage profileFollowsPage;
    public LoginSteps loginSteps;
    public SearchPageSteps searchPageSteps;
    public ProfilePageSteps profilePageSteps;

    @Parameters(value = {"deviceName", "platform", "udid"})
    @BeforeMethod(description = "Open application")
    public void setUp(@Optional("xiaomi redmi 4x") String deviceName, @Optional("Android") String platform, @Optional("495dc6217cf4") String udid) {
        DriverManager manager;
        switch (platform) {
            case ("Android"):
                manager = new AndroidDriverManager();
                break;
            case ("iOS"):
                manager = new IOSDriverManager();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + platform);
        }
        appiumServer = AppiumServerJava.startServer();
        driver = manager.getDriver(appiumServer, deviceName, platform, udid);
        log.debug(String.format("Driver has been initialized. Device name: '%s', platform: '%s', udid: '%s'" + deviceName, platform, udid));
        homePage = new HomePage(driver);
        profileFollowsPage = new ProfileFollowsPage(driver);
        locationStrategy = new LocationStrategy(driver);
        loginSteps = new LoginSteps(driver);
        searchPageSteps = new SearchPageSteps(driver);
        profilePageSteps = new ProfilePageSteps(driver);
    }

    @AfterMethod(description = "Close application", alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        appiumServer.stop();
    }
}