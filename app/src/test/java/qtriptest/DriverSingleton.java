package qtriptest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;

public class DriverSingleton {

private static RemoteWebDriver driver = null;


    
@BeforeTest(alwaysRun=true)
    public static RemoteWebDriver getDriverInstance(String browserType) throws MalformedURLException{
       if(driver==null){
        switch(browserType){
            case "chrome":
            final DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(BrowserType.CHROME);
            driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
            driver.manage().window().maximize();
            break;
          }
    
        }
        return driver;
      }
    }