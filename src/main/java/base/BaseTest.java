package base;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.Utils;

public class BaseTest {

	public WebDriver driver;
	public Logger log;
	ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
	public File file;

	@BeforeSuite
	public void setUp() {
		log = LogManager.getLogger(BaseTest.class.getName());
		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		file = new File("./log4j2.xml");
		context.setConfigLocation(file.toURI());
		log.info("Loading Configuration file");
		Utils.loadConfigFile();
	}

	public WebDriver initiateDriver() {
		String browser = Utils.getValueFromConfigFile("browser");
		if (browser.equalsIgnoreCase("CHROME")) {
			log.info("Browser selected : CHROME");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("FIREFOX")) {
			log.info("Browser selected : FIREFOX");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("INTERNET EXPLORER")) {
			log.info("Browser selected : INTERNET EXPLORER");
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		threadLocalDriver.set(driver);
		log.info("Navigating to the website URL present in config file");
		threadLocalDriver.get().get(Utils.getValueFromConfigFile("testURL"));
		log.info("Maximixing the window");
		threadLocalDriver.get().manage().window().maximize();
		return threadLocalDriver.get();
	}

	@AfterMethod
	public void closeDriver() {
		log.info("Closing the webdriver");
		if (threadLocalDriver.get() != null)
			threadLocalDriver.get().close();
	}
}
