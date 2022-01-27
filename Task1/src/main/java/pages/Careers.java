package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;


public class Careers {

	
			
	//Driver
	public WebDriver driver ;
	
	//Constructor
	public Careers(WebDriver driver)
	{
		this.driver = driver;
	}
		
	//locators
	String CheckOpenPosition_Xpath = "//span[@data-alt = \"Check our open positions\"]";
	
	//Functions
	@Step("Click on Check open positions")
	public void click_CheckOpenPositions_btn()
	{
		driver.findElement(By.xpath(CheckOpenPosition_Xpath)).click();
	}
	
	@Step("Verify Join Us page url is correct")
	public String assert_JoinUsPage_url()
	{
		return driver.getCurrentUrl();
	}

}
