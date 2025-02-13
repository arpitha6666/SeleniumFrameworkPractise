package dataUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    public List<HashMap<String,String>> getJsonToMap() throws IOException {
        //read JSON to
       String jsonContent= FileUtils.readFileToString(new File(System.getProperty("user.dir") + "/src/test/java/data/PurchaseData.json", String.valueOf(StandardCharsets.UTF_8)));
       //String to map can be done using Jackson-databind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
        });
        return data;
    }

}
