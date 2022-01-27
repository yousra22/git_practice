package pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
public class Company {

	//Driver
	public WebDriver driver ;
	
	//Constructor
	public Company(WebDriver driver)
	{
		this.driver= driver;
	}
	
	//locators
	String leadershipSection_Xpath = "//div[@class='cm-content']/h2";
	String fb_Xpath = "//a/span[@class='musala musala-icon-facebook']";
	
	//Functions
	@Step("Verify Leadership section is displayed")
	public String assert_LeaderShipSection_isDisplayed ()
	{
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		
		return driver.findElement(By.xpath(leadershipSection_Xpath)).getText();
		 
		
	}
	
	@Step("Click on FB icon")
	public void click_FB_icon()
	{
		driver.findElement(By.xpath(fb_Xpath)).click();
		
	}
	
	
	
}
