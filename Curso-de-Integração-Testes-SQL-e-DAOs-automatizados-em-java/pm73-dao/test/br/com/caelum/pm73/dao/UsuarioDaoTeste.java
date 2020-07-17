package br.com.caelum.pm73.dao;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import br.com.caelum.pm73.dominio.Usuario;

public class UsuarioDaoTeste {
	
	private Session session;
	private UsuarioDao usuarioDao;

	@Before
	public void setUp() {
		session = new CriadorDeSessao().getSession();
		usuarioDao = new UsuarioDao(session);
		
	}
	
	@After
	public void setDown() {
		session.close();
	}
	
	@Test
	public void deveEncontrarPeloNomeEEmail() {
		usuarioDao.salvar(new Usuario("Jo�o da Silva", "joao@dasilva.com.br"));
		
		Usuario usuario = usuarioDao.porNomeEEmail("Jo�o da Silva", "joao@dasilva.com.br");
		
		assertEquals("Jo�o da Silva", usuario.getNome());
		assertEquals("joao@dasilva.com.br",usuario.getEmail());
	}
	
	@Test
	public void deveRetornaNuloQuandoNaoExistir() {
		
		Usuario usuario = usuarioDao.porNomeEEmail("Jo�o da Silva", "joao@dasilva.com.br");
		
		assertNull(usuario);
	}

}
