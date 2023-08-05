package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_04 {

    static RemoteWebDriver driver;

    @BeforeTest(alwaysRun=true)
    public static void createDriver() throws MalformedURLException {
    
        // final DesiredCapabilities capabilities = new DesiredCapabilities();
        // capabilities.setBrowserName(BrowserType.CHROME);
        // driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities); // This line creates a new instance of RemoteWebDriver in each test class
    
        driver=DriverSingleton.createDriver();
    
        driver.manage().window().maximize();
    }

    @Test(dataProvider="DatasetsforQTrip", dataProviderClass=DP.class, enabled=true, description = "verify Relaibility flow" , priority = 4, groups={"Reliability Flow"})
    public void TestCase04(String NewUserName, String Password,String dataset1,String dataset2,String dataset3) throws Exception
    {
      driver=DriverSingleton.createDriver();
    
      driver.manage().window().maximize();
       driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        
        HomePage homepage= new HomePage(driver);
              homepage.navigateToHome();


       
                RegisterPage registerp= new RegisterPage(driver);

                registerp.navigatetoRegisterpage();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                NewUserName = NewUserName + String.valueOf(timestamp.getTime());
                Password = Password + String.valueOf(timestamp.getTime());
              
                registerp.registernewuser(NewUserName, Password, true);

                LoginPage login= new LoginPage(driver);
    
                System.out.println(NewUserName + Password);
        
                login.performlogin(NewUserName, Password);
        
                login.veryuserloggedin();
        
                Thread.sleep(2000);
                
         String[] DataSet1 = dataset1.split(";");
         String[] DataSet2 = dataset2.split(";");
         String[] DataSet3 =dataset3.split(";");

         //First set of data
        
         homepage.navigateToHome();

         homepage.search_city(DataSet1[0]);
         Thread.sleep(1000);
         homepage.navigatedto_AdventurePage(DataSet1[0]);

         
       AdventurePage adventure = new AdventurePage(driver);

       adventure.searchadventure_clickadeventure(DataSet1[1]);

       AdventureDetailsPage adventuredetailspage=new AdventureDetailsPage(driver);

       adventuredetailspage.adventurebookings(DataSet1[2], DataSet1[3], DataSet1[4]);

       adventuredetailspage.verifyAdventureBooking();

       adventuredetailspage.reservationClick();

      //second set of data
       homepage.navigateToHome();  

       homepage.search_city(DataSet2[0]);
       Thread.sleep(1000);
       homepage.navigatedto_AdventurePage(DataSet2[0]);
       Thread.sleep(1000);

     adventure.searchadventure_clickadeventure(DataSet2[1]);

     adventuredetailspage.adventurebookings(DataSet2[2], DataSet2[3], DataSet2[4]);

     adventuredetailspage.verifyAdventureBooking();

     adventuredetailspage.reservationClick();
     Thread.sleep(2000);

     //third set of data
     homepage.navigateToHome();  

     homepage.search_city(DataSet3[0]);
     Thread.sleep(1000);
     homepage.navigatedto_AdventurePage(DataSet3[0]);
     Thread.sleep(1000);

   adventure.searchadventure_clickadeventure(DataSet3[1]);

   adventuredetailspage.adventurebookings(DataSet3[2], DataSet3[3], DataSet3[4]);

   adventuredetailspage.verifyAdventureBooking();

   adventuredetailspage.reservationClick();
   Thread.sleep(2000);

 HistoryPage historypage= new HistoryPage(driver);
 historypage.getTransactionId();


    }  

}
