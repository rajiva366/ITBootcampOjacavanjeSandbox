import org.testng.annotations.Test;

import readingFromFile.TxtFileReading;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class SandboxRegistrationTest {
	
	WebDriver driver;
	RegistrationPageObject regPage;
	
	@BeforeClass
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void initalization() {
		regPage = new RegistrationPageObject(driver, TxtFileReading.readXPaths());
	}

//	Zakljucno sa testom sa priority =5 - Detaljno proveriti da li je moguæe registrovati se bez unosa svih polja.
	@Test (priority=0)
	public void registrationWithoutFirstname() {
		
		driver.get(RegistrationPageObject.Registration_URL);
		regPage.typeRegLastname("Maric");
		regPage.typeRegEmail("mm@yahoo.com");
		regPage.typeRegPassword("Maric123");
		regPage.typeRegWebsite("www.mmaric.com");
		regPage.selectRegCountry("Serbia");
		regPage.NotRobotReg();
		regPage.clickRegistration();
		
		Assert.assertEquals(regPage.withoutFirstnameReg(), "Please fill in this field");	
	}
	
	@Test (priority=1)
	public void registrationWithoutLastName() {
		driver.get(RegistrationPageObject.Registration_URL);
		regPage.typeRegFirstname("Mirjana");
		regPage.typeRegEmail("mm@yahoo.com");
		regPage.typeRegPassword("Maric123");
		regPage.typeRegWebsite("www.mmaric.com");
		regPage.selectRegCountry("Serbia");
		regPage.NotRobotReg();
		regPage.clickRegistration();
		
		Assert.assertEquals(regPage.withoutLastname(), "Please fill in this field");	
	}
	
	@Test (priority=2)
	public void registrationWithoutEmail() {
		driver.get(RegistrationPageObject.Registration_URL);
		regPage.typeRegFirstname("Mirjana");
		regPage.typeRegLastname("Maric");
		regPage.typeRegPassword("Maric123");
		regPage.typeRegWebsite("www.mmaric.com");
		regPage.selectRegCountry("Serbia");
		regPage.NotRobotReg();
		regPage.clickRegistration();
		
		Assert.assertEquals(regPage.withoutEmail(), "Please fill in this field");	
	}
	
	@Test (priority=3)
	public void registrationWithoutPassword() {
		driver.get(RegistrationPageObject.Registration_URL);
		regPage.typeRegFirstname("Mirjana");
		regPage.typeRegLastname("Maric");
		regPage.typeRegEmail("mm@yahoo.com");
		regPage.typeRegWebsite("www.mmaric.com");
		regPage.selectRegCountry("Serbia");
		regPage.NotRobotReg();
		regPage.clickRegistration();
		
		Assert.assertEquals(regPage.withoutPassword(), "Please fill in this field");	
	}
	
	@Test (priority=4)
	public void registrationWithoutWebsite() {
		driver.get(RegistrationPageObject.Registration_URL);
		regPage.typeRegFirstname("Mirjana");
		regPage.typeRegLastname("Maric");
		regPage.typeRegEmail("mm@yahoo.com");
		regPage.typeRegPassword("Maric123");
		regPage.selectRegCountry("Serbia");
		regPage.NotRobotReg();
		regPage.clickRegistration();
		
		Assert.assertEquals(regPage.withoutWebsite(), "Please fill in this field");	
	}
	
	@Test (priority=5)
	public void registrationWithoutRobot() {
		driver.get(RegistrationPageObject.Registration_URL);
		regPage.typeRegFirstname("Mirjana");
		regPage.typeRegLastname("Maric");
		regPage.typeRegEmail("mm2@yahoo.com");
		regPage.typeRegPassword("Maric123");
		regPage.typeRegWebsite("www.mmaric.com");
		regPage.selectRegCountry("Serbia");
		regPage.clickRegistration();
		
		Assert.assertEquals(regPage.withoutRobot(), "Please validate reCaptcha");	
	}
	
//	Testirati da li radi forma za registraciju unosom podataka za jednog korisnika. - Zgog robota mozda nece proci test
	@Test (priority=6)
	public void registrationOneUser() {
		driver.get(RegistrationPageObject.Registration_URL);
		regPage.typeRegFirstname("Mirjana");
		regPage.typeRegLastname("Maric");
		regPage.typeRegEmail("mm1@yahoo.com");
		regPage.typeRegPassword("Maric123");
		regPage.typeRegWebsite("www.mmaric1.com");
		regPage.selectRegCountry("Serbia");
		regPage.NotRobotReg();
		regPage.clickRegistration();
		regPage.choosePhysicalProducts();
		regPage.clickCreateAccount();
		
		Assert.assertEquals(regPage.messageRegistrationCorrect(), "Thank you for creating a 2Checkout Account!");	
	}
	
	

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

}
