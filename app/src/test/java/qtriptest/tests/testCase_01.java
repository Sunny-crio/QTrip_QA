package qtriptest.tests;

import qtriptest.DP;
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
     
     SoftAssert softassert;
     @FindBy(xpath="*//a[@class='nav-link login register']")
     WebElement enterintoregisterpagebutton;
     


    @BeforeSuite(alwaysRun = true)
    public static void createDriver() throws MalformedURLException {
        // Launch Browser using Zalenium
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
         driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);


      
    }
    

    @Test(dataProvider = "DatasetsforQTrip" ,dataProviderClass= DP.class)
    public void TestCase01(String Username,String Password) throws Exception 
    {
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
       Username = Username + String.valueOf(timestamp.getTime());
       Password = Password + String.valueOf(timestamp.getTime());
     
       System.out.println(Username + Password);
        RegisterPage register = new RegisterPage(driver);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        

        softassert = new SoftAssert();
        driver.manage().window().maximize();
//driver.navigate().to("https://qtripdynamic-qa-frontend.vercel.app/");

        driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/register/");

       // enterintoregisterpagebutton.click();
        


        register.registernewuser(Username,Password,true);
      
      // softassert.assertTrue( register.registernewuser(Username,Password,true),"registration failed");
      softassert.assertAll();
      
      
    }
    

}
