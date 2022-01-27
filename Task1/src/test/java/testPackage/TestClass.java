package testPackage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import base.TestBase;
import dataProviders.ExcelReader;
import pages.*;

public class TestClass extends TestBase {
	
	//Page Objects:
	HomePage homePageObj;
	ContactUS_Form contactUs_PageObj;
	Company company_PageObj;
	Facebook facebook_PageObj;
	Careers careers_PageObj;
	JoinUs joinUs_PageObj;
	JobDesc jobDesc_PageObj;
	

	ArrayList<String> job_sections = new ArrayList<>(Arrays.asList("General description", "Requirements", " Responsibilities" ,"What we offer"));  
	
	
	@DataProvider(name="ExcelData")
	public Object[][] userRegisterData() throws IOException
	{
		ExcelReader ER = new ExcelReader();
		return ER.getExcelData();
	}

	
	@Test (dataProvider = "ExcelData" , description = "Invalid Email address format validation msg")            
	public void TestCase1(String name, String mail, String mobile , String subject, String Msg , String errorMsg) throws InterruptedException, Exception {
		
		homePageObj = new HomePage(driver);
		homePageObj.click_ContactUs();
		
		contactUs_PageObj = new ContactUS_Form(driver);
		contactUs_PageObj.fill_ContactUS_form(name,mail,mobile, subject, Msg);
	
		
		String actualMsg = contactUs_PageObj.assert_EmailValidationMsg_isDisplayed();
		  
		assertEquals(actualMsg, errorMsg);
		
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ESCAPE);
		r.keyRelease(KeyEvent.VK_ESCAPE);

		
		
	}     
	
	
	@Test (description = "Check Company page sections & Facebook page")
	public void TestCase2() throws InterruptedException
	{
		homePageObj = new HomePage(driver);
		homePageObj.click_Company();
		
		String actURL = homePageObj.assert_CompanyPage_url();
		assertEquals(actURL, pro.getProperty("company_url"));
		
		
		company_PageObj = new Company(driver);
		String sectionName_displayed = company_PageObj.assert_LeaderShipSection_isDisplayed();
		
		assertEquals(sectionName_displayed, "Leadership");
		
		String parent=driver.getWindowHandle();
		
		company_PageObj.click_FB_icon();
		
		facebook_PageObj = new Facebook(driver);
		String act_fb_URL = facebook_PageObj.assert_FBPage_url();
		assertEquals(act_fb_URL, pro.getProperty("fb_url"));
		
		//driver.close();
		driver.switchTo().window(parent);
		
		//assertTrue(facebook_PageObj.assert_MuslaPic_isDisplayed());
		
	}
	
	
	@Test  (description = "Apply for a job")
	public void TestCase3() throws InterruptedException
	{
		homePageObj = new HomePage(driver);
		homePageObj.click_Careers();
		
		careers_PageObj = new Careers(driver);
		careers_PageObj.click_CheckOpenPositions_btn();
		String act_joinUS_Url = careers_PageObj.assert_JoinUsPage_url();
		assertEquals(act_joinUS_Url, pro.getProperty("joinUs_url"));
		
		joinUs_PageObj = new JoinUs(driver);
		joinUs_PageObj.select_location("Anywhere");
		joinUs_PageObj.select_job();
		
		jobDesc_PageObj = new JobDesc(driver);
		ArrayList<String> act_sections_names = new ArrayList<String>();
		act_sections_names = jobDesc_PageObj.assert_mainPageSections_isDisplayed();
		
		 SoftAssert softAssert = new SoftAssert();
		 softAssert.assertTrue(act_sections_names.equals(job_sections));
	 
		 softAssert.assertTrue(jobDesc_PageObj.assert_applyBtn_isDisplayed());
		 jobDesc_PageObj.click_apply_btn();
		 
		 jobDesc_PageObj.fill_applyForm("test.");
		 assertTrue(jobDesc_PageObj.assert_errorMsgs_isDisplayed());
		 
		 
	}

	@Test   (description = "Get avialable positions for specfic location")
	public void TestCase4() throws InterruptedException
	{
		homePageObj = new HomePage(driver);
		homePageObj.click_Careers();
		
		careers_PageObj = new Careers(driver);
		careers_PageObj.click_CheckOpenPositions_btn();
		
		joinUs_PageObj = new JoinUs(driver);
		joinUs_PageObj.select_location("Sofia");
		joinUs_PageObj.get_AvailableJobsDetails();
		
		System.out.println("-----------------------------------------------");
		
		joinUs_PageObj.select_location("Skopje");
		joinUs_PageObj.get_AvailableJobsDetails();
	}
	
	@AfterClass 
	public void afterTest() {  
		driver.quit();  
	}         

}
