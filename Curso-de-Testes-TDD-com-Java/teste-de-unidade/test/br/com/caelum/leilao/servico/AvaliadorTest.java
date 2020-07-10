package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
		
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		
		
	}
	
	@Test
	public void deveCalcularAMedia() {
		 // cenario: 3 lances em ordem crescente
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Play 4");

        leilao.propoe(new Lance(maria,500.0));
        leilao.propoe(new Lance(joao,400.0));
        leilao.propoe(new Lance(jose,300.0));

        // executando a acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);
        
        // comparando a saida com o esperado
        assertEquals(400, leiloeiro.getMedia(), 0.0001);
	}
	
	@Test
	public void testaMediaDeZeroLance() {
		
		//cenario : 0 lance
		
		Leilao leilao = new Leilao("Play 5");
		
		// executando a acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		// comparando a saida com o esperado
		assertEquals(0, leiloeiro.getMedia(), 0.0001);
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		//cenario : apenas um lance
		Usuario joao = new Usuario("João");
		Leilao leilao = new Leilao("Xbox 360");
		
		leilao.propoe(new Lance(joao, 1000.0));
		
		// executando a acao
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		//comparando a saida com o esperado
		assertEquals(1000, avaliador.getMaiorLance(), 0.00001);
		assertEquals(1000, avaliador.getMenorLance(), 0.00001);
		
	}
	
	@Test
	public void deveEntenderValoresRandomicos() {
		//cenario: valores aleatorios de lance
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Usuario sam = new Usuario("Sam");
		
		Leilao leilao = new Leilao("Caixa magica");
		
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(maria, 450.0));
		leilao.propoe(new Lance(joao, 120.0));
		leilao.propoe(new Lance(sam, 700.0));
		leilao.propoe(new Lance(joao, 630.0));
		leilao.propoe(new Lance(maria, 230.0));
		
		//executando a acao
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		//comparando a saida com o esperado
		assertEquals(120, avaliador.getMenorLance(),0.00001);
		assertEquals(700, avaliador.getMaiorLance(),0.00001);
	}


	@Test
	public void deveEncontrarOsTresMaioresLances() {
		// cenario : 3 maiores lances
		Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(maria, 400.0));
        
        //executando acao

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        
        //comparando a saida com o esperado

        assertEquals(3, maiores.size());
        assertEquals(400, maiores.get(0).getValor(), 0.00001);
        assertEquals(300, maiores.get(1).getValor(), 0.00001);
        assertEquals(200, maiores.get(2).getValor(), 0.00001);
	}
	
	@Test
    public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(2, maiores.size());
        assertEquals(200, maiores.get(0).getValor(), 0.00001);
        assertEquals(100, maiores.get(1).getValor(), 0.00001);
    }
	
	@Test
    public void deveDevolverListaVaziaCasoNaoHajaLances() {
        Leilao leilao = new Leilao("Playstation 3 Novo");

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(0, maiores.size());
    }
























}
