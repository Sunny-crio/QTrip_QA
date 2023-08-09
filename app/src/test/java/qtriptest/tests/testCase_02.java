package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Timestamp;
import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.concurrent.TimeUnit;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class testCase_02 {

   static RemoteWebDriver driver;

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
      driver = DriverSingleton.getDriverInstance("chrome");
      rs1 = ReportSingleton.getInstanceOfSingleTonReportClass();
      test = rs1.getTest();
      logStatus("driver", "Initializing driver", "Success");
      // create an instance of extent reports
      // report = new ExtentReports("app/src/test/java/qtriptest/reports/qkartTest_Testcase02.html",
      //       true);
    report = rs1.getReport();

      // start a new test
    test = report.startTest("QKart Regression report");
   }



   @Test(dataProvider = "DatasetsforQTrip", dataProviderClass = DP.class, enabled = true,
         description = "verify Search and Filter flow", priority = 2,
         groups = {"Search and Filter flow"})
   public void TestCase02(String CityName, String Category_Filter, String DurationFilter,
         String ExpectedfilterdResult, String ExpectedunfilteredResult) throws Exception {


      HomePage homepage = new HomePage(driver);
      homepage.navigateToHome();

      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

      homepage.search_city(CityName);
      test.log(LogStatus.PASS, "City search successful");

      Thread.sleep(1000);
      // softassert.assertFalse(homepage.noCityFound(),"City found");

      // boolean status= homepage.navigatedto_AdventurePage(CityName);

      // System.out.println(status);

      AdventurePage adventure = new AdventurePage(driver);
      adventure.selectfilters(DurationFilter);

      test.log(LogStatus.PASS, "Duration filter selected ");
      Thread.sleep(2000);

      adventure.isdurationhoursdisplayed(DurationFilter);
      test.log(LogStatus.PASS, "Duration filter displayed ");
      Thread.sleep(2000);


      adventure.categoryfilter(Category_Filter);
      // adventure.iscategorydisplayed(Category_Filter);
      Thread.sleep(2000);

      adventure.filteredresult(ExpectedfilterdResult);
      Thread.sleep(2000);


      adventure.searchadventuresclear();
      Thread.sleep(2000);

      adventure.clearcategoryfilters();
      Thread.sleep(2000);

      adventure.clearhoursfilter();
      Thread.sleep(1000);

      adventure.unfilteredresult(ExpectedunfilteredResult);
      Thread.sleep(2000);

      test.log(LogStatus.PASS, " testcase 2 successfull  ");



   }

   @AfterSuite
   public void tearDown() {
      if (driver != null) {
         System.out.println("End");
         report.endTest(test);
         report.flush();
         // ReportSingleton.getInstanceOfSingleTonReportClass().getReport().flush();
         driver.quit();
      }
   }


}


