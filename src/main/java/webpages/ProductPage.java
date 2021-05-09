package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;
import utilities.Utils;

public class ProductPage extends BasePage {

	WebDriver driver;

	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private By addToCartButton = By.linkText("Add to cart");
	private By productName = By.cssSelector("h2.name");
	private By productPrice = By.cssSelector("h3.price-container");

	public void acceptPopUpConfirmation() {
		Utils.acceptPopup(driver);
	}

	public void clickOnAddToCartButton() {
		Utils.click(driver, addToCartButton);
	}

	public String getProductName() {
		return Utils.getElement(driver, productName).getText();
	}

	public String getProductPrice() {
		return Utils.getElement(driver, productPrice).getText().replace("$", "").replace("*includes tax", "").trim();
	}
}
