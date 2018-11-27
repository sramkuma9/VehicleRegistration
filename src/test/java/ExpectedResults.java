/* Title - Test for driving the vehicle registration expected values from excel.
Author - Srini Ramkumar.
*/
import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExpectedResults {
	
	private static HSSFWorkbook workbook;
	private static HSSFSheet sheet;
	private static String dataCellValueMake;
	private static String dataCellValueColour;
	
	public static void expResult(String fileName, int i, String make, String colour){
		// path of the input file.
		//String fileName = "C:\\Srini\\printOut\\DataSheet.xls";
		String sheetName = "Sheet1";
		FileInputStream file;
		workbook = null;
		// using apache POI to get to excel sheet.
		try {
			file = new FileInputStream(new File(fileName));
			workbook = new HSSFWorkbook(file);
			sheet = workbook.getSheet(sheetName);
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// getting the make from the excel sheet.
		dataCellValueMake = sheet.getRow(i).getCell(2).getStringCellValue();
		// getting the colour from the excel sheet.
		dataCellValueColour = sheet.getRow(i).getCell(3).getStringCellValue();
		String regNumber = PageObjects.getRegNumber();
		System.out.println("The expected make for reg number: " + regNumber + " record is " + dataCellValueMake);
		System.out.println("The expected colour for reg number: " + regNumber + " record is " + dataCellValueColour);
		// Asserting the make for a vehicle registration number.
		assertEquals(dataCellValueMake,make);
		// Asserting the make for a vehicle registration number.
		assertEquals(dataCellValueColour,colour);
	}
}