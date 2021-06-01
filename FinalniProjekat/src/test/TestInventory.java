package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import finalniProjekat.Inventory;
import finalniProjekat.Login;

public class TestInventory {

	private static WebDriver driver;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "D:\\Programs\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterClass
	public void closeDriver() {
		driver.close();
	}

	@Test
	public void testSortInventoryFromLowToHigh() {
		driver.get("https://www.saucedemo.com");
		Login.inputUsername(driver, "standard_user");
		Login.inputPassword(driver, "secret_sauce");
		Login.clickLoginButton(driver);

		Inventory.clickOnFilter(driver);
		Inventory.clickOnLohi(driver);
		Assert.assertEquals(Inventory.checkIfNumbersAreFromLowToHigh(driver), true);
	}
}
