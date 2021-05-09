package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestNGException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.Utils;

public class ListenerImplementation implements ITestListener {

	Utils util;
	ExtentTest test;
	ExtentReports report;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, result.getMethod().getMethodName() + ": This test case has passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver;
		try {
			extentTest.get().log(Status.FAIL, result.getMethod().getMethodName() + ": This test case has failed");
			extentTest.get().fail(result.getThrowable());
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
			String methodName = result.getMethod().getMethodName();
			String path = util.getScreenshotPath(driver, methodName);
			extentTest.get().addScreenCaptureFromPath(path, methodName);
		} catch (Exception e) {
			throw new TestNGException("Error occured while attaching the screenshot");
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, result.getMethod().getMethodName() + ": This test case has been skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
		util = new Utils();
		report = util.getReportObject();
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

}
