package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;

public class JobDesc {

	
	//Driver
	public WebDriver driver ;
	
	//Constructor
	public JobDesc(WebDriver driver)
	{
		this.driver = driver;
	}
	
	//Locators
	String mainSections_Xpath = "//div[@class='content-title']/h2";
	String apply_Xpath = "//input[@type='button']";
	String captcha_Id = "recaptcha-anchor";
	
	//Apply Form locators
	String name ="your-email";
	String upload = "uploadtextfield";
	String send_Xpath = "//input[@type='submit']";
	String close_Classname = "close-form";
	String error_msgs_Classname ="wpcf7-not-valid-tip";
	
	
	//Functions
	@Step("Verify Job description page sections are displayed")
	public ArrayList<String> assert_mainPageSections_isDisplayed()
	{
		ArrayList<String> sections_names = new ArrayList<String>();
		
		 
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		List<WebElement> sections = driver.findElements(By.xpath(mainSections_Xpath));
		
		for (WebElement section : sections) {
			sections_names.add(section.getText());
			
		}
		
		return sections_names;
	}
	
	@Step("Verify Apply button is displayed")
	public boolean assert_applyBtn_isDisplayed()
	{
		return driver.findElement(By.xpath(apply_Xpath)).isDisplayed();
	}
	
	@Step("Click apply")
	public void click_apply_btn()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath(apply_Xpath)));
		
	}
	
	@Step("Fill apply form fields")
	public void fill_applyForm(String email)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.name(name)).sendKeys(email);
		
		driver.findElement(By.name(upload)).sendKeys("D:\\TechExam\\src\\test\\resources\\Test doc.docx");
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath(send_Xpath)));
        
        js.executeScript("arguments[0].click();", driver.findElement(By.className(close_Classname)));
		
	}
	
	@Step("Verify validation error msgs are displayed")
	public boolean assert_errorMsgs_isDisplayed()
	{
		return driver.findElement(By.className(error_msgs_Classname)).isDisplayed();
	}
	
	

}
