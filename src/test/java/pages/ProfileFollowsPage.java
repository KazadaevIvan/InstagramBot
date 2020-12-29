package pages;

import database.DBConnection;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import utils.appium.AppiumUtils;

import java.util.ArrayList;
import java.util.List;

public class ProfileFollowsPage extends BasePage {

    public ProfileFollowsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("Verify Profile Follows page is opened")
    @Override
    public ProfileFollowsPage isPageOpened() {
        waitForElementToAppear("followersTab");
        return this;
    }

    @Step("Get list of profiles to follow")
    public List<String> getListOfProfilesToFollow(int profilesNumber) {
        DBConnection db = new DBConnection();
        db.connect();
        List<String> profileNamesToFollow = new ArrayList<>();
        int counter = 0;
        String profileName;
        for (int i = 0; i < 500; i++) {
            for (MobileElement el : locationStrategy.getElementsList("usersList")) {
                if (waitForElementToAppear(el)) {
                    profileName = el.getText();
                    if (!db.containsProfileName(profileName)) {
                        profileNamesToFollow.add(profileName);
                        db.addProfileName(profileName);
                        counter++;
                    }
                }
                locationStrategy.getElementsList("usersList").remove(el);
                if (counter == profilesNumber) break;
            }
            if (counter == profilesNumber) break;
            AppiumUtils.scrollDownByCoordinates(driver, locationStrategy.getElement("profileFollowsPageListView"), 0.5);
        }
        db.close();
        return profileNamesToFollow;
    }
}
