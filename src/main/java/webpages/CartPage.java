package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;
import utilities.Utils;

public class CartPage extends BasePage {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private By placeOrderButton = By.xpath("//button[text()='Place Order']");
	private By priceForFirstItemInCart = By.cssSelector("tbody tr td:nth-of-type(3)");

	private By nameInput = By.id("name");
	private By countryInput = By.id("country");
	private By cityInput = By.id("city");
	private By creditCardInput = By.id("card");
	private By monthInput = By.id("month");
	private By yearInput = By.id("year");

	private By purchaseButton = By.xpath("//button[text()='Purchase']");

	private By orderInformation = By.xpath("//div[contains(@class,'sweet')]/p");

	private By okButton = By.cssSelector("button.confirm");

	public void clickOnPurchaseButton() {
		Utils.click(driver, purchaseButton);
	}

	public String getPriceOnCartPage() {
		return Utils.getElement(driver, priceForFirstItemInCart).getText();
	}

	public void clickOnPlaceOrderButton() {
		Utils.click(driver, placeOrderButton);
	}

	public void deleteProductUsingName(String productName) {
		Utils.click(driver, By.xpath("//td[text()='" + productName + "']/following-sibling::td/a[text()='Delete']"));
		Utils.applyWait(3000);
	}

	public boolean selectedProductIsDisplayed(String productName) {
		return Utils.elementIsDisplayed(driver,
				By.xpath("//td[text()='" + productName + "']/following-sibling::td/a[text()='Delete']"));
	}

	public void fillFormDetails(String name, String country, String city, String creditCard, String month,
			String year) {
		Utils.sendKeys(driver, nameInput, name);
		Utils.sendKeys(driver, countryInput, country);
		Utils.sendKeys(driver, cityInput, city);
		Utils.sendKeys(driver, creditCardInput, creditCard);
		Utils.sendKeys(driver, monthInput, month);
		Utils.sendKeys(driver, yearInput, year);
	}

	public String[] captureOrderInformation() {
		return (Utils.getElement(driver, orderInformation).getAttribute("innerHTML")).split("<br>");
	}

	public void clickOnOkButton() {
		Utils.click(driver, okButton);
	}

}
