import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPageObject {
	
	public static String TXTPRODUCTS = "TXTPRODUCTS";
	public static String TXTADDNEWPRODUCTSBUTTON = "TXTADDNEWPRODUCTSBUTTON";
	public static String TXTTYPEPRODNAME = "TXTTYPEPRODNAME";
	public static String TXTTYPEPRODPRICE = "TXTTYPEPRODPRICE";
	public static String TXTNEWPRODUCTSAVECHANGESBUTTON = "TXTNEWPRODUCTSAVECHANGESBUTTON";
	public static String TXTADDEDPRODUCTMESSAGE = "TXTADDEDPRODUCTMESSAGE";
	public static String TXTEDITPRODUCTS = "TXTEDITPRODUCTS";
	
	WebDriver driver;
	Map<String, String> xPaths;
	
	public ProductsPageObject(WebDriver driver, Map<String, String> xPaths) {
		super();
		this.driver = driver;
		this.xPaths = xPaths;
	}
	
	public void clickProductsCard() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(TXTPRODUCTS))));
		driver.findElement(By.xpath(xPaths.get(TXTPRODUCTS))).click();
	}
	
	public void clicladdNewProduct() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(TXTADDNEWPRODUCTSBUTTON))));
		driver.findElement(By.xpath(xPaths.get(TXTADDNEWPRODUCTSBUTTON))).click();
	}
		
	public void typeProdustName(String name) {
		driver.findElement(By.xpath(xPaths.get(TXTTYPEPRODNAME))).sendKeys(name);
	}
	
	public void typeProductPrice(String price) {
		driver.findElement(By.xpath(xPaths.get(TXTTYPEPRODPRICE))).sendKeys(price);
	}
	
	public void clickButtonSaveChanges () {
		driver.findElement(By.xpath(xPaths.get(TXTNEWPRODUCTSAVECHANGESBUTTON))).click();
	}
	
	public String addedProdMessage() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(TXTADDEDPRODUCTMESSAGE))));
		WebElement message = driver.findElement(By.xpath(xPaths.get(TXTADDEDPRODUCTMESSAGE)));
		return message.getText();
	}
	
	public void clickEditProducts() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(TXTEDITPRODUCTS))));
		driver.findElement(By.xpath(xPaths.get(TXTEDITPRODUCTS))).click();
	}
	
	public void increasePrice (Double num) {
		List<WebElement> prods = driver.findElements(By.xpath("//*[contains(@name, 'price')]"));
		Iterator<WebElement>itr = prods.iterator();
		while(itr.hasNext()) {
			WebElement element = itr.next();
			String value = element.getAttribute("value");
			double increasedValue = Double.parseDouble(value)+num;
			element.clear();
			element.sendKeys(String.valueOf(increasedValue));
		}
	}

}
