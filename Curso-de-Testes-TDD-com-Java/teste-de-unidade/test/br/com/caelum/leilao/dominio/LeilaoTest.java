package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;

public class LeilaoTest {
	
	private Usuario steveJobs;
	private Usuario steveWozniak;
	private Usuario billGates;
	
	@Before
	public void setUp() {
		this.steveJobs = new Usuario("Steve Jobs");
		this.steveWozniak = new Usuario("Steve Wozniak");
		this.billGates = new Usuario("Bill Gates");
	}
	
	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new CriadorDeLeilao().para("MacBook pro 15").constroi();
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(steveJobs, 2000));
		
		assertEquals(1,leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(),0.00001);
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("MacBook pro 15");
		leilao.propoe(new Lance(steveJobs, 2000.0));
		leilao.propoe(new Lance(steveWozniak, 3000.0));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(),0.00001);
		assertEquals(3000.0, leilao.getLances().get(1).getValor(),0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesDoMesmoUsuario() {
		Leilao leilao = new Leilao("MacBook pro 15");
		
		leilao.propoe(new Lance(steveJobs, 2000.0));
		leilao.propoe(new Lance(steveJobs, 3000.0));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(),0.00001);
		
	}
	
	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Leilao leilao = new Leilao("MacBook Pro 15"); 
		
		leilao.propoe(new Lance(steveJobs, 2000.0));
		leilao.propoe(new Lance(billGates, 3000.0));
		
		leilao.propoe(new Lance(steveJobs, 4000.0));
		leilao.propoe(new Lance(billGates, 5000.0));
		
		leilao.propoe(new Lance(steveJobs, 6000.0));
		leilao.propoe(new Lance(billGates, 7000.0));
		
		leilao.propoe(new Lance(steveJobs, 8000.0));
		leilao.propoe(new Lance(billGates, 9000.0));
		
		leilao.propoe(new Lance(steveJobs, 10000.0));
		leilao.propoe(new Lance(billGates, 11000.0));
		
		//deve ser ignorado
		leilao.propoe(new Lance(steveJobs, 12000.0));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(11000, leilao.getLances().get(leilao.getLances().size()-1).getValor(),0.00001);
		
	}
	
	@Test
	public void deveDobrarOUltimoLanceDado() {
		Leilao leilao = new Leilao("MacBook Pro 15");
		
		leilao.propoe(new Lance(steveJobs, 2000.0));
		leilao.propoe(new Lance(billGates, 3000.0));
		leilao.dobraLance(steveJobs);
		
		assertEquals(4000.0, leilao.getLances().get(2).getValor(),0.00001);
	}
	
	@Test
	public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		
		leilao.dobraLance(steveJobs);
		
		assertEquals(0, leilao.getLances().size());
	}

}
