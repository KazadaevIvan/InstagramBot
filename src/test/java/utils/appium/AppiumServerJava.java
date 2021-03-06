package utils.appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import lombok.extern.log4j.Log4j2;
import utils.PropertyManager;

import java.util.concurrent.ThreadLocalRandom;

@Log4j2
public final class AppiumServerJava {

    public static AppiumDriverLocalService startServer() {
        final String url = PropertyManager.getInstance().get("appium.server.url");
        final String appiumJsRunner = PropertyManager.getInstance().get("appium.server.js.path");
        final String pathName = PropertyManager.getInstance().get("path.name");
        int randomNumber = ThreadLocalRandom.current().nextInt(20000, 30000);
        final int appiumServerPort = randomNumber;
        log.debug("Appium port: " + appiumServerPort);

        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(
                new AppiumServiceBuilder()
                        .withIPAddress(url)
                        .usingPort(appiumServerPort)
//                        .withAppiumJS(new File(appiumJsRunner))
//                        .usingDriverExecutable(new File(pathName))
                        .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                        .withArgument(GeneralServerFlag.LOG_LEVEL, "error"));
        service.start();
        return service;
    }
}