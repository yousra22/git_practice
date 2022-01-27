package base;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	protected  static WebDriver driver;
	protected  Properties pro;
	
	@BeforeTest
	public void readConfig() throws Exception
	{
		ProjectConfiguration pConf = new ProjectConfiguration();
		pro = pConf.loadProperites();
	}
	
	
	@BeforeClass
	public void instantiateDriver() throws Exception{
		System.out.println("First line of the instantiate method");
		 
		String browser = pro.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")){
		//	WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", pro.getProperty("driverPath"));  
			driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("firefox")){	
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
 
		}
		driver.manage().window().maximize();
		driver.get( pro.getProperty("url"));
		System.out.println("LastLine of the instatiate method of TestBase");
	}
	
	
    public static WebDriver getDriverInstance()
    {
    	
		return driver;
    	
    }
}
