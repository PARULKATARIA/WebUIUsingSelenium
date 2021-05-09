package tests;

import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import base.BaseTest;
import webpages.CartPage;
import webpages.HomePage;
import webpages.ProductPage;

public class OrderTest extends BaseTest {

	public WebDriver driver;
	HomePage homePage;
	ProductPage productPage;
	CartPage cartPage;
	Logger log;

	@Test
	public void verifyUserCanPlaceAnOrder() {
		log = LogManager.getLogger(OrderTest.class.getName());
		driver = initiateDriver();
		homePage = new HomePage(driver);

		System.out.println("Step 1 : Customer navigation through product categories: Phones, Laptops and Monitors");
		log.info("Clicking on the Phones link");
		homePage.clickOnPhonesLink();
		log.info("Verifying that products displayed are phones");
		this.verifyProductCategoryNavigation("phone");
		log.info("Clicking on the Monitors link");
		homePage.clickOnMonitorsLink();
		log.info("Verifying that products displayed are monitors");
		this.verifyProductCategoryNavigation("monitor");
		log.info("Clicking on the Laptops link");
		homePage.clickOnLaptopsLink();
		log.info("Verifying that products displayed are laptops");
		this.verifyProductCategoryNavigation("laptop");

		System.out.println(
				"Step 2 : Navigate to Laptop → Sony vaio i5 and click on Add to cart.Accept pop up confirmation.");
		String sonyLaptop = "Sony vaio i5";
		log.info("Select sony laptop and click on OK button after adding it to cart");
		this.selectingLaptopAndSuccessfullyAddingItToCart(sonyLaptop);
		log.debug("Fetch the price for sony laptop displayed on Product Page");
		String priceOnProductPage = productPage.getProductPrice();

		System.out.println(
				"Step 3 : Navigate to Laptop → Dell i7 8gb and click on Add to cart.Accept pop up confirmation.");
		String dellLaptop = "Dell i7 8gb";
		log.info("Click on Products Store link");
		homePage.clickOnProductsStoreLink();
		log.info("Select dell laptop and click on OK button after adding it to cart");
		this.selectingLaptopAndSuccessfullyAddingItToCart(dellLaptop);

		System.out.println("Step 4 : Navigate to Cart → Delete Dell i7 8gb from cart.");
		log.info("Click on Cart link");
		cartPage = productPage.clickOnCartLink();
		log.debug("Verify that both the selected laptops are being displayed on the cart page");
		Assert.assertTrue(cartPage.selectedProductIsDisplayed(sonyLaptop),
				String.format("[%s] is not visible in the cart", sonyLaptop));
		Assert.assertTrue(cartPage.selectedProductIsDisplayed(sonyLaptop),
				String.format("[%s] is not visible in the cart", dellLaptop));
		log.info("Delete dell laptop from the cart");
		cartPage.deleteProductUsingName(dellLaptop);
		log.debug("verify that dell laptop is no longer displayed on the cart page after deletion");
		Assert.assertFalse(cartPage.selectedProductIsDisplayed(dellLaptop),
				String.format("[%s] is not deleted from the cart", dellLaptop));
		String priceOnCartPage = cartPage.getPriceOnCartPage();
		log.debug("verify that price displayed on product page and cart page is equal");
		Assert.assertEquals(priceOnCartPage, priceOnProductPage,
				"Price displayed on Product Page is not equal to price displayed on Cart Page");

		System.out.println("Step 5 : Click on Place order");
		log.info("Clicking on Place Order button");
		cartPage.clickOnPlaceOrderButton();

		// fill in all the information required on the webform
		String name = "Parul Kataria", country = "India", city = "Gurgaon", creditCardNumber = "12345678",
				month = "April", year = "2021";
		System.out.println("Step 6 : Fill in all web form fields.");
		log.info("Filling all form details");
		cartPage.fillFormDetails(name, country, city, creditCardNumber, month, year);

		// Click on the purchase button provided on the form
		System.out.println("Step 7 : Click on Purchase");
		log.info("Clicking on Purchase button");
		cartPage.clickOnPurchaseButton();

		System.out.println("Step 8 : Capture and log purchase Id and Amount.");
		log.debug("Fetching the successful order information");
		String[] purchasedOrderInformation = cartPage.captureOrderInformation();
		log.debug("Verify correct price is being displayed");
		Assert.assertTrue(purchasedOrderInformation[1].contains(priceOnCartPage),
				"Wrong price displayed on Order confirmation Popup");
		log.debug("Verify correct name is being displayed");
		Assert.assertTrue(purchasedOrderInformation[3].contains(name),
				"Wrong name displayed on Order confirmation Popup");
		log.debug("Verify correct credit card is being displayed");
		Assert.assertTrue(purchasedOrderInformation[2].contains(creditCardNumber),
				"Wrong creditCardNumber displayed on Order confirmation Popup");

		System.out.println("Step 9 : Click on OK");
		log.info("Clicking on Ok button");
		cartPage.clickOnOkButton();

	}

	public void selectingLaptopAndSuccessfullyAddingItToCart(String productName) {
		// clicks on laptops link
		homePage.clickOnLaptopsLink();
		// selects laptop using the productName
		productPage = homePage.selectLaptopByName(productName);
		// verify that the product displayed on Product Page is the one which was
		// selected
		Assert.assertEquals(productPage.getProductName(), productName,
				String.format("[%s] is not being displayed on Product Page", productName));
		// click on Add to Cart button
		productPage.clickOnAddToCartButton();
		// click on the OK button on Pop up Confirmation
		productPage.acceptPopUpConfirmation();
	}

	public void verifyProductCategoryNavigation(String category) {
		Assert.assertTrue(homePage.productDescription().toLowerCase().contains(category),
				String.format("[%s] : Products displayed do not belong to this product category", category));
	}
}