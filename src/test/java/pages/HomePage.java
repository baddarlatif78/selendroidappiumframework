package pages;
import io.appium.java_client.android.AndroidDriver;
public class HomePage extends BasePage {
 public HomePage(AndroidDriver d){super(d);}
 public boolean isHomeDisplayed(){return isTextVisible("EN")||isTextVisible("Chrome")||isTextVisible("File");}
 public boolean hasExpectedControls(){return isTextVisible("Chrome")&&isTextVisible("File");}
 public void tapEnglish(){text("EN").click();}
 public void chooseNoNo(){textContains("No, no").click();}
 public void openChrome(){textContains("Chrome").click();}
 public void openRegistration(){textContains("File").click();}
 public void openProgress(){textContains("Show Progress Bar for a while").click();}
 public void showToast(){textContains("Displays a Toast").click();}
 public void openPopup(){textContains("Display Popup Window").click();}
 public void throwException(){textContains("Press to throw unhandled exception").click();}
 public void typeException(String value){textContains("Type to throw unhandled exception").sendKeys(value);}
}