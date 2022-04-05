package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.lance.LancesPage;

public class LoginTest {
	
	private LoginPage paginaDeLogin;
	
	@BeforeEach
	public void BeforeEach() {
		this.paginaDeLogin = new LoginPage();
	}
	
	@AfterEach
	public void afterEach() {
		this.paginaDeLogin.fechar();
	}
	
	@Test
	public void deveriaEfetuarloginComDadosValidos() {
		paginaDeLogin.preencherFormularioDeLogin("fulano", "pass");
		// paginaDeLogin.efetuaLogin();

	
		Assert.assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
		Assert.assertFalse(paginaDeLogin.isPaginaAtual());
	}
	
	@Test
	public void deveriaEfetuarloginComDadosInvalidos() {
		paginaDeLogin.preencherFormularioDeLogin("invalido", "pass123");
		paginaDeLogin.efetuaLogin();
		
		Assert.assertTrue(paginaDeLogin.isPaginaDeLoginComDadosInvalidos());
		Assert.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
		Assert.assertNull(paginaDeLogin.getNomeUsuarioLogado()); // validando exception 
	}
	
	@Test
	public void naoDeveriaAcessarUrlRestritaSemEstarLogado() {
		LancesPage paginaDeLances = new LancesPage();

		Assert.assertFalse(paginaDeLances.isPaginaAtual());
		Assert.assertFalse(paginaDeLances.isTituloLeilaoVisivel());

		// paginaDeLances.fechar();
	}
	

}
