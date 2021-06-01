package finalniProjekat;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Inventory {

	// Lokatori
	public static final String Url = "https://www.saucedemo.com/inventory.html";
	public static final String Filter = ".product_sort_container";
	public static final String Lohi = "//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[3]";
	public static final String Inventory = ".inventory_item_price";

	// Metode

	public static void clickOnFilter(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector(Filter)).click();
	}

	public static void clickOnLohi(WebDriver driver) {
		driver.findElement(By.xpath(Lohi)).click();
	}

	public static boolean checkIfNumbersAreFromLowToHigh(WebDriver driver) {
		List<WebElement> list = driver.findElements(By.cssSelector(Inventory));
		for (int i = 0; i < (list.size() - 1); i++) {
			WebElement price1 = list.get(i);
			Double priceIteam1 = Double.parseDouble(price1.getText().substring(1));

			WebElement price2 = list.get(i + 1);
			Double priceIteam2 = Double.parseDouble(price1.getText().substring(1));

			if (priceIteam1 > priceIteam2) {
				return false;
			}

		}
		return true;
	}
}
