package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.alura.leilao.PageObject;

public class LeiloesPage extends PageObject {

	// Padrão Page Object 

	private WebDriver browser;
	
	private static final String URL_LIST = "http://localhost:8080/leiloes";
	private static final String URL_FORM = "http://localhost:8080/leiloes/new";

	public LeiloesPage(WebDriver browser) {
		super(browser);
	}
	
	public CadastroLeilaoPage carregarFormulario() {
		this.browser.navigate().to(URL_FORM);
		return new CadastroLeilaoPage(browser); // mantem pag aberto  
	}

	public boolean isLeilaoCadastrado(String nome, String valor, String data) {
	    WebElement linhaDaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
	    WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
	    WebElement colunaDataAbertura = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
	    WebElement colunaValorInicial = linhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));

	    return colunaNome.getText().equals(nome)
	            && colunaDataAbertura.getText().equals(data) 
	            && colunaValorInicial.getText().equals(valor);
	}

	public boolean isPaginaAtual() {
		return browser.getCurrentUrl().equals(URL_LIST);
	}
	
}
