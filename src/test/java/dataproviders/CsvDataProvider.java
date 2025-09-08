package dataproviders;

import org.testng.annotations.DataProvider;
import utils.CsvUtils;
import utils.CsvUtils.Credential;

import java.io.IOException;
import java.util.List;

public class CsvDataProvider {

    @DataProvider(name = "csvData")
    public Object[][] provideCsvData() throws IOException {
        String path = System.getProperty("user.dir") + "/src/test/resources/testdata/users.csv";
        List<Credential> credentials = CsvUtils.readCredentials(path);

        Object[][] data = new Object[credentials.size()][1];
        for (int i = 0; i < credentials.size(); i++) {
            data[i][0] = credentials.get(i); // wrap each Credential in an Object[]
        }

        return data;
    }
}
