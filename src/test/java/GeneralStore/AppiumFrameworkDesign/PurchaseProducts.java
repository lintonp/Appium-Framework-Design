package GeneralStore.AppiumFrameworkDesign;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.StartsActivity;

public class PurchaseProducts extends BaseTest {
	
//	@BeforeTest
//	public void preTest() {
////		Activity activity = new Activity("io.appium.android.apis", "com.androidsample.generalstore.MainActivity");
////      driver.startActivity(activity);
//		Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
//		((JavascriptExecutor) driver).executeScript("mobile: startActivity", 
//													ImmutableMap.of("intent", "com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
//	}
	
	@Test(dataProvider = "getLoginData")
	public void validateLogin(String name, String gender, String country) {
//		loginPage lp = new loginPage(driver);
		
		System.out.println(name  +", "+ gender +", "+ country);
		
//		Assert.assertTrue(lp.validateLoginTitle());
//		
//		lp.login("Linton");
//		
//		Assert.assertFalse(lp.validateLoginTitle());

	}
	
	@Test
	public void errorLoginValidation() {
		lp.clickLoginButton();
		Assert.assertTrue(lp.validateLoginTitle());
	}
	
	@Test
	public void purchaseProduct() {
//		loginPage lp = new loginPage(driver);
		Products productsPage = lp.login("Linton");

		productsPage.addProductToCart("Jordan 6 Rings");
		
		Cart cart = productsPage.goToCart();
		
		cart.clickPurchase();
		
		
	}
	
	@DataProvider
	public Object[][] getLoginData(){
		return new Object[][] {{"Messi", "Male", "Argentina"},{"Ronaldo", "Male", "Portugal"}};
	}
	
}
