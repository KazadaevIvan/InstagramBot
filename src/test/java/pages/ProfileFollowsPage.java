package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import utils.appium.AppiumUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ProfileFollowsPage extends BasePage {
    public static final String LIST_VIEW_LOCATOR_IOS = "follow-list";
    public static final String LIST_VIEW_LOCATOR_ANDROID = "android:id/list";
    public static final String FOLLOWERS_TAB_LOCATOR_IOS = "//XCUIElementTypeStaticText[contains(@label,'Followers')]";
    public static final String FOLLOWERS_TAB_LOCATOR_ANDROID = "//android.widget.TextView[contains(@text,'Followers')]";
    public MobileElement listView;
    public MobileElement followersTab;

    public List<MobileElement> usersList;

    public ProfileFollowsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public MobileElement getListView() {
        switch (platform) {
            case ("iOS"):
                listView = driver.findElementByAccessibilityId(LIST_VIEW_LOCATOR_IOS);
                break;
            case ("Android"):
                listView = driver.findElementById(LIST_VIEW_LOCATOR_ANDROID);
                break;
        }
        waitForElementToAppear(listView);
        return listView;
    }

    @Override
    public ProfileFollowsPage isPageOpened() {
        switch (platform) {
            case ("iOS"):
                followersTab = driver.findElementByXPath(FOLLOWERS_TAB_LOCATOR_IOS);
                break;
            case ("Android"):
                followersTab = driver.findElementByXPath(FOLLOWERS_TAB_LOCATOR_ANDROID);
                break;
        }
        waitForElementToAppear(followersTab);
        return this;
    }

    public List<MobileElement> getUsersList() {
        switch (platform) {
            case ("iOS"):
                usersList = driver.findElementsByXPath("//XCUIElementTypeButton[@name]/preceding-sibling::XCUIElementTypeOther[@name]");
                break;
            case ("Android"):
                usersList = driver.findElementsById("com.instagram.android:id/follow_list_username");
                break;
        }
        return usersList;
    }

    public List<String> getListOfProfilesToFollow(int profilesNumber) throws IOException {
        List<String> names = Files.readAllLines(Paths.get("src/test/resources/names.txt"));
        int checker = 0;
        for (int i = 0; i < 500; i++) {
            for (MobileElement el : getUsersList()) {
                if (!names.contains(el.getText())) {
                    names.add(el.getText());
                    Files.write(Paths.get("src/test/resources/names.txt"), names);
                    checker++;
                }
                getUsersList().remove(el);
                if (checker == profilesNumber) break;
            }
            if (checker == profilesNumber) break;
            AppiumUtils.scrollDownByCoordinates(driver, getListView(), 0.5);
        }
        return names.subList(names.size() - profilesNumber, names.size());
    }
}
