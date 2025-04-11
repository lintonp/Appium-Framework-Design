package GeneralStore.AppiumFrameworkDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GeneralStore.AppiumFrameworkDesign.utilities.AppGestures;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class loginPage extends AppGestures {
	
	public AndroidDriver driver;
	public loginPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id = "android:id/text1")
	public WebElement countryDropdown;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	public WebElement name;
//	@AndroidFindBy(xpath = "//android.widget.EditText")
//	public WebElement name;
	
	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
	public WebElement genderMale;
	
	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
	public WebElement genderFemale;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	public WebElement letsShopButton;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	public WebElement generalStoreTitle;
	
	public boolean validateLoginTitle() {
		try {
			return generalStoreTitle.getText().trim().equals("General Store");
		}
		catch (StaleElementReferenceException e) {
			return false;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void selectDropdown(String country) {
		try {
			countryDropdown.click();
			
			scrollIntoViewElementText(country);			
			String dropdownOptions_XP = "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\""+country+"\"]";
			driver.findElement(By.xpath(dropdownOptions_XP)).click();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void login(String country, String fullName, String gender) {
		selectDropdown(country);
		if(gender.equalsIgnoreCase("male")) genderMale.click();
		else genderFemale.click();
		
		login(fullName);
	}
	
	public Products login(String fullName) {
		waitForElementVisible(name, 5);
		name.sendKeys(fullName);
		letsShopButton.click();
		
		return new Products(driver);
	}
	
	public void clickLoginButton() {
		try {
			letsShopButton.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
