package Part1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	
	//Driver instance on which tests would be run
	public static WebDriver driver;
	
	static String driverPath = "/home/ajay/Srini/libraryFIlesSelenium/Srini/ChromeDriver/";
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void setDriver(String browserType, String appURL) throws Exception
	{	
	    switch(browserType)
	    {
	    	case "IE":
	    		driver = initInternetExplorerDriver(appURL);
	    		break;
	    	case "FIREFOX":
	    		driver = initFirefoxDriver(appURL);
	    		break;
	    	case "CHROME":
	    		driver = initChromeDriver(appURL);
	    		System.out.println("in chrome...");
	    		break;
	    	default:
	    		System.out.println("browser : "+ browserType + "is invalid, launching Chrome as browser of choice..");
	    		driver = initChromeDriver(appURL);
	    }
		
	}
	
	private static WebDriver initChromeDriver(String appURL) {
		//String envName = PropertiesManager.getProperty("EnvName");
		//System.out.println("Executing the tests in " + envName + " environment");
		System.out.println("Launching google chrome with new profile..");
		String exePath = driverPath + "chromedriver";
		System.setProperty("webdriver.chrome.driver", exePath);
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.SECONDS);
		//driver.get(appURL);
		driver.navigate().to(appURL);
		return driver;
	}
	
	private static WebDriver initInternetExplorerDriver(String appURL) {
		System.out.println("Launching google chrome with new profile..");
		String exePath = driverPath + "IEDriverServer.exe";
		System.setProperty("webdriver.ie.driver", exePath);
		System.out.println("Launching Internet browser..");
		WebDriver driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}
	
	private static WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		//firefox driver path
		//WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}
	
	//@Parameters({ "browserType", "appURL" })
	@BeforeSuite
	public void initializeTestBaseSetup() {
		System.out.println("begin");
		try {
			//PropertiesManager.initializeProperties();
			setDriver("CHROME","http://www.qaworks.com/");
			//String BowserType = "CHROME";
			//String URL = "http://www.qaworks.com/";
		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}
	
	@AfterSuite
	public void AfterSuite() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		driver.close();
	}

}

