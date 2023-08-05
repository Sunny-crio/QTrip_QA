package qtriptest.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HistoryPage {

     RemoteWebDriver driver;

     String url= "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/index.html";

  
     @FindBy(xpath ="//tbody[@id='reservation-table']/tr/th")
     List<WebElement> transactionId;
 
     @FindBy(xpath = "//button[text()='Cancel']")
     WebElement cancel_button;
 
     @FindBy(xpath="//div[contains(text(),'Oops! You have not made any reservations yet!')]")
     WebElement verify_cancelmsg;
 
     @FindBy(xpath = "//div[text()='Logout']")
     WebElement logout;

    public HistoryPage(RemoteWebDriver driver){

        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);

    }

    public void navigateToHistoryPage() throws InterruptedException{
        if(!driver.getCurrentUrl().equals(url)){
          driver.get(url);
      }

    }
      public void getTransactionId(){

        for(int i=0;i<transactionId.size();i++)
        {
             System.out.println("TransactionID ="+ transactionId.get(i).getText());
        }

       

    }
    public void cancelReservation() throws InterruptedException{
        Thread.sleep(2000);
        cancel_button.click();
        Thread.sleep(2000);
        driver.navigate().refresh();
    }

    public boolean verifyCancelReservation(){

        if(verify_cancelmsg.getText().contains("Oops! You have not made any reservations yet!")){
            return true;
          }else{
            return false;
          }
        }
    
        public void logout() throws InterruptedException{
            Thread.sleep(2000);
            logout.click();
        }


    
}