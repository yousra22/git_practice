package pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;


public class Facebook {

	//Driver
	public WebDriver driver ;
	
	//Constructor
	public Facebook(WebDriver driver)
	{
		this.driver= driver;
	}
	
	//locators
	String muslaPic_CSS = "/circle.mlqo0dh0 georvekb s6kb5r3f/";
	
	//Functions
	@Step("Verify FB page url is correct")
	public String  assert_FBPage_url()
	{
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) 
		{ 
			driver.switchTo().window(window);
		}
		
		return driver.getCurrentUrl();
	}
	
	@Step("Verify Musla profile pic is displayed")
	public boolean assert_MuslaPic_isDisplayed() 
	{
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		 return driver.findElement(By.cssSelector(muslaPic_CSS)).isDisplayed();
	}
}
	