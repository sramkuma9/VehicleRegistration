/* Title - Test for driving the vehicle registration values from excel.
Author - Srini Ramkumar.
*/
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.util.ArrayList;
	import org.apache.poi.hssf.usermodel.HSSFSheet;
	import org.apache.poi.hssf.usermodel.HSSFWorkbook;

	public class InputDataSheet {
		private static HSSFWorkbook workbook;
		private static HSSFSheet sheet;
		private static int rowCount;
		private static int currentRow;
		private static String dataCellValue;
		
		public static ArrayList<String> driveVehicleRegistrationNumber(String fileName){
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
			// getting the last row of the sheet.
			rowCount = sheet.getLastRowNum();
			ArrayList<String> vehicleRegistrationNumber = new ArrayList<>();
			// getting the cell value from the excel sheet.
			for(currentRow=2;currentRow<=rowCount;currentRow++)
			{
				dataCellValue = sheet.getRow(currentRow).getCell(1).getStringCellValue();
				System.out.println("The entered vehicle registration number for " + (currentRow-1) + " record is " + dataCellValue);
				vehicleRegistrationNumber.add(dataCellValue);
			}
			return vehicleRegistrationNumber;
		}
}