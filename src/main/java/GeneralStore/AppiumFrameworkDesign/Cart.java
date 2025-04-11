package GeneralStore.AppiumFrameworkDesign;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import GeneralStore.AppiumFrameworkDesign.utilities.AppGestures;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Cart extends AppGestures {

	public AndroidDriver driver;
	
	public Cart(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	public WebElement pageTitle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	public WebElement purchaseButton;
	
	public boolean verifyCartTitle() {
		return pageTitle.getText().equals("Cart");
	}
	
	public void clickPurchase() {
		try {
			purchaseButton.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
