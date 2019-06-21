package com.inetbanking.qa.Util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.inetbanking.qa.Base.BaseClass;

public class Reporting extends BaseClass implements ITestListener
{
	public ExtentHtmlReporter htmlreporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext context) {	
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //timestamp
		String repName = "Test-Report-"+timestamp+".html";

		htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);//specifying location
		htmlreporter.config().setDocumentTitle("Automation Report");
		htmlreporter.config().setReportName("Functional Report");
		htmlreporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("Host name", "local host");
		extent.setSystemInfo("User", "Niharika");
		extent.setSystemInfo("Environment", "QA");

	}
	public void onTestStart(ITestResult result) {

	}	

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName()); //create new entry in the report
		test.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {

		test = extent.createTest(result.getName()); //create new entry in the report
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		System.out.println("Failed Test");

		String screenshotpath = takesScreenShot(result.getMethod().getMethodName());

		try {
			test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotpath).build());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName()); //create new entry in the report
		test.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
		System.out.println("Test Case Skipped");
	}
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}
}
