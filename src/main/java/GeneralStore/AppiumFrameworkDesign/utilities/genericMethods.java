package GeneralStore.AppiumFrameworkDesign.utilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class genericMethods {
	public static double convertCurrencytoDouble(String price) {
		return Double.parseDouble(price.replaceAll("[$,]", ""));
	}
	
	public static List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), new TypeReference<List<HashMap<String, String>>>() {});
    }
}
