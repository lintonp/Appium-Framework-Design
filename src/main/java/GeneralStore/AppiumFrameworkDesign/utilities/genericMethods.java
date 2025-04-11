package GeneralStore.AppiumFrameworkDesign.utilities;

public class genericMethods {
	public static double convertCurrencytoDouble(String price) {
		return Double.parseDouble(price.replaceAll("[$,]", ""));
	}
}
