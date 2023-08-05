package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.sql.Timestamp;

public class testCase_01 {
     static RemoteWebDriver driver;
    

     
    // SoftAssert softassert;
     @FindBy(xpath="*//a[@class='nav-link login register']")
     WebElement enterintoregisterpagebutton;

     @BeforeTest(alwaysRun=true)
public static void createDriver() throws MalformedURLException {

    // final DesiredCapabilities capabilities = new DesiredCapabilities();
    // capabilities.setBrowserName(BrowserType.CHROME);
    // driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities); // This line creates a new instance of RemoteWebDriver in each test class

    driver=DriverSingleton.createDriver();

    driver.manage().window().maximize();
}

    

@Test(dataProvider = "DatasetsforQTrip", dataProviderClass =DP.class, enabled = true, description = "verify Login flow" , priority = 1, groups={"Login Flow"})
    public void TestCase01(String Username,String Password) throws Exception 
    {
      driver=DriverSingleton.createDriver();
    
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
       Username = Username + String.valueOf(timestamp.getTime());
       Password = Password + String.valueOf(timestamp.getTime());
     
       System.out.println(Username + Password);

      
      RegisterPage registerp= new RegisterPage(driver);

        

      SoftAssert softassert = new SoftAssert();
        driver.manage().window().maximize();
//driver.navigate().to("https://qtripdynamic-qa-frontend.vercel.app/");

driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/register/");

       // enterintoregisterpagebutton.click();
        
 registerp.registernewuser(Username,Password,true);
      
      //softassert.assertTrue( register.registernewuser(Username,Password,true),"registration failed");

      LoginPage login= new LoginPage(driver);

       login.performlogin(Username,Password);
login.veryuserloggedin();
 softassert.assertAll();
      
      
    }
    

}
