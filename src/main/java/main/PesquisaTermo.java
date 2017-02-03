package main;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PesquisaTermo {

	private String termo;
	private WebDriver webdriver;
	private Wait wait;
	

	public WebDriver getWebdriver() {
		return webdriver;
	}

	public void setWebdriver(WebDriver webdriver) {
		this.webdriver = webdriver;
	}
	
	public Wait getWait() {
		return wait;
	}

	public void setWait(Wait wait) {
		this.wait = wait;
	}
	
	public void pesquisaTermo(String termo) {
		
		this.setTermo(termo);

		try {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			setWebdriver(new ChromeDriver(options));
			getWebdriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			wait = new WebDriverWait(getWebdriver(), 10);

			getWebdriver().get("https://www.google.com.br/");
			getWait().until(ExpectedConditions.titleContains("Google"));

			getWebdriver().findElement(By.id("lst-ib")).sendKeys(termo);
			getWait().until(ExpectedConditions.elementToBeClickable((By.id("_fZl"))));

			getWebdriver().findElement(By.id("_fZl")).click();

		} catch (Throwable t) {

			t.printStackTrace();
		}
		return;
	}

	public String getTermo() {
		return termo;
	}

	public void setTermo(String termo) {
		this.termo = termo;
	}

}
