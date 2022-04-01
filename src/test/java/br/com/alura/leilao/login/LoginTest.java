package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class LoginTest {
	
	private PageLogin paginaDeLogin;
	
	@BeforeEach
	public void BeforeEach() {
		this.paginaDeLogin = new PageLogin();
	}
	
	@AfterEach
	public void afterEach() {
		this.paginaDeLogin.fechar();
	}
	
	@Test
	public void deveriaEfetuarloginComDadosValidos() {
		efetuarLogin("fulano", "pass");
		String usuarioLogado = browser.findElement(By.id("usuario-logado")).getText(); // recupera o texto do elemento
		
		Assert.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
		Assert.assertEquals("fulano", usuarioLogado);
	
	}
	
	@Test
	public void deveriaEfetuarloginComDadosInvalidos() {
		efetuarLogin("invalido", "pass123");
		
//		browser.findElement(By.id("username")).sendKeys("invalido"); // envia os dados ao input
//		browser.findElement(By.id("password")).sendKeys("pass123"); 
//		browser.findElement(By.id("login-form")).submit(); // submeter form
		
		// String pagLogin = "http://localhost:8080/login?error";
		
		Assert.assertTrue(browser.getCurrentUrl().equals(URL_LOGIN + "?error")); // permaneceu na tela de login 
		Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
		Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado"))); // validando exception 

	}
	
	@Test
	public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
	    this.browser.navigate().to("http://localhost:8080/leiloes/2");
		Assert.assertTrue(browser.getCurrentUrl().equals(URL_LOGIN));
		
		Assert.assertFalse(browser.getPageSource().contains("Dados do Leilão"));
	}
	
	public void efetuarLogin(String username, String password) {
		browser.findElement(By.id("username")).sendKeys(username); // envia os dados ao input
		browser.findElement(By.id("password")).sendKeys(password); 
		browser.findElement(By.id("login-form")).submit(); // submeter form
	}

}
