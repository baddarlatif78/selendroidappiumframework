package pages;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import java.util.List;
public class RegistrationPage extends BasePage {
 public RegistrationPage(AndroidDriver d){super(d);}
 public boolean isDisplayed(){return isTextVisible("Welcome to register")||isTextVisible("register");}
 public List<WebElement> fields(){return driver.findElements(By.className("android.widget.EditText"));}
 public boolean hasDefaultName(String value){for(WebElement e:fields())if(value.equals(e.getText())||value.equals(e.getAttribute("text")))return true;return sourceContains(value);}
 public boolean hasDefaultLanguage(String value){return isTextVisible(value)||sourceContains(value);}
 public void fillFirstTwoFields(String name,String email){List<WebElement> f=fields();if(f.size()<2)throw new NoSuchElementException("Expected two editable fields; found "+f.size());f.get(0).clear();f.get(0).sendKeys(name);f.get(1).clear();f.get(1).sendKeys(email);}
 public void acceptAdsIfPresent(){for(WebElement c:driver.findElements(By.className("android.widget.CheckBox")))if(!c.isSelected())c.click();}
 public void register(){textContains("Register User").click();}
 public boolean containsDetails(String name,String email){return isTextVisible(name)||sourceContains(name)||isTextVisible(email)||sourceContains(email);}
}