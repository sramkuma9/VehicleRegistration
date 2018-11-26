package Part2;

/* Title - Test for checking the make and colour of a vehicle.
 Author - Srini Ramkumar.
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.util.FileCopyUtils;

import Part1.ScanConfigFolder;

public class Action {
	@Test
	public void testCase001(){
		//calling the methods to scan the configure folder and get the input files.
		ArrayList<String> fileName = ScanConfigFolder.configScan();
		
		// Setting up chrome driver - geckodriver.
		System.setProperty("webdriver.chrome.driver", "/home/ajay/Srini/libraryFIlesSelenium/Srini/ChromeDriver/chromedriver");
		//invoking chrome and navigating to the URL.
		WebDriver driver = new ChromeDriver();
		String appUrl = "https://www.gov.uk/get-vehicle-information-from-dvla";
		driver.get(appUrl);	
		// waiting the webpage to load.
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
		PageFactory.initElements(driver, PageObjects.class);
		// clicking the startNow button.
		PageObjects.clickStartNowButton();
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