package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_03 {

    static RemoteWebDriver driver;
    public String lastUsername;
    static ExtentReports report;
    static ExtentTest test;
    static ReportSingleton rs1;
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
     // report= new ExtentReports("app/src/test/java/qtriptest/reports/qkartTest_Testcase03.html", true);

      //start a new test
     // test=report.startTest("QKart Regression report"); 

     report = rs1.getReport();

     // start a new test
   test = report.startTest("QKart Regression report");
   }

    
    @Test(dataProvider="DatasetsforQTrip", dataProviderClass=DP.class, enabled=true,description = "verify booking and cancellation flow" , priority = 3, groups={"Booking and Cancellation Flow"})
    public void TestCase03(String NewUserName, String Password, String SearchCity, String AdventureName,String GuestName,String Date, String count) throws Exception
    {
      
       
      

       RegisterPage registerp= new RegisterPage(driver);

       driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/register/");

       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

       Timestamp timestamp = new Timestamp(System.currentTimeMillis());
       NewUserName = NewUserName + String.valueOf(timestamp.getTime());
       Password = Password + String.valueOf(timestamp.getTime());

       registerp.registernewuser(NewUserName, Password, true);




       LoginPage login= new LoginPage(driver);
       
      
        System.out.println(NewUserName + Password);

        driver.manage().window().maximize();

       // LoginPage login= new LoginPage(driver);

        login.performlogin(NewUserName, Password);

        login.veryuserloggedin();

        Thread.sleep(2000);

        HomePage homepage= new HomePage(driver);    
       // homepage.navigateToHome();

       

        homepage.search_city(SearchCity);

        

        Thread.sleep(2000);

        AdventurePage adventure = new AdventurePage(driver);

        adventure.searchadventure_clickadeventure(AdventureName);

        
        AdventureDetailsPage adventuredetailspage=new AdventureDetailsPage(driver);

        adventuredetailspage.adventurebookings(GuestName, Date, count);

        adventuredetailspage.verifyAdventureBooking();

        adventuredetailspage.reservationClick();    
        
        
       test.log(LogStatus.PASS, " testcase 3 successfull  ");

    }

}
