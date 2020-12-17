package utils.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;

import java.time.Duration;

import static io.appium.java_client.touch.offset.PointOption.point;

public class AppiumUtils {
    public static void scrollByCoordinates(AppiumDriver<MobileElement> driver, MobileElement listView, double startYRation) {
        int height = listView.getSize().getHeight();
        int width = listView.getSize().getWidth();
        int startX = width/2;
        int startY = (int) (height * startYRation);
        int endY = (int) (height * 0.1);

        TouchAction action = new TouchAction(driver);
        action
                .press(point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(point(startX, endY))
                .release().perform();
    }
}
