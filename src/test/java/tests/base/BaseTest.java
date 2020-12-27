package tests.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.*;
import pages.HomePage;
import pages.ProfileFollowsPage;
import steps.LoginSteps;
import steps.ProfilePageSteps;
import steps.SearchPageSteps;
import utils.LocationStrategy;
import utils.PropertyReader;
import utils.appium.AppiumServerJava;
import utils.driver.AndroidDriverManager;
import utils.driver.DriverManager;
import utils.driver.IOSDriverManager;

@Listeners(TestListener.class)
public class BaseTest {
    public String email = System.getenv().getOrDefault("email", PropertyReader.getProperty("email"));
    public String password = System.getenv().getOrDefault("password", PropertyReader.getProperty("password"));
    public String profile = System.getenv().getOrDefault("profile", PropertyReader.getProperty("profile"));
    public int numberOfProfilesToFollow = Integer.parseInt(System.getenv().getOrDefault("number.of.profiles.to.follow", PropertyReader.getProperty("number.of.profiles.to.follow")));

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