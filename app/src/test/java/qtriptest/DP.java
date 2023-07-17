package qtriptest;

import qtriptest.Utils.ExcelUtils;
import org.testng.annotations.DataProvider;

public class DP {


    @DataProvider(name="DatasetsforQTrip")
    public Object [][] datasetsforQTrip(){
        return ExcelUtils.getDatafromExcel("TestCase01");

    }

    
}
