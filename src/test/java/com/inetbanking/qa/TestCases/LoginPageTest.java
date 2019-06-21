package com.inetbanking.qa.TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.inetbanking.qa.Base.BaseClass;
import com.inetbanking.qa.Pages.HomePage;
import com.inetbanking.qa.Pages.LoginPage;
import com.inetbanking.qa.Util.Reporting;

@Listeners(Reporting.class)
public class LoginPageTest extends BaseClass{

	LoginPage lp;
	HomePage hp;

	public LoginPageTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		logger.info("Initializing the browser");
		lp = new LoginPage();
	}


	@Test(priority=1)
	public void IsLogoPresentTest() {
		lp.IsLogoPresent();
		logger.info("Checking for the presence of Logo");
		Assert.assertTrue(true);
		logger.info("Success");
	}

	@Test(priority=2) 
	public void IsSelLinkPresentTest() {
		lp.IsSelLinkPresent();
		logger.info("Checking for the presence of Selenium Link");
		Assert.assertTrue(true);
		logger.info("The link is present");
	}

	@Test(priority=3)
	public void LoginTest() throws InterruptedException {

		hp = lp.Login(prop.getProperty("userID"), prop.getProperty("password"));
		//System.out.println("Print username and password: "+prop.getProperty("userID")+" and "+prop.getProperty("password"));
		logger.info("Keying username, Password and checking for login functionality");
		logger.info("Login Success!!");
		Assert.assertEquals(driver.getTitle(), "Guru99 Bank Manager HomePage");
		logger.warn("Invalid assertion, Title dosen't match!!!");

		if(handleAlerts()==true)
		{
			driver.switchTo().alert().accept();//close the alert
			logger.info("Invalid credentials and swiching to default page");
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
		}
		else {
			Assert.assertTrue(true);
			logger.info("Valid credentials");
			lp.clickLogoutLink();
			logger.info("Clicking on logout link");
			driver.switchTo().alert().accept();//close logout alert
			driver.switchTo().defaultContent();
		}
	}

	@AfterClass
	public void tearDown() {
		finish();
		logger.info("Quitting the browser");
		logger.info("Test Complete!");
	}
}
