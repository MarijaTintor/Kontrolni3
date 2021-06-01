package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import finalniProjekat.Login;

public class TestLogin {

	/*
	 * Test Selenium Zadatak Napisati program na programskom jeziku Java koji sluzi
	 * za testiranje sajta https://www.saucedemo.com/ Pokusati logovanje prvo sa
	 * nevalidnim, a potom sa validnim kredencijalima i proveriti da li se nakon
	 * toga korisnik nalazi na odgovarajucoj staranici. Na stranici
	 * https://www.saucedemo.com/inventory.html sortirati proizvode po ceni (od
	 * najnize ka najvisoj). Proveriti da li je sortiranje ispravno. Program pisati
	 * postujuci page object model. Koristiti Test NG za proveru ispravnosti
	 * testova. Kredencijale procitati iz datoteke data.xlsx. Resenje okaciti na
	 * GitHub nalog i svoj folder na google drive-u (napraviti folder pod nazivom
	 * kontrolni3).
	 */

	private static WebDriver driver;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "D:\\Programs\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterClass
	public void closeDriver() {
		driver.close();
	}

	@Test
	public void testValidCredentials() {
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();

		Login.inputUsername(driver, "maliMrav");
		Login.inputPassword(driver, "D8iGTr");
		Login.clickLoginButton(driver);

		Login.inputUsername(driver, "standard_user");
		Login.inputPassword(driver, "secret_sauce");
		Login.clickLoginButton(driver);

		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://www.saucedemo.com/inventory.html";

		SoftAssert sa = new SoftAssert();

		sa.assertEquals(actualUrl, expectedUrl);

	}

	@Test
	public void testCredentials() {
		File f = new File("Data.xlsx");
		try {
			InputStream is = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(is);
			Sheet sheet = wb.getSheetAt(0);

			SoftAssert sa = new SoftAssert();

			for (int i = 0; i < 2; i++) {

				driver.get("https://www.saucedemo.com/");

				Row row = sheet.getRow(i);
				Cell c0 = row.getCell(0);
				Cell c1 = row.getCell(1);

				String username = c0.toString();
				String password = c1.toString();

				Login.inputUsername(driver, username);
				Login.inputPassword(driver, password);
				Login.clickLoginButton(driver);

				String actualUrl = driver.getCurrentUrl();
				String expectedUrl = "https://www.saucedemo.com/inventory.html";

				sa.assertEquals(actualUrl, expectedUrl);

			}

			sa.assertAll();
			wb.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
