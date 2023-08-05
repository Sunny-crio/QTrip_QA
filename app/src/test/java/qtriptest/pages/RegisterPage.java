package qtriptest.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.annotations.Test;

public class RegisterPage {
     RemoteWebDriver driver;

     @FindBy(name="email")
    WebElement email;
    @FindBy(xpath="*//div//input[@name='password']")
    WebElement password;
    @FindBy(xpath="*//div//input[@name='confirmpassword']")
    WebElement confirmpassword; 
    @FindBy(xpath="*//div//button[text()='Register Now']")
    WebElement registerbutton;
  
  String url="https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
    
    public RegisterPage(RemoteWebDriver driver) {
       this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void navigatetoRegisterpage()
    { if (!this.driver.getCurrentUrl().equals(this.url)) {
      this.driver.get(this.url);
  }
    }
  
    @Test
    public boolean registernewuser(String emailaddress, String passwordtext, Boolean isUserregistered) 
      {
     
       isUserregistered =true;
        if(driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/pages/register/"))
        {
      
       email.sendKeys(emailaddress);
       password.sendKeys(passwordtext);
       confirmpassword.sendKeys(passwordtext);
       registerbutton.click(); 
       isUserregistered= driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/pages/login/");

       }
      
       else {
        return isUserregistered = false;
       }

      return isUserregistered;
      
      }



    }