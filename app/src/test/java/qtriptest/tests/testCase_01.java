package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
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

  static ExtentReports report;
  static ExtentTest test;
  static ReportSingleton rs1;

  RegisterPage registerp;

  LoginPage login;
 // Method to help us log our Unit Tests
 public static void logStatus(String type, String message, String status) {
     System.out.println(String.format("%s |  %s  |  %s | %s",
             String.valueOf(java.time.LocalDateTime.now()), type, message, status));
 }

 // Iinitialize webdriver for our Unit Tests
 @BeforeSuite(alwaysRun = true, enabled = true)
 public static void createDriver() throws MalformedURLException {
     logStatus("driver", "Initializing driver", "Started");
     driver=DriverSingleton.getDriverInstance("chrome");
     rs1 = ReportSingleton.getInstanceOfSingleTonReportClass();
     test = rs1.getTest();
     logStatus("driver", "Initializing driver", "Success");

     //create an instance of extent reports
    //  report= new ExtentReports("app/src/test/java/qtriptest/Reports/qkartTest_Testcase01.html", true);
    report = rs1.getReport();
 

     //start a new test
     test=report.startTest("QKart Regression report"); 
     driver.manage().window().maximize();   
 }
     
   

    

@Test(dataProvider = "DatasetsforQTrip", dataProviderClass =DP.class, enabled = true, description = "verify Login flow" , priority = 1, groups={"Login Flow"})
    public void TestCase01(String Username,String Password) throws Exception 
    {
    
    
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
       Username = Username + String.valueOf(timestamp.getTime());
       Password = Password + String.valueOf(timestamp.getTime());
     
       System.out.println(Username + Password);

      
      RegisterPage registerp= new RegisterPage(driver);

      registerp.navigatetoRegisterpage();

        

      SoftAssert softassert = new SoftAssert();
        driver.manage().window().maximize();
//driver.navigate().to("https://qtripdynamic-qa-frontend.vercel.app/");

//driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/register/");

       // enterintoregisterpagebutton.click();
        
 registerp.registernewuser(Username,Password,true);
 test.log(LogStatus.PASS, "Registration successful");
      
      //softassert.assertTrue( register.registernewuser(Username,Password,true),"registration failed");

      LoginPage login= new LoginPage(driver);

       login.performlogin(Username,Password);
      
login.veryuserloggedin();
test.log(LogStatus.PASS, "Login successful");



test.log(LogStatus.PASS, " testcase 1 successfull  ");
 softassert.assertAll();
      
      
    }
    

}
