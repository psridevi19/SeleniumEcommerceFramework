package ecom.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
            //read json to String
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"/src/main/java/ecom/data/PurchaseOrder.json"));

            //String to HashMap using Jackson DataBind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>() {
        });
        return data;

    }
}
