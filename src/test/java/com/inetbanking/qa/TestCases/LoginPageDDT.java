package com.inetbanking.qa.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.inetbanking.qa.Base.BaseClass;
import com.inetbanking.qa.Pages.HomePage;
import com.inetbanking.qa.Pages.LoginPage;
import com.inetbanking.qa.Util.Reporting;
import com.inetbanking.qa.Util.XLUtils;

@Listeners(Reporting.class)
public class LoginPageDDT extends BaseClass{
	LoginPage lp;
	HomePage hp;

	@BeforeClass
	public void setUp() {
		initialization();
		lp= new LoginPage();
		hp = new HomePage();
	}

	public LoginPageDDT() {
		super();
	}

	@Test(dataProvider = "LoginData")
	public void LoginDDT(String user, String pwd) {
		hp = lp.Login(user,pwd);
		if(handleAlerts()==true) {
			driver.switchTo().alert().accept();
			Assert.assertTrue(false);
		}
		else {
			Assert.assertTrue(true);
			lp.clickLogoutLink();
			Assert.assertTrue(true);
		}
	}

	@DataProvider(name="LoginData")
	String[][] getData() throws IOException
	{
		String path = System.getProperty("user.dir")+"/src/main/java/com/inetbanking/qa/TestData/Book.xlsx";

		int rowCount = XLUtils.getRowCount(path, "Sheet1");
		int colCount = XLUtils.getCellCount(path, "Sheet1", 1);

		String loginData[][] = new String[rowCount][colCount];

		for(int i=1;i<=rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				loginData[i-1][j]=XLUtils.getCellData(path,"Sheet1",i,j); // 1 0
			}
		}
		return loginData;
	}

	@AfterClass
	public void tearDown() {
		finish();
	}
}
