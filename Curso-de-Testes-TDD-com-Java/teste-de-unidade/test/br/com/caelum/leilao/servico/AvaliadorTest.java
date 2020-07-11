package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {
	
	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;
	
	@Before
	public void setUp() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("Joao");
		this.jose = new Usuario("Jóse");
		this.maria = new Usuario("Maria");
		
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		Leilao leilao = new CriadorDeLeilao().para("Play 4")
				.lance(joao,250.0)
				.lance(jose,300.0)
				.lance(maria,400.0)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		// parte 3: comparando a saida com o esperado
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		
		
	}

	
	@Test
	public void deveCalcularAMedia() {
        Leilao leilao = new CriadorDeLeilao().para("Play 4")
        		.lance(maria, 500.0)
        		.lance(joao, 400.0)
        		.lance(jose, 300.0)
        		.constroi();

        leiloeiro.avalia(leilao);
        
        // comparando a saida com o esperado
        assertEquals(400, leiloeiro.getMedia(), 0.0001);
	}
	
	@Test
	public void testaMediaDeZeroLance() {
		
		
		Leilao leilao = new Leilao("Play 5");
		
		// executando a acao
		leiloeiro.avalia(leilao);
		
		// comparando a saida com o esperado
		assertEquals(0, leiloeiro.getMedia(), 0.0001);
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		//cenario : apenas um lance
		Leilao leilao = new CriadorDeLeilao().para("xbox 360")
				.lance(joao, 1000.0)
				.constroi();
		
		// executando a acao
		leiloeiro.avalia(leilao);
		
		//comparando a saida com o esperado
		assertEquals(1000, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000, leiloeiro.getMenorLance(), 0.00001);
		
	}
	
	@Test
	public void deveEntenderValoresRandomicos() {		
		Leilao leilao = new CriadorDeLeilao().para("Caixa magina")
				.lance(joao, 200.0)
				.lance(maria, 450.0)
				.lance(joao, 120.0)
				.lance(jose, 700.0)
				.lance(joao, 630.0)
				.lance(maria, 230.0)
				.constroi();
		
		//executando a acao
		leiloeiro.avalia(leilao);
		
		//comparando a saida com o esperado
		assertEquals(120, leiloeiro.getMenorLance(),0.00001);
		assertEquals(700, leiloeiro.getMaiorLance(),0.00001);
	}


	@Test
	public void deveEncontrarOsTresMaioresLances() {
		// cenario : 3 maiores lances
		Leilao leilao = new CriadorDeLeilao().para("Caixa magina")
	        .lance(joao, 100.0)
	        .lance(maria, 200.0)
	        .lance(joao, 300.0)
	        .lance(maria, 400.0)
	        .constroi();
        
        //executando acao

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
		Leilao leilao = new CriadorDeLeilao().para("Caixa magina")
				.lance(joao, 100.0)
        		.lance(maria, 200.0)
        		.constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(2, maiores.size());
        assertEquals(200, maiores.get(0).getValor(), 0.00001);
        assertEquals(100, maiores.get(1).getValor(), 0.00001);
    }
	
	@Test
    public void deveDevolverListaVaziaCasoNaoHajaLances() {
		Leilao leilao = new CriadorDeLeilao().para("Caixa magina")
				.constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(0, maiores.size());
    }
























}
