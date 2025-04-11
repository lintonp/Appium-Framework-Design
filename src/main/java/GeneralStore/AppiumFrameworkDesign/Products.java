package GeneralStore.AppiumFrameworkDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import GeneralStore.AppiumFrameworkDesign.utilities.AppGestures;
import GeneralStore.AppiumFrameworkDesign.utilities.genericMethods;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Products extends AppGestures {
	
	public AndroidDriver driver;
	
	public Products(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	public WebElement PageTitle;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement goToCart;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName']")
	public List<WebElement> Products_Name;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice']")
	public List<WebElement> Products_AddToCart;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']")
	public List<WebElement> Products_price;
	
	private String Products_Name_xpath = "//android.widget.TextView[@text='XP_name']";
	private String Products_price_xpath = "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice']";
	private String Products_AddToCart_xpath = "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']";
	
	public boolean validateProductsLandingPage() {
		try {
			return PageTitle.isDisplayed() && PageTitle.getText().equals("Products");
		} 
		catch (StaleElementReferenceException e) {
			e.printStackTrace();
			return false;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String addProductToCart(String productName) {
		String productPrice = "";
		try {
			scrollIntoViewElementText(productName);
			
			String addToCartXP = Products_Name_xpath.replace("XP_name", productName) + 
					"/parent::android.widget.LinearLayout"
					+ Products_AddToCart_xpath;
			String priceXP = Products_Name_xpath.replace("XP_name", productName) + 
					"/parent::android.widget.LinearLayout"
					+ Products_price_xpath;
			
			productPrice = driver.findElement(By.xpath(priceXP)).getText();
			
			driver.findElement(By.xpath(addToCartXP)).click();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productPrice;
	}
	
	public double addProductstoCart(List<String> products) {
		double amount = 0;
		try {
			List<Double> prices = products.stream().map(product -> genericMethods.convertCurrencytoDouble(addProductToCart(product))).collect(Collectors.toList());
			System.out.println(prices);
			amount = products.stream().mapToDouble(product -> genericMethods.convertCurrencytoDouble(addProductToCart(product))).sum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amount;
	}
	
	public Cart goToCart() {
		try {
			goToCart.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Cart(driver);
	}
	
}
