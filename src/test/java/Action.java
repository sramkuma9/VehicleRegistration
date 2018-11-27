/* Title - Test for checking the make and colour of a vehicle.
 Author - Srini Ramkumar.
 */
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
//import org.springframework.util.FileCopyUtils;

public class Action extends BaseTest{
	
	public static org.openqa.selenium.WebDriver driver;
	private static PageObjects pO;
	
	@BeforeMethod
	@org.testng.annotations.BeforeClass
	public void setUp() {
		driver=getDriver();		
		pO = new PageObjects(driver);
	}
	@Test
	public void testCase001() throws InterruptedException{
		//calling the methods to scan the configure folder and get the input files.
		ArrayList<String> fileName = ScanConfigFolder.configScan();
		TimeUnit.SECONDS.sleep(3);
		// clicking the startNow button.
		pO.clickStartNowButton();
		TimeUnit.SECONDS.sleep(3);
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS) ;
		int j=1;
		for (Iterator<String> iter = fileName.iterator(); iter.hasNext(); j++)
		{ 
			String callFileName = iter.next();
			System.out.println(callFileName);
			// driving the vehicle registration number from the imput file.
			ArrayList<String> VehicleRegistrationNumber = InputDataSheet.driveVehicleRegistrationNumber(callFileName);			
			int i = 2;
			for (Iterator<String> it = VehicleRegistrationNumber.iterator(); it.hasNext(); i++)
			{
				String vehRegNum = it.next();
				PageObjects.sendVehRegNum(vehRegNum);
				PageObjects.clickContinueButton();
				// getting the make from the webpage.
				String make = PageObjects.getMake();
				String regNumber = PageObjects.getRegNumber();
				System.out.println("The actual make for reg number: " + regNumber + " is " + make);
				// getting the colour from the webpage.
				String colour = PageObjects.getColour();
				System.out.println("The actual colour for reg number: " + regNumber + " is " + colour);
				// checking the result.
				ExpectedResults.expResult(callFileName,i,make,colour);
				// take screenshot function
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				// copy screenshot locally.
				try {
					FileCopyUtils.copy(scrFile, new File("c:\\tmp\\screenshot" + i + ".png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				PageObjects.clickbackButton();
				driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
			}	
		}
		//closing the driver.
		driver.close();
	}
}