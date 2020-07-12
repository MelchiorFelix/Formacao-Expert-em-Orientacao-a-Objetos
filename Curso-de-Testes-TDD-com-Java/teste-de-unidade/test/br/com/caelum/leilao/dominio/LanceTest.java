package br.com.caelum.leilao.dominio;

import org.junit.Test;

public class LanceTest {
	
	private Usuario joao;
	
	public void setUp() {
		joao = new Usuario("Jõão da Silva");
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void deveRecusarLancescomValorDeZero() {
		new Lance(joao, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deveRecusarLancesComValoresNegativos() {
		new Lance(joao, -10);
	}

}
