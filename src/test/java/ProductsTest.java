import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.yaml.snakeyaml.reader.ReaderException;

import readingFromFile.TxtFileReading;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class ProductsTest {
	WebDriver driver;
	SandboxLoginPageObject loginPage;
	ProductsPageObject productPage;

	@BeforeTest
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@BeforeClass
	public void logInUser() {
		driver.get(SandboxLoginPageObject.LOGIN_URL);
		loginPage = new SandboxLoginPageObject(driver, TxtFileReading.readXPaths());
		loginPage.typeUsername("cjoskowiczc5");
		loginPage.typePassword("S2TlZROe");
		loginPage.clickLoginButton();
	}
	
	@BeforeMethod
	public void inicialization() {
		productPage = new ProductsPageObject(driver, TxtFileReading.readXPaths());
		
	}
// Testirati dodavanje 5 proizvoda - potrebno je popuniti samo osnovne podatke podacima iz Excel fajla.
//	@Test (priority=0)
//	public void addProducts() throws IOException {
//		SoftAssert sa = new SoftAssert();
//		FileInputStream fis = new FileInputStream("src\\test\\java\\NewProducts.xlsx");
//		XSSFWorkbook wb = new XSSFWorkbook(fis);
//		XSSFSheet sheet = wb.getSheetAt(0);
//		Row row;
//		for (int i = 1; i < sheet.getLastRowNum(); i++) {
//			row = sheet.getRow(i);
//			productPage.clickProductsCard();
//			productPage.clicladdNewProduct();
//			productPage.typeProdustName(row.getCell(1).toString());
//			productPage.typeProductPrice(row.getCell(2).toString());
//			productPage.clickButtonSaveChanges();
//			sa.assertEquals(productPage.addedProdMessage(), "Update successful");		
//		}
//	}
	
//	Poveæati cenu prethodno kreiranih proizvoda za 100 i proveriti uspešnost izmena.
	@Test (priority = 1)
	public void increasePrice() {
		productPage.clickProductsCard();
		productPage.clickEditProducts();
		productPage.increasePrice(100.00);
	}

	@AfterClass
	public void logOutUser() {
		loginPage.logOut();
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

}
