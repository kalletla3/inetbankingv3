package com.inetbanking.qa.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.inetbanking.qa.Base.BaseClass;

public class LoginPage extends BaseClass{
	
	@FindBy(name="uid")
	WebElement userid;
	
	@FindBy(name="password")
	WebElement pwd;
	
	@FindBy(name="btnLogin")
	WebElement logbtn;
	
	@FindBy(xpath="//h2[@class='barone']")
	WebElement logo;
	
	@FindBy(xpath="//li[@class='dropdown']/a[contains(text(),'Selenium')]")
	WebElement selLink;
	
	@FindBy(xpath="//a[contains(text(),'Log out')]")
	WebElement logoutLink;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean IsLogoPresent() {
		return logo.isDisplayed();
	}
	
	public boolean IsSelLinkPresent() {
		return selLink.isEnabled();
	}
	public void clickLogoutLink() {
		logoutLink.click();
	}
	public HomePage Login(String un, String pswd) {
		userid.sendKeys(un);
		pwd.sendKeys(pswd);
		logbtn.click();
		return new HomePage();
	}
	
}
