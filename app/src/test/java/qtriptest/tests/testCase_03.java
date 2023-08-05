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
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_03 {

    static RemoteWebDriver driver;

    // @BeforeTest(alwaysRun=true)
    // public static void createDriver() throws MalformedURLException {
    
    //     // final DesiredCapabilities capabilities = new DesiredCapabilities();
    //     // capabilities.setBrowserName(BrowserType.CHROME);
    //     // driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities); // This line creates a new instance of RemoteWebDriver in each test class
    
    //     driver=DriverSingleton.createDriver();
    
    //     driver.manage().window().maximize();
    // }
    

    
    @Test(dataProvider="DatasetsforQTrip", dataProviderClass=DP.class, enabled=true,description = "verify booking and cancellation flow" , priority = 3, groups={"Booking and Cancellation Flow"})
    public void TestCase03(String NewUserName, String Password, String SearchCity, String AdventureName,String GuestName,String Date, String count) throws Exception
    {
      
       
       driver=DriverSingleton.createDriver();

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

        Thread.sleep(1000);

        AdventurePage adventure = new AdventurePage(driver);

        adventure.searchadventure_clickadeventure(AdventureName);

        
        AdventureDetailsPage adventuredetailspage=new AdventureDetailsPage(driver);

        adventuredetailspage.adventurebookings(GuestName, Date, count);

        adventuredetailspage.verifyAdventureBooking();

        adventuredetailspage.reservationClick();        

    }

}
