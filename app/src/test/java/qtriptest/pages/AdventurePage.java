package qtriptest.pages;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage {
    RemoteWebDriver driver;

    @FindBy(xpath="*//select[@id='duration-select']")
    WebElement DurationFilter_hours;

    @FindBy(xpath="(*//div[contains(text(),'Clear')])[1]")
    WebElement DurationFilter_Clear;

    @FindBy(xpath="*//select[@id='category-select']")
    WebElement CategoryFilter;

    @FindBy(xpath="(*//div[contains(text(),'Clear')])[2]")
    WebElement CategoryFilter_Clear;

    @FindBy(xpath="*//input[@id = 'search-adventures']")
    WebElement SearchAdventures;

    @FindBy(xpath="*//div[@id='data']")
   List<WebElement> adventurecontents;

   @FindBy(xpath="(*//div[contains(text(),'Clear')])[3]")
   WebElement searchadventuresclear;
    
   @FindBy(xpath="*//img[@class='img-responsive']")
   WebElement adventureimgclick;

    public AdventurePage(RemoteWebDriver driver)
    {
        this.driver = driver;

        // AjaxElementLocatorFactory Ajax = new AjaxElementLocatorFactory(driver, 20);
        // PageFactory.initElements(Ajax, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
        

    }

   public void selectfilters(String Durationfilter) throws Exception
   {
    try{

   
    Select filters = new Select(DurationFilter_hours);
    filters.selectByVisibleText(Durationfilter);

    Thread.sleep(2000);
  //  DurationFilter_hours.click();

    System.out.println(DurationFilter_hours.getText());

    }
    catch(Exception e){
        System.out.println(e);
    }

   }
  public void clearhoursfilter()
  {
    DurationFilter_Clear.click();
  }

public boolean isdurationhoursdisplayed(String Durationfilter)
{
    boolean isdurationhoursdisplayed= false;
    try{
         String text=DurationFilter_hours.getText();
         isdurationhoursdisplayed = text.equals(Durationfilter);
    }
    catch(Exception e)
    {
        System.out.println(e);
        return !isdurationhoursdisplayed;
    }
    return isdurationhoursdisplayed;
}
 public void categoryfilter(String Categoryfiltercheck)
 {

    try{
        Select cfilters= new Select(CategoryFilter);
        cfilters.selectByVisibleText(Categoryfiltercheck);

        Thread.sleep(1000);
        System.out.println(CategoryFilter.getText());

    }
    catch(Exception e)
    {
        e.printStackTrace();
        System.out.println(e);
    }
 }
 public void clearcategoryfilters()
 {
    CategoryFilter_Clear.click();
 }
  
 public boolean iscategorydisplayed(String Categoryfiltercheck)
 {
    boolean iscategorydisplayed= false;
    try
    {
        String categorytext= CategoryFilter.getText();
        iscategorydisplayed= categorytext.equals(Categoryfiltercheck);
    }
    catch(Exception e)
    {
        System.out.println(e);
        return !iscategorydisplayed;

    }
    return iscategorydisplayed;
 }


public boolean filteredresult(String filteredResult)
{
    Integer expectedresult = adventurecontents.size();
    String expresult=expectedresult.toString();
        if(expresult.equals(filteredResult)){
           System.out.println( "filteredResults "+filteredResult);
           return true;

        }
        else return false;
 }



public boolean unfilteredresult(String unfilteredresult)
{
    Integer expectedresult = adventurecontents.size();
    String expresult=expectedresult.toString();
    
        if(expresult.equals(unfilteredresult)){
           System.out.println( "unfilteredResults "+unfilteredresult);
           return true;

        }
        else return false;
 }

 public void searchadventuresclear()
 {
    searchadventuresclear.click();
 }

 public void searchadventure_clickadeventure(String adventurename) throws Exception
 {

    SearchAdventures.sendKeys(adventurename);
    Thread.sleep(2000);

    adventureimgclick.click();

    if(driver.getCurrentUrl().contains("https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/detail/?adventure="))
    {
        System.out.println(true);
    }    

 }

}
