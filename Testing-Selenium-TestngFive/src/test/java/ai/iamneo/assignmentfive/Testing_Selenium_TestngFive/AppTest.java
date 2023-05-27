package ai.iamneo.assignmentfive.Testing_Selenium_TestngFive;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;

public class AppTest {
	WebDriver driver;

	@BeforeSuite
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.port", "8080");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void testSignInButton() {
		driver.get("http://www.snapdeal.com");

		WebElement signInButton = driver.findElement(By.xpath("//div[@class='accountInner']"));
		signInButton.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement loginButton = driver.findElement(By.xpath("//a[contains(text(),'login')]"));
		loginButton.click();
	}

	@Test
	public void inputButton() {
		driver.switchTo().frame("loginIframe");
		WebElement element = driver.findElement(By.name("username"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value='babisshyamanoharan@gmail.com';", element);

		WebElement continueButton = driver.findElement(By.xpath("//*[@id='checkUser']"));
		continueButton.click();
		Scanner scanner_user = new Scanner(System.in);
		System.out.println("Enter the OTP: ");
		String user = scanner_user.nextLine();
		driver.findElement(By.xpath("//*[@id=\"loginOtpUC\"]/div[1]/input")).sendKeys(user);
		driver.findElement(By.xpath("//*[@id=\"loginUsingOtp\"]")).click();

	}

	@AfterMethod
	public void verifyAccountCreation() {
		List<WebElement> successElements = driver.findElements(By.xpath("//*[@id=\"sdHeader\"]/div[4]/div[2]"));
		if (!successElements.isEmpty()) {
			System.out.println("The account is logged in successfully");
		} else {
			System.out.println("The account is not logged in successfully");

		}
		
	}
}
