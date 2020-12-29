package utils.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;

import static io.appium.java_client.touch.offset.PointOption.point;

@Log4j2
public class AppiumUtils {
    public static void scrollDownByCoordinates(AppiumDriver<MobileElement> driver, MobileElement listView, double startYRation) {
        int height = listView.getSize().getHeight();
        int width = listView.getSize().getWidth();
        int startX = width / 2;
        int startY = (int) (height * startYRation);
        int endY = (int) (height * 0.1);

        log.debug("Scrolling down");

        TouchAction action = new TouchAction(driver);
        action
                .press(point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(point(startX, endY))
                .release().perform();
    }
}
