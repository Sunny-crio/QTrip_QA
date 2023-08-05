package qtriptest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage {

    RemoteWebDriver driver;
    //@FindBy(xpath="*//input[@name='email']")
    @FindBy(xpath ="*//input[@id='floatingInput']")
    WebElement Email;
    @FindBy(xpath="*//input[@name='password']")
    WebElement Password;
    @FindBy(xpath="*//button[text()='Login to QTrip']")
    WebElement loginbutton;

    
    
    public LoginPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);

        
    }

    
    public void performlogin(String username, String password) throws InterruptedException{
        Thread.sleep(2000);
      
        Email.sendKeys(username);
        Password.sendKeys(password);
        loginbutton.click();
      
        }

    public boolean veryuserloggedin(){
        boolean userloggedin=true;
        if(driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/")){

          return userloggedin;
            
        }
        else {
            return !userloggedin;
        }
    }
}
