package qtriptest.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.sound.midi.Soundbank;
import org.apache.poi.hssf.record.cf.DataBarFormatting;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {


    // public static void print2DArray(Object[][] arr) {
    //     for (int i = 0; i < arr.length; i++) {
    //         for (int j = 0; j < arr[i].length; j++) {
    //             System.out.print(arr[i][j] + " ");
    //         }
    //         System.out.println();
    //     }
    // }
 

    public static String[][] getDatafromExcel(String sheetName) {
        try {
            File excelDataFile = new File("app/src/test/resources/DatasetsforQTrip.xlsx");
            FileInputStream fileinput = new FileInputStream(excelDataFile);

            
           
            // Initiate the excel workbook
            XSSFWorkbook workbook = new XSSFWorkbook(fileinput);
            XSSFSheet sheet = workbook.getSheet(sheetName);
          
            int rowCount = sheet.getPhysicalNumberOfRows();
            System.out.println(rowCount);
            int colcount = sheet.getRow(0).getLastCellNum();
            System.out.println(colcount);

            // Define 2D string array to store data
            String[][] data = new String[rowCount -1][colcount- 1];
            DataFormatter dataFormatter = new DataFormatter();


            // Iteration through rows and columns
            for (int i = 1; i < rowCount; i++) {
                for (int j = 1; j < colcount; j++) {
                    String value = dataFormatter.formatCellValue(sheet.getRow(i).getCell(j));
                    System.out.println(value);
                    data[i -1][j - 1] = value;

                }

            }

            workbook.close();
            fileinput.close();

           // print2DArray(data);


            return data;

        }


        catch (IOException e) {
            e.printStackTrace();
            e.getMessage();

        }
        return new String[0][];
    }



    public static void main(String[] args) {
        ExcelUtils.getDatafromExcel("TestCase01");
        ExcelUtils.getDatafromExcel("TestCase02");
        ExcelUtils.getDatafromExcel("TestCase03");
        
        
    }
}

