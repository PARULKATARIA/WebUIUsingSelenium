package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Utils {

	public static Properties property;
	public static FileInputStream fis;

	public static void loadConfigFile() {
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/config/config.properties");
			property = new Properties();
			property.load(fis);
		} catch (Exception e) {
			throw new TestException(
					String.format("Either file is not available on this path or loading issue occured :  [%s]",
							"/src/main/java/config/config.properties"));
		}
	}

	public static String getValueFromConfigFile(String key) {
		try {
			return property.get(key).toString();
		} catch (Exception ex) {
			throw new TestException("There is no such property in the config file");
		}
	}

	public String getScreenshotPath(WebDriver driver, String testCaseName) throws IOException {
		String path = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(path));
		return path;
	}

	public ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "/reports/extentreport.html";
		ExtentReports report = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("WebFEAutomation Test Results");
		reporter.config().setReportName("Parul Kataria");
		reporter.config().setDocumentTitle("Project : https://www.demoblaze.com");
		reporter.config().setTheme(Theme.STANDARD);
		report.attachReporter(reporter);
		return report;
	}

	public static void waitCondition(WebDriver driver, ExpectedCondition<?> condition) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(condition);
	}

	public static void acceptPopup(WebDriver driver) {
		try {
			Utils.waitCondition(driver, ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			throw new TestException(String.format("Error occured while clicking OK on Pop up"));
		}
	}

	public static WebElement getElement(WebDriver driver, By selector) {
		try {
			Utils.waitCondition(driver, ExpectedConditions.elementToBeClickable(selector));
			return driver.findElement(selector);
		} catch (Exception e) {
			throw new TestException(String.format("This element is not present: [%s]", selector));
		}
	}

	public static void applyWait(long timeInMS) {
		try {
			Thread.sleep(timeInMS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static boolean elementIsDisplayed(WebDriver driver, By selector) {
		try {
			Utils.waitCondition(driver, ExpectedConditions.elementToBeClickable(selector));
			return driver.findElement(selector).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static void sendKeys(WebDriver driver, By selector, String value) {
		WebElement element = getElement(driver, selector);
		try {
			element.sendKeys(value);
		} catch (Exception e) {
			throw new TestException(
					String.format("Error while inserting [%s] in following element: [%s]", value, selector.toString()));
		}
	}

	public static void click(WebDriver driver, By selector) {
		WebElement element = getElement(driver, selector);
		try {
			element.click();
		} catch (Exception e) {
			throw new TestException(String.format("This element is not clickable: [%s]", selector));
		}
	}
}
