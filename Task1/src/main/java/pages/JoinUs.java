package pages;



import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import io.qameta.allure.Step;


public class JoinUs {

	//Driver
	public WebDriver driver ;
	
	//Constructor
	public JoinUs(WebDriver driver)
	{
		this.driver= driver;
	}
	
	//locators
	String locations_Id = "get_location";
	String jobsAvailableList_Xpath = "//article[@class = \"card-jobsHot\"]";	
	String jobsListNames_Xpath = "//h2[@class = \"card-jobsHot__title\"]";
	String jobsAvaialabeListLinks_Xpath = "//div[@class = \"card-container\"]//a";
	
	
	//Functions
	@Step("Select Location")
	public void select_location(String location)
	{
		Select drpLocations = new Select (driver.findElement(By.id(locations_Id)));
		drpLocations.selectByVisibleText(location);
		
		System.out.println(location);
		System.out.printf("%n");
	}
	
	@Step("Select Job")
	public void select_job()
	{
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
		List<WebElement> jobs = driver.findElements(By.xpath(jobsAvailableList_Xpath));
		jobs.get(0).click();
	}
	
	@Step("Get avilable Jobs details")
	public void get_AvailableJobsDetails()
	{
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
		List<WebElement> jobs_names = driver.findElements(By.xpath(jobsListNames_Xpath));
		
	
		List<WebElement> jobs_links = driver.findElements(By.xpath(jobsAvaialabeListLinks_Xpath));
		
		  for (int i=0; i<jobs_names.size();i++){
		      System.out.println("Position:" + jobs_names.get(i).getText());
		      System.out.println("More info:"+ jobs_links.get(i).getAttribute("href"));
		    }
	}
}
