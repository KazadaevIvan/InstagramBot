package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.*;
import utils.appium.AppiumServerJava;
import utils.driver.AndroidDriverManager;
import utils.driver.DriverManager;
import utils.driver.IOSDriverManager;

public class BaseTest {
    AppiumDriverLocalService appiumServer;
    AppiumDriver driver;
    SignUpPage signUpPage;
    SignInPage signInPage;
    HomePage homePage;
    SearchPage searchPage;
    ProfilePage profilePage;
    ProfileFollowsPage profileFollowsPage;
    PostsPage postsPage;

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
        signUpPage = new SignUpPage(driver);
        signInPage = new SignInPage(driver);
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
        profilePage = new ProfilePage(driver);
        profileFollowsPage = new ProfileFollowsPage(driver);
        postsPage = new PostsPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        appiumServer.stop();
    }
}
