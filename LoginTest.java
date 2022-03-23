package hw4;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginTest {

	static WebDriver driver;

//	Change your selenium driver path here
	static String pathChromeDriver="/*Place holder for the file path of chrome driver*/";
	static String pathLoginPage="/*Place holder for the file path of login page html page*/";

	String txtFirstName="txtFirstName";
	String txtLastName="txtLastName";
	String txtEmail="txtEmail";
	String txtPhone="txtPhone";
	String txtAddress="txtAddress";
	String btnValidate="btnValidate";
	String txtMessageLogin="txtMessageLogin";

	@BeforeClass
	public static void openBrowser()
	{
		System.setProperty("webdriver.chrome.driver", pathChromeDriver);
		driver= new ChromeDriver() ;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterClass
	public static void closeBrowser() {
	driver.quit();
	}

	@Test
	public void loginSuccessTest() throws InterruptedException {
		driver.get(pathLoginPage);
		driver.manage().window().maximize();
driver.findElement(By.xpath("//input[@id='"+txtFirstName+"']")).sendKeys("Charlie");
	driver.findElement(By.xpath("//input[@id='"+txtLastName+"']")).sendKeys("Moreland");
	driver.findElement(By.xpath("//input[@id='"+txtEmail+"']")).sendKeys("cem13@iastate.edu");
	driver.findElement(By.xpath("//input[@id='"+txtPhone+"']")).sendKeys("319-331-2495");
	driver.findElement(By.xpath("//input[@id='"+txtAddress+"']")).sendKeys("Ames,IA");
	Select gender = new Select(driver.findElement(By.name("selectGender")));
	gender.selectByVisibleText("Male");
	Select state = new Select(driver.findElement(By.name("selectState")));
	state.selectByVisibleText("California");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='"+btnValidate+"']")).click();

		String strMessage=driver.findElement(By.xpath("//p[@id='"+"FinalResult"+"']")).getText();
		assertEquals("OK", strMessage);
	}

	@Test
	public void loginFailedTest() throws InterruptedException {
		driver.get(pathLoginPage);
		driver.manage().window().maximize();
driver.findElement(By.xpath("//input[@id='"+txtFirstName+"']")).sendKeys("Charlie");
	driver.findElement(By.xpath("//input[@id='"+txtLastName+"']")).sendKeys("Moreland");
	driver.findElement(By.xpath("//input[@id='"+txtEmail+"']")).sendKeys("cem13");
	driver.findElement(By.xpath("//input[@id='"+txtPhone+"']")).sendKeys("319");
	driver.findElement(By.xpath("//input[@id='"+txtAddress+"']")).sendKeys("Ames,IA");
	Select gender = new Select(driver.findElement(By.name("selectGender")));
	gender.selectByVisibleText("Male");
	Select state = new Select(driver.findElement(By.name("selectState")));
	state.selectByVisibleText("California");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='"+btnValidate+"']")).click();

		String strMessage=driver.findElement(By.xpath("//p[@id='"+"FinalResult"+"']")).getText();
		assertEquals("Error", strMessage);
	}




}
