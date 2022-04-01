package br.com.alura.leilao.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageLogin {
	
	private WebDriver browser;
	private static final String URL_LOGIN = "http://localhost:8080/login";
	
	public PageLogin() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		this.browser = new ChromeDriver();
		browser.navigate().to(URL_LOGIN);
	}

	public void fechar() {
		browser.quit();
	}
	
}
