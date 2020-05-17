import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import readingFromFile.TxtFileReading;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class SandLoginTest {
	WebDriver driver;
	SandboxLoginPageObject loginPage;

	@BeforeMethod
	public void inIt() {
		loginPage = new SandboxLoginPageObject(driver, TxtFileReading.readXPaths());
	}

	@BeforeClass
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

// Testirati da li je moguæe ulogovati se ukoliko je preskoèen korak registracije korisnika (da li se može prijaviti koristeæi podatke koji nikada nisu saèuvani u bazi korisnika).
	@Test(priority = 0)
	public void loginWithoutRegistration() {
		driver.get(SandboxLoginPageObject.LOGIN_URL);
		loginPage.typeUsername("Looda");
		loginPage.typePassword("Looda123");
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.incorrectLoginMessage(), "Incorrect username or password.");
	}
// Testirati logovanje jednog korisnika 
	@Test(priority = 1)
	public void loginAndOutValidUser() {
		driver.get(SandboxLoginPageObject.LOGIN_URL);
		loginPage.typeUsername("Vukasin2");
		loginPage.typePassword("Vukasin123");
		loginPage.clickLoginButton();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(loginPage.logedUsername(), "Vukasin2");
		loginPage.logOut();
		String currentUrl = driver.getCurrentUrl();
		sa.assertEquals(currentUrl, "https://sandbox.2checkout.com/sandbox");
	}
// Testirati logovanje korisnika podacima iz Excel fajla
	@Test(priority = 2)
	public void login30Users() throws IOException {
		SoftAssert sa = new SoftAssert();
		driver.get(SandboxLoginPageObject.LOGIN_URL);
		FileInputStream fis = new FileInputStream("src\\test\\java\\users.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		Row row;
		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			loginPage.typeUsername(row.getCell(1).toString());
			loginPage.typePassword(row.getCell(3).toString());
			loginPage.clickLoginButton();
			sa.assertEquals(loginPage.logedUsername(), row.getCell(1).toString());
			loginPage.logOut();
			sa.assertEquals(driver.getCurrentUrl(), "https://sandbox.2checkout.com/sandbox");
		}
	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

}
