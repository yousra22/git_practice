package pages;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class ContactUS_Form {

	//Driver
		public WebDriver driver ;
		
		//Constructor
		public ContactUS_Form(WebDriver driver)
		{
			this.driver= driver;
		}
		
		//locators
		String name = "your-name";
		String email = "your-email";
		String mobile = "mobile-number";
		String subject = "your-subject";
		String message = "your-message";
		String captcha_id = "recaptcha-accessible-status";
		String send_xpath = "//*[contains(@type,'submit')]";
		
		String email_Validation_Xpath = "//span[text()='The e-mail address entered is invalid.']";
		
		//functions
		@Step("Fill Contact us form")
		public void fill_ContactUS_form(String FirstName, String EmailAdd, String MobNum, String Sub, String Msg) throws InterruptedException
		{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(name))).sendKeys(FirstName);
			
			
			WebElement Email_address = driver.findElement(By.name(email));
			Email_address.sendKeys(EmailAdd);
			
			WebElement Mobile = driver.findElement(By.name(mobile));
			Mobile.sendKeys(MobNum);
			
			WebElement subject_cont = driver.findElement(By.name(subject));
			subject_cont.sendKeys(Sub);
			

			WebElement message_content = driver.findElement(By.name(message));
			message_content.sendKeys(Msg);
			
			WebElement send_button = driver.findElement(By.xpath(send_xpath));
			send_button.click();
		}
		
		@Step("Verify email validation msg is displayed")
		public String assert_EmailValidationMsg_isDisplayed()
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			if( driver.findElement(By.xpath(email_Validation_Xpath)).isDisplayed())
				{
					return driver.findElement(By.xpath(email_Validation_Xpath)).getText();
				}
			else 
				return "empty";
		}
		
		
}
