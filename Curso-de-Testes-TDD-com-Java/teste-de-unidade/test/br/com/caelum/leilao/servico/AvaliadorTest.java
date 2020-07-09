package br.com.caelum.leilao.servico;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// parte 1: cenario
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("Jóse");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Play 4");
		
		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		// parte 2: executando a acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		// parte 3: comparando a saida com o esperado
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		
		
	}
	
	@Test
	public void deveCalcularAMedia() {
		 // cenario: 3 lances em ordem crescente
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Play 4");

        leilao.propoe(new Lance(maria,300.0));
        leilao.propoe(new Lance(joao,400.0));
        leilao.propoe(new Lance(jose,500.0));

        // executando a acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);
        
        // comparando a saida com o esperado
        Assert.assertEquals(400, leiloeiro.getMedia(), 0.0001);
	}
	
	@Test
	public void testaMediaDeZeroLance() {
		
		//cenario : 0 lance
		
		Leilao leilao = new Leilao("Play 5");
		
		// executando a acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		// comparando a saida com o esperado
		Assert.assertEquals(0, leiloeiro.getMedia(), 0.0001);
	}
}
