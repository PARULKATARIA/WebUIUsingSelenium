package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.Utils;
import webpages.CartPage;
import webpages.HomePage;

public class BasePage extends BaseTest {

	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	private By cartLink = By.id("cartur");
	private By productStoreLink = By.id("nava");

	public CartPage clickOnCartLink() {
		Utils.click(driver, cartLink);
		return new CartPage(driver);
	}

	public HomePage clickOnProductsStoreLink() {
		Utils.click(driver, productStoreLink);
		return new HomePage(driver);
	}
}
