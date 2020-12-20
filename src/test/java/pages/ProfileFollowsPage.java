package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.json.simple.parser.ParseException;
import utils.appium.AppiumUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ProfileFollowsPage extends BasePage {

    public ProfileFollowsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Override
    public ProfileFollowsPage isPageOpened() throws IOException, ParseException {
        locationStrategy.getElement("followersTab").isDisplayed();
        return this;
    }

    public List<String> getListOfProfilesToFollow(int profilesNumber) throws IOException {
        List<String> names = Files.readAllLines(Paths.get("src/test/resources/names.txt"));
        int checker = 0;
        String profileName;
        for (int i = 0; i < 500; i++) {
            for (MobileElement el : locationStrategy.getElementsList("usersList")) {
                profileName = el.getText();
                if (!names.contains(profileName)) {
                    names.add(profileName);
                    Files.write(Paths.get("src/test/resources/names.txt"), names);
                    checker++;
                }
                locationStrategy.getElementsList("usersList").remove(el);
                if (checker == profilesNumber) break;
            }
            if (checker == profilesNumber) break;
            AppiumUtils.scrollDownByCoordinates(driver, locationStrategy.getElement("profileFollowsPageListView"), 0.5);
        }
        return names.subList(names.size() - profilesNumber, names.size());
    }
}
