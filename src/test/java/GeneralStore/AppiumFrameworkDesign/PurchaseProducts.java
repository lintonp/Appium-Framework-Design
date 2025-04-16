package GeneralStore.AppiumFrameworkDesign;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import GeneralStore.AppiumFrameworkDesign.utilities.genericMethods;
import io.appium.java_client.android.Activity;

public class PurchaseProducts extends BaseTest {
	
	@BeforeTest
	public void preTest() {
//		Activity activity = new Activity("io.appium.android.apis", "com.androidsample.generalstore.MainActivity");
//      driver.startActivity(activity);
		Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", 
													ImmutableMap.of("intent", "com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
	}
	
	@Test(dataProvider = "getLoginData")
	public void validateLogin(HashMap<String, String> data) {
		
		System.out.println("Name: " + data.get("name"));
        System.out.println("Gender: " + data.get("gender"));
        System.out.println("Country: " + data.get("country"));
        System.out.println("--------------");
		
		Assert.assertTrue(lp.validateLoginTitle());
		
		lp.login("Linton");
		
		Assert.assertFalse(lp.validateLoginTitle());

	}
	
	@Test
	public void errorLoginValidation() {
		lp.clickLoginButton();
		Assert.assertTrue(lp.validateLoginTitle());
	}
	
	@Test
	public void purchaseProduct() {
		Products productsPage = lp.login("Linton");

		productsPage.addProductToCart("Jordan 6 Rings");
		
		Cart cart = productsPage.goToCart();
		
		cart.clickPurchase();
		
		
	}
	
	@DataProvider
	public Object[][] getLoginData(){
		
		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String,String>>();
		try {
			dataList = genericMethods.getJsonData( System.getProperty("user.dir") 
					+ "\\src\\test\\java\\resources\\TCdata\\loginData.json");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        Object[][] loginData = new Object[dataList.size()][1];
        
        for (int i = 0; i < dataList.size(); i++) {
        	loginData[i][0] = dataList.get(i);
        }
		
		return loginData;
	}
	
}
