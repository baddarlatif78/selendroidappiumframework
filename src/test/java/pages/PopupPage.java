package pages;
import io.appium.java_client.android.AndroidDriver;
public class PopupPage extends BasePage {public PopupPage(AndroidDriver d){super(d);}public void dismiss(){textContains("Dismiss").click();}}