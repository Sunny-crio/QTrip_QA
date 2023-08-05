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

public class testCase_02 {

    static RemoteWebDriver driver;
     
    SoftAssert softassert;

   


//     @BeforeTest(alwaysRun=true)
// public static void createDriver() throws MalformedURLException {

//     // final DesiredCapabilities capabilities = new DesiredCapabilities();
//     // capabilities.setBrowserName(BrowserType.CHROME);
//     // driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities); // This line creates a new instance of RemoteWebDriver in each test class
//     driver=DriverSingleton.createDriver();

//    driver.manage().window().maximize();
// }



@Test(dataProvider = "DatasetsforQTrip", dataProviderClass = DP.class, enabled = true,
description = "verify Search and Filter flow", priority = 2,
groups = {"Search and Filter flow"})
   public void TestCase02(String CityName, String Category_Filter, String DurationFilter,
   String ExpectedfilterdResult, String ExpectedunfilteredResult) throws Exception {

    driver=DriverSingleton.createDriver();
                HomePage homepage= new HomePage(driver);
                homepage.navigateToHome();

                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                homepage.search_city(CityName);

                Thread.sleep(1000);
               //softassert.assertFalse(homepage.noCityFound(),"City found");
               
               //boolean status= homepage.navigatedto_AdventurePage(CityName);

            //  System.out.println(status);

               AdventurePage adventure = new AdventurePage(driver);
               adventure.selectfilters(DurationFilter);
               Thread.sleep(2000);

               adventure.isdurationhoursdisplayed(DurationFilter);
               Thread.sleep(2000);

              
               adventure.categoryfilter(Category_Filter);
            //    adventure.iscategorydisplayed(Category_Filter);
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

              


                   
 
}
 @AfterSuite
    
        public void tearDown() {
            if (driver != null) {
                 //ReportSingleton.getInstanceOfSingleTonReportClass().getReport().flush();
                driver.quit();
            }
        }


}


