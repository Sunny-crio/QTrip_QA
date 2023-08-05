package qtriptest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";

    @FindBy(xpath="*//input[@placeholder='Search a City ']")
    WebElement searchbox;

    @FindBy(xpath="*//ul[@id='results']//h5")
    WebElement no_city_found;

    @FindBy(xpath="*//ul[@id='results']/a/li")
    WebElement autocompletesearchcity; //searching the city and clicking on the autocomplete city

    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);

        
    }

    public void navigateToHome() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    // public void nocityfound(String cityname){

    //     searchbox.sendKeys(cityname);

    //     if(no_city_found.isDisplayed())
    //     {
    //         System.out.println("no city found");
    //     }
    
    // }
    public Boolean noCityFound() {
        Boolean status = false;
        try {
            // Check the presence of "No city found" text in the web page. Assign status
            // = true if the element is *displayed* else set status = false
            status = no_city_found.isDisplayed();
            return status;
        } catch (Exception e) {
            return status;
        }
    }
    public void search_city(String cityname) throws InterruptedException{

    
      
        Thread.sleep(2000);
        System.out.println("name of the city " + cityname);
        searchbox.sendKeys(cityname);
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(autocompletesearchcity));
        autocompletesearchcity.click();


    }
    
    public boolean navigatedto_AdventurePage(String cityname){

        boolean navigatedtoexpectedcitypage=false;
 
       try{

       
        if(driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/?city="+cityname));
        {
            navigatedtoexpectedcitypage=true;
        }
    }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
        return navigatedtoexpectedcitypage;
    }
}


       


    


    
    
