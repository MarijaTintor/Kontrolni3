package finalniProjekat;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {

	// Lokatori

	public static final String userName = "user-name";
	public static final String pass = "password";
	public static final String loginButton = "login-button";
	public static final String errorLogin = "//*[@id=\"login_button_container\"]/div/form/div[3]/h3";

	// Metode

	public static void inputUsername(WebDriver driver, String username) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id(userName)).sendKeys(username);
	}

	public static void inputPassword(WebDriver driver, String password) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id(pass)).sendKeys(password);
	}

	public static void clickLoginButton(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id(loginButton)).click();
	}

	public static WebElement getErrorLogin(WebDriver driver) {
		return driver.findElement(By.xpath(errorLogin));
	}

}
