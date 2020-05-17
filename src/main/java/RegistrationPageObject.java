import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPageObject {
	
	public static String Registration_URL="https://www.2checkout.com/pricing/2sell/";
	
	
	public static String TXTREGFIRSTNAME = "TXTREGFIRSTNAME";
	public static String TXTREGLASTNAME = "TXTREGLASTNAME";
	public static String TXTREGEMAIL = "TXTREGEMAIL";
	public static String TXTREGPASSWORD = "TXTREGPASSWORD";
	public static String TXTREGWEBSITE = "TXTREGWEBSITE";
	public static String TXTREGCOUNTRY = "TXTREGCOUNTRY";
	public static String TXTREGROBOT = "TXTREGROBOT";
	public static String TXTREGBUTTON = "TXTREGBUTTON";
	public static String TXTREGPHISICALPRODUCTS= "TXTREGPHISICALPRODUCTS";
	public static String TXTCREATEACCOUNTBOTTON = "TXTCREATEACCOUNTBOTTON";
	public static String TXTREGISTRETEDTEXT = "TXTREGISTRETEDTEXT";
	public static String TXTNOFIRSTNAME = "TXTNOFIRSTNAME";
	public static String TXTNOLASTNAME = "TXTNOLASTNAME";
	public static String TXTNOEMAIL = "TXTNOEMAIL";
	public static String TXTNOPASSWORD = "TXTNOPASSWORD";
	public static String TXTNOWEBSITE = "TXTNOWEBSITE";
	public static String TXTNOROBOT = "TXTNOROBOT";
	
	
	WebDriver driver;
	Map<String, String> xPaths;
	
	
	public RegistrationPageObject(WebDriver driver, Map<String, String> xPaths) {
		super();
		this.driver = driver;
		this.xPaths = xPaths;
	}

	public void typeRegFirstname(String firstName) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(TXTREGFIRSTNAME))));
		driver.findElement(By.xpath(xPaths.get(TXTREGFIRSTNAME))).sendKeys(firstName);
	}
	
	public void typeRegLastname(String lastName) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(TXTREGLASTNAME))));
		driver.findElement(By.xpath(xPaths.get(TXTREGLASTNAME))).sendKeys(lastName);
	}
	
	public void typeRegEmail(String email) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(TXTREGEMAIL))));
		driver.findElement(By.xpath(xPaths.get(TXTREGEMAIL))).sendKeys(email);
	}
	
	public void typeRegPassword(String password) {
		driver.findElement(By.xpath(xPaths.get(TXTREGPASSWORD))).sendKeys(password);
	}
	
	public void typeRegWebsite(String website) {
		driver.findElement(By.xpath(xPaths.get(TXTREGWEBSITE))).sendKeys(website);
	}
	
	public void selectRegCountry(String country) {
		Select state = new Select(driver.findElement(By.xpath(xPaths.get(TXTREGCOUNTRY))));
		state.selectByVisibleText(country);
	}
	
	public void NotRobotReg() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(TXTREGROBOT))));
		WebElement robot = driver.findElement(By.xpath(xPaths.get(TXTREGROBOT)));
		if(!robot.isSelected())
		robot.click();
	}
	
	public void clickRegistration() {
		driver.findElement(By.xpath(xPaths.get(TXTREGBUTTON))).click();
	}
	
	public void choosePhysicalProducts() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(TXTREGPHISICALPRODUCTS))));
		driver.findElement(By.xpath(xPaths.get(TXTREGPHISICALPRODUCTS))).click();
	}
	
	public void clickCreateAccount() {
		driver.findElement(By.xpath(xPaths.get(TXTCREATEACCOUNTBOTTON))).click();
	}
	
	public String messageRegistrationCorrect () {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(TXTREGISTRETEDTEXT))));
		WebElement correctReg = driver.findElement(By.xpath(xPaths.get(TXTREGISTRETEDTEXT)));
		return correctReg.getText();
	}
	
	
	public String withoutFirstnameReg () {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(TXTNOFIRSTNAME))));
		WebElement noFN = driver.findElement(By.xpath(xPaths.get(TXTNOFIRSTNAME)));
		return noFN.getText();
	}
	
	public String withoutLastname () {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(TXTNOLASTNAME))));
		WebElement noLN = driver.findElement(By.xpath(xPaths.get(TXTNOLASTNAME)));
		return noLN.getText();
	}
	
	public String withoutEmail() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(TXTNOEMAIL))));
		WebElement noEmail = driver.findElement(By.xpath(xPaths.get(TXTNOEMAIL)));
		return noEmail.getText();
	}
	
	public String withoutPassword() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(TXTNOPASSWORD))));
		WebElement noPass = driver.findElement(By.xpath(xPaths.get(TXTNOPASSWORD)));
		return noPass.getText();
	}
	
	
	public String withoutWebsite() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(TXTNOWEBSITE))));
		WebElement noWeb = driver.findElement(By.xpath(xPaths.get(TXTNOWEBSITE)));
		return noWeb.getText();
	}
	
	public String withoutRobot() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(TXTNOROBOT))));
		WebElement noRob= driver.findElement(By.xpath(xPaths.get(TXTNOROBOT)));
		return noRob.getText();
	}

}
