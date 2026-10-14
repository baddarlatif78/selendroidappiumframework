package pages;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
public class ChromePage extends BasePage {
 public ChromePage(AndroidDriver d){super(d);}
 public boolean hasGreeting(){return isTextVisible("Hello")||isTextVisible("can you")||sourceContains("Hello");}
 public void enterName(String name){switchToWebViewIfAvailable();List<WebElement> fields=driver.findElements(By.cssSelector("input"));if(fields.isEmpty()){switchToNative();throw new NoSuchElementException("Name input not found; inspect Appium Inspector.");}fields.get(0).clear();fields.get(0).sendKeys(name);}
 public void selectCar(String car){List<WebElement> selects=driver.findElements(By.cssSelector("select"));if(!selects.isEmpty()){new Select(selects.get(0)).selectByVisibleText(car);return;}try{textContains("Volvo").click();}catch(NoSuchElementException ignored){}textContains(car).click();}
 public void submit(){try{textContains("Send me your name").click();}catch(TimeoutException e){List<WebElement>b=driver.findElements(By.cssSelector("input[type='submit'],button"));if(b.isEmpty())throw e;b.get(0).click();}}
 public boolean contains(String value){return isTextVisible(value)||sourceContains(value);}
 public void clickHere(){try{textContains("here").click();}catch(TimeoutException e){List<WebElement>a=driver.findElements(By.cssSelector("a"));if(a.isEmpty())throw e;a.get(a.size()-1).click();}}
}