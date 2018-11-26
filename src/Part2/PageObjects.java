package Part2;
/* Title - Page Objects for vehicle registration web page.
Author - Srini Ramkumar.
*/
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;

public class PageObjects {
	
	// locating the web elements.
	
	@FindBy(xpath = "//a[@href=\"https://www.vehicleenquiry.service.gov.uk\"] [@role=\"button\"]")
	static WebElement startNowButton;
	
	@FindBy(id = "Vrm")
	static WebElement inputVehRegNum;
	
	@FindBy(tagName = "Button")
	static WebElement continueButton;
	
	@FindBy(css = "#pr3 > div > ul > li:nth-child(1) > span.reg-mark")
	static WebElement regNumber;
	
	@FindBy(css = "li.list-summary-item > span > strong") 
	static WebElement make;
	
	@FindBy(xpath = "//li[@class=\"list-summary-item\"][last()]")
	static WebElement colour;
	
	@FindBy(xpath = "//a[@href=\"javascript:history.back()\"] [@class=\"back-to-previous link-back\"]")
	static WebElement backButton;
	
	// Initializing the webelements of the webpage using page factory.
	public void pageFactory() {
		WebDriver driver = new ChromeDriver();
		PageFactory.initElements(driver, PageObjects.class);
	}
	
	// method to click startnow button.
	public static void clickStartNowButton(){
		startNowButton.click();
	}
	
	// method to enter vehicle registration number.
	public static void sendVehRegNum(String regNumber){
		inputVehRegNum.sendKeys(regNumber);
	}
	
	// method to click continue button.
	public static void clickContinueButton(){
		continueButton.click();
	}
	
	// method to get the make.
		public static String getRegNumber(){
			String vehRegNumber = regNumber.getText();
			return vehRegNumber;
		}
	
	// method to click back button.
	public static void clickbackButton(){
		backButton.click();
	}
	
	// method to get the make.
	public static String getMake(){
		String makeName = make.getText();
		return makeName;
	}
	
	// method to get the colour.
	public static String getColour(){
		String colourName = colour.getText().substring(7); 
		return colourName;
	}
}