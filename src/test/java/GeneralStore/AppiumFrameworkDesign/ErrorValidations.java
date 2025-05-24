package GeneralStore.AppiumFrameworkDesign;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidations extends BaseTest {
	@Test
	public void errorLoginValidation() {
		System.out.println("Running Login Errorvalidations");
		lp.clickLoginButton();
		Assert.assertTrue(lp.validateLoginTitle());
	}
}
