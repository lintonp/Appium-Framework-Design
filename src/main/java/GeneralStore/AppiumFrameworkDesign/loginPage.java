package GeneralStore.AppiumFrameworkDesign;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import GeneralStore.AppiumFrameworkDesign.utilities.AppGestures;
import io.appium.java_client.android.AndroidDriver;

public class loginPage extends AppGestures {
	
	public AndroidDriver driver;
	public loginPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@FindBy()
	public WebElement name;
	
}
