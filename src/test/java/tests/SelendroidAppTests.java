package tests;
import base.BaseTest;
import pages.*;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class SelendroidAppTests extends BaseTest {
 private HomePage home(){return new HomePage(driver);}
 @Test(priority=1,description="TC01 - Launch and verify home screen")
 public void TC01_home(){Assert.assertTrue(home().isHomeDisplayed(),"Home controls not visible");Assert.assertTrue(home().hasExpectedControls(),"Chrome and File controls not visible");}
 @Test(priority=2,description="TC02 - EN then No, no")
 public void TC02_language(){home().tapEnglish();home().chooseNoNo();Assert.assertTrue(home().isHomeDisplayed(),"Home not displayed");}
 @Test(priority=3,description="TC03 - Chrome, name, Mercedes, Volvo")
 public void TC03_chrome(){home().openChrome();ChromePage p=new ChromePage(driver);Assert.assertTrue(p.hasGreeting(),"Greeting missing");p.enterName("Appium Tester");p.selectCar("Mercedes");p.submit();Assert.assertTrue(p.contains("Appium Tester"),"Name missing");Assert.assertTrue(p.contains("Mercedes"),"Car missing");p.clickHere();Assert.assertTrue(p.contains("Volvo"),"Default car is not Volvo");p.switchToNative();}
 @Test(priority=4,description="TC04 - Registration")
 public void TC04_registration(){home().openRegistration();RegistrationPage p=new RegistrationPage(driver);Assert.assertTrue(p.isDisplayed(),"Registration screen missing");Assert.assertTrue(p.hasDefaultName("Mr. Burns"),"Mr. Burns default not detected");Assert.assertTrue(p.hasDefaultLanguage("Ruby"),"Ruby default not detected");p.fillFirstTwoFields("Appium User","appium@example.com");p.acceptAdsIfPresent();p.register();Assert.assertTrue(p.containsDetails("Appium User","appium@example.com"),"Submitted details missing");}
 @Test(priority=5,description="TC05 - Progress loader")
 public void TC05_progress(){home().openProgress();new WebDriverWait(driver,Duration.ofSeconds(30)).until(d->d.findElements(By.className("android.widget.ProgressBar")).isEmpty());Assert.assertTrue(new RegistrationPage(driver).isDisplayed(),"Registration screen missing after progress");}
 @Test(priority=6,description="TC06 - Toast")
 public void TC06_toast(){home().showToast();boolean seen;try{seen=new WebDriverWait(driver,Duration.ofSeconds(3)).until(d->d.findElements(AppiumBy.xpath("//*[contains(@text,'Toast') or contains(@text,'toast')]")).stream().anyMatch(WebElement::isDisplayed));}catch(TimeoutException e){seen=false;}Assert.assertTrue(seen,"Toast not detected; verify actual message");}
 @Test(priority=7,description="TC07 - Popup dismiss")
 public void TC07_popup(){home().openPopup();new PopupPage(driver).dismiss();Assert.assertTrue(home().isHomeDisplayed(),"Home not visible after dismiss");}
 @Test(priority=8,description="TC08 - Exception button")
 public void TC08_exceptionButton(){home().throwException();boolean visible;try{visible=new WebDriverWait(driver,Duration.ofSeconds(5)).until(d->home().isHomeDisplayed());}catch(Exception e){visible=false;}Assert.assertTrue(visible,"App did not remain/recover on home screen");}
 @Test(priority=9,description="TC09 - Exception text")
 public void TC09_exceptionText(){home().typeException("test");Assert.assertTrue(driver.getPageSource().contains("test"),"Typed text not found");Assert.assertTrue(home().isHomeDisplayed(),"Home screen not visible");}
}