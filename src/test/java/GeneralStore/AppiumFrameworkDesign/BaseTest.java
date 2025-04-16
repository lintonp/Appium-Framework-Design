package GeneralStore.AppiumFrameworkDesign;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	loginPage lp;
	
	@BeforeClass
	public void configureAppium() throws FileNotFoundException, IOException {
		
		Properties props = new Properties();
		props.load(new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\GeneralStore\\AppiumFrameworkDesign\\resources\\globalData.properties"));
		
		String ipAddress = props.getProperty("ipAddress"),
				androidDeviceName = props.getProperty("androidDeviceName");
		int portNumber = Integer.parseInt(props.getProperty("portNumber")),
			implicitWaitDuration = Integer.parseInt(props.getProperty("implicitWaitDuration"));
		
		service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Linton Pereira\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(ipAddress).usingPort(portNumber).build();
		service.start();
				
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(androidDeviceName);
		options.setApp(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\General-Store.apk");
		
		driver = new AndroidDriver(new URL("http://"+ipAddress+":"+portNumber+"/"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitDuration));
		
		lp = new loginPage(driver);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}
	
}
