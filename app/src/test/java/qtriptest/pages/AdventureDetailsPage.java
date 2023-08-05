package qtriptest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AdventureDetailsPage {

    RemoteWebDriver driver;

    @FindBy(xpath="//input[@name='name']")
    WebElement name_textbox;

    @FindBy(xpath="*//input[@type='date']")
    WebElement Pick_date;

    @FindBy(xpath="*//input[@type='number']")
    WebElement PersonCount;

    @FindBy(xpath="*//button[@class='reserve-button']")
    WebElement Reservebutton;

    @FindBy(xpath="//div[contains(text(),'Greetings! Reservation for this adventure is successful')]")
    WebElement Reservation_sucess_msg;

    @FindBy(xpath = "//a[text()='Reservations']")
    WebElement reservation_icon;

   public AdventureDetailsPage(RemoteWebDriver driver)
    {
     this.driver=driver;
     PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void adventurebookings(String personname,String Pickdate, String Personcount) throws Exception{


        Thread.sleep(3000);

        name_textbox.sendKeys(personname);

        Pick_date.sendKeys(Pickdate);

        PersonCount.sendKeys(Personcount);

        Reservebutton.click();

      
    }

    public boolean verifyAdventureBooking(){
    
        if(Reservation_sucess_msg.getText().contains("Greetings! Reservation for this adventure is successful")){
          return true;
        }else{
          return false;
        }
    }
    public void reservationClick() throws InterruptedException{
        Thread.sleep(2000);
        reservation_icon.click();
     
      }

}