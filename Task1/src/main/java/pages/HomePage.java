package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class HomePage {
	
	//Driver
	public WebDriver driver ;
	
	//Constructor
	public HomePage(WebDriver driver)
	{
		this.driver= driver;
	}
	
	//locators
	String contactUs_classname = "fancybox";
	String company_linktext = "COMPANY";
	String careers_linktext = "CAREERS";
	

	
	//functions

	@SuppressWarnings("deprecation")
	@Step("Click on ContactUs")
	public void click_ContactUs()
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(contactUs_classname))).click();
		
		
		//driver.findElement(By.className(contact_us)).click();
	}

	@Step("Click on Company")
	public void click_Company()
	{
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(company_linktext))).click();
		
	}
	
	@Step("Verify Company Page url is correct")
	public String assert_CompanyPage_url()
	{
		return driver.getCurrentUrl();
	}
	
	@Step("Click on Careers")
	public void click_Careers()
	{
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(careers_linktext))).click();
		
	}
}
