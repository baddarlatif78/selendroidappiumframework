package base;

import config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;

public class BaseTest {
    protected AndroidDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(ConfigReader.get("platform.name", "Android"))
                .setAutomationName(ConfigReader.get("automation.name", "UiAutomator2"))
                .setDeviceName(ConfigReader.get("device.name", "Android Emulator"))
                .setApp(resolveAppPath(ConfigReader.get("app.path", "selendroid-test-app.apk")))
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(Duration.ofSeconds(ConfigReader.getInt("new.command.timeout", 120)));
        String packageName = ConfigReader.get("app.package");
        String activityName = ConfigReader.get("app.activity");
        if (packageName != null && !packageName.isBlank()) options.setAppPackage(packageName);
        if (activityName != null && !activityName.isBlank()) options.setAppActivity(activityName);
        driver = new AndroidDriver(new URL(ConfigReader.get("appium.server.url", "http://127.0.0.1:4723")), options);
    }

    private String resolveAppPath(String configuredPath) {
        String override = System.getProperty("app.path");
        String path = (override == null || override.isBlank()) ? configuredPath : override;
        Path resolved = Path.of(path);
        if (!resolved.isAbsolute()) resolved = Path.of(System.getProperty("user.dir")).resolve(resolved);
        return resolved.normalize().toString();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            try { driver.quit(); } finally { driver = null; }
        }
    }
}