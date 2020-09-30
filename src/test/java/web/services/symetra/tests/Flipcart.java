package web.services.symetra.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Flipcart {
	
	public static By electronics=By.xpath("//span[contains(text(),'Electronics')]");
	public static By laptop = By.xpath("//a[@class='']");
	public static By closePopup = By.className("_29YdH8");
	public static By minValSelect = By.xpath("//div[@class='_1qKb_B']//select[@class='fPjUPw']");
	public static By dellSelect = By.xpath("//div[@class='_1GEhLw'][contains(text(),'Dell')]");
	
	public static 	WebDriver driver;
	
	

	@BeforeSuite
	public void setup() {
		System.setProperty("webdriver.chrome.driver","C:\\SoftWares\\chromedriver_win32\\chromedriver_85.exe");
		driver= new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
		
	}
	@Test
	public void flipcart() throws InterruptedException {
		Select select;
		int count=0;
		long laptopAmt=0;
		String laptopAmtChar="";
	    driver.findElement(closePopup).click();
	    driver.findElement(electronics).click();
	    
	    Actions act = new Actions(driver);
	     
	    act.moveToElement(driver.findElement(By.cssSelector("div:nth-child(1) div.zi6sUf div._3zdbog.CcQxbA div._1OSP27 > span._2FZd5H:nth-child(1)"))).build().perform();;
	    Thread.sleep(5000);
	    driver.findElement(By.linkText("Laptops")).click();
	    
	    select=new Select(driver.findElement(minValSelect));
		select.selectByValue("40000");
		Thread.sleep(5000);
	    
		 driver.findElement(dellSelect).click();
		 Thread.sleep(5000);
		 
		 List<WebElement> list=new ArrayList<WebElement>();
		 list = driver.findElements(By.className("_2rQ-NK"));
		 System.out.println(list);
			int elementSize= list.size();
			
			for(int i=0; i<elementSize;i++) {
				String elementString= list.get(i).getText();
				int stringLength=elementString.length();
				
				String price=elementString.substring(1,stringLength);
				count++;
				String priced=price.replace(",", "");
				System.out.println(priced);
				long amt = Long.parseLong(priced);
				
				if(count==1) {
					laptopAmt=amt;
					laptopAmtChar=price;
				}
				
				if(amt<laptopAmt) {
					laptopAmt=amt;
					laptopAmtChar=price;				
				}
				else {
					
				}		
			}
			
			System.out.println("smallest amount is :"+laptopAmt);
	    
	    Thread.sleep(5000);
	    	       	
	}
	

}
