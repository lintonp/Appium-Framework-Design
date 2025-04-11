package GeneralStore.AppiumFrameworkDesign.utilities;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AppGestures {
	AndroidDriver driver;
	public AppGestures(AndroidDriver driver) {
		this.driver = driver;
	}
	
	public void longClick(WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) element).getId()
				));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void scrollIntoViewElementText(String elementText) {
		try {
			driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+elementText+"\"))"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void swipeGesture(WebElement element, String direction) {
		try {
			((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) element).getId(),
			    "direction", direction,
			    "percent", 0.75
			));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dragGesture(WebElement element, int x, int y) {
		try {
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) element).getId(),
				    "endX", x,
				    "endY", y
				));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getAttribute_JS(WebElement element, String attribute) {
		String attributeValue = "";
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			attributeValue = (String) js.executeScript("return arguments[0].getAttribute('" +attribute+ "');", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attributeValue;
	}
	
	public void waitForElementVisible(WebElement we, int time) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
			wait.until(ExpectedConditions.visibilityOf(we));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
