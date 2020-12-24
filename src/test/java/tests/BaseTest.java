package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
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

public class BaseTest {
    String email = System.getenv().getOrDefault("email", PropertyReader.getProperty("email"));
    String password = System.getenv().getOrDefault("password", PropertyReader.getProperty("password"));
    String profile = System.getenv().getOrDefault("profile", PropertyReader.getProperty("profile"));
    int numberOfProfilesToFollow = Integer.parseInt(System.getenv().getOrDefault("number.of.profiles.to.follow", PropertyReader.getProperty("number.of.profiles.to.follow")));

    AppiumDriverLocalService appiumServer;
    AppiumDriver<MobileElement> driver;
    LocationStrategy locationStrategy;
    HomePage homePage;
    ProfileFollowsPage profileFollowsPage;
    LoginSteps loginSteps;
    SearchPageSteps searchPageSteps;
    ProfilePageSteps profilePageSteps;

    @Step("Open application on '{deviceName}'")
    @Parameters(value = {"deviceName", "platform", "udid"})
    @BeforeMethod
    public void setUp(@Optional("xiaomi redmi 4x") String deviceName, @Optional("Android") String platform, @Optional("495dc6217cf4") String udid) {
        DriverManager manager = null;
        switch (platform) {
            case ("Android"):
                manager = new AndroidDriverManager();
                break;
            case ("iOS"):
                manager = new IOSDriverManager();
                break;
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

    @Step("Close application")
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        appiumServer.stop();
    }
}
