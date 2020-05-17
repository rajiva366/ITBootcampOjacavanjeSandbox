import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SandboxLoginPageObject {
	
	public static String LOGIN_URL = "https://sandbox.2checkout.com/sandbox";
	
	public static String TXTUSERNAME = "TXTUSERNAME";
	public static String TXTPASSWORD = "TXTPASSWORD";
	public static String TXTLOGINBUTTON = "TXTLOGINBUTTON";
	public static String TXTINCCORECTLOGINMESSAGE = "TXTINCCORECTLOGINMESSAGE";
	public static String TXTLOGEDUSERMANE = "TXTLOGEDUSERMANE";
	public static String TXTLOGEDICON = "TXTLOGEDICON";
	public static String TXTLOGOUTBUTTON = "TXTLOGOUTBUTTON";
	
	WebDriver driver;
	Map<String, String> xPaths;
	
	public SandboxLoginPageObject(WebDriver driver, Map<String, String> xPaths) {
		super();
		this.driver = driver;
		this.xPaths = xPaths;
	}
	
	public void typeUsername(String key) {
		driver.findElement(By.xpath(xPaths.get(TXTUSERNAME))).sendKeys(key);;
	}
	
	public void typePassword(String key) {
		driver.findElement(By.xpath(xPaths.get(TXTPASSWORD))).sendKeys(key);
	}
	
	public void clickLoginButton() {
		driver.findElement(By.xpath(xPaths.get(TXTLOGINBUTTON))).click();
	}
	
	public String incorrectLoginMessage() {
		WebElement inccorectMess = driver.findElement(By.xpath(xPaths.get(TXTINCCORECTLOGINMESSAGE)));
//		return driver.findElement(By.className("content")).findElement(By.tagName("p")).getText();
		return inccorectMess.getText();
	}
	
	public String logedUsername() {
		WebElement logy = driver.findElement(By.xpath(xPaths.get(TXTLOGEDUSERMANE)));
		return logy.getText();
	}
	
	public void logOut() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(TXTLOGEDICON))));
		driver.findElement(By.xpath(xPaths.get(TXTLOGEDICON))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(TXTLOGOUTBUTTON))));
		driver.findElement(By.xpath(xPaths.get(TXTLOGOUTBUTTON))).click();
	}
	
	
	
}
