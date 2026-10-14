package pages;

import config.ConfigReader;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected final AndroidDriver driver;
    protected final WebDriverWait wait;
    protected BasePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("explicit.wait.seconds", 15)));
    }
    protected WebElement text(String value) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().text(" + quote(value) + ")")));
    }
    protected WebElement textContains(String value) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().textContains(" + quote(value) + ")")));
    }
    private String quote(String value) { return "\"" + value.replace("\\", "\\\\").replace("\"", "\\\"") + "\""; }
    public boolean isTextVisible(String value) {
        try { return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().textContains(" + quote(value) + ")")).isDisplayed(); }
        catch (NoSuchElementException e) { return false; }
    }
    public boolean sourceContains(String value) {
        try { return driver.getPageSource().contains(value); } catch (WebDriverException e) { return false; }
    }
    public void switchToWebViewIfAvailable() {
        try { for (String context : driver.getContextHandles()) if (context.toUpperCase().contains("WEBVIEW")) { driver.context(context); return; } }
        catch (WebDriverException ignored) {}
    }
    public void switchToNative() { try { driver.context("NATIVE_APP"); } catch (WebDriverException ignored) {} }
}