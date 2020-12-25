package pages;

import database.DBConnection;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import utils.appium.AppiumUtils;

import java.util.ArrayList;
import java.util.List;

public class ProfileFollowsPage extends BasePage {

    public ProfileFollowsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Override
    public ProfileFollowsPage isPageOpened() {
        waitForElementToAppear(locationStrategy.getElement("followersTab"));
        return this;
    }

    public List<String> getListOfProfilesToFollow(int profilesNumber) {
        DBConnection db = new DBConnection();
        db.connect();
        List<String> profileNamesToFollow = new ArrayList<>();
        int checker = 0;
        String profileName;
        for (int i = 0; i < 500; i++) {
            for (MobileElement el : locationStrategy.getElementsList("usersList")) {
                profileName = el.getText();
                if (!db.containsProfileName(profileName)) {
                    profileNamesToFollow.add(profileName);
                    db.addProfileName(profileName);
                    checker++;
                }
                locationStrategy.getElementsList("usersList").remove(el);
                if (checker == profilesNumber) break;
            }
            if (checker == profilesNumber) break;
            AppiumUtils.scrollDownByCoordinates(driver, locationStrategy.getElement("profileFollowsPageListView"), 0.5);
        }
        db.close();
        return profileNamesToFollow;
    }
}
