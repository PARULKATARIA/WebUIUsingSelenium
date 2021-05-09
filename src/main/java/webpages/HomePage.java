package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;
import utilities.Utils;

public class HomePage extends BasePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private By phonesLink = By.linkText("Phones");
	private By laptopsLink = By.linkText("Laptops");
	private By monitorsLink = By.linkText("Monitors");

	private By productDescription = By.className("card-block");

	public ProductPage selectLaptopByName(String laptopName) {
		this.clickOnLaptopsLink();
		Utils.click(driver, By.linkText(laptopName));
		return new ProductPage(driver);
	}

	public String productDescription() {
		return Utils.getElement(driver, productDescription).getText();
	}

	public void clickOnPhonesLink() {
		Utils.click(driver, phonesLink);
		Utils.applyWait(1000);
	}

	public void clickOnLaptopsLink() {
		Utils.click(driver, laptopsLink);
		Utils.applyWait(1000);
	}

	public void clickOnMonitorsLink() {
		Utils.click(driver, monitorsLink);
		Utils.applyWait(1000);
	}
}
