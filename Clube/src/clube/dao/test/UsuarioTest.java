package clube.dao.test;

import org.junit.Test;

import clube.dao.AssociadoDAO;
import clube.dao.UsuarioDAO;
import clube.model.Associado;
import clube.model.security.Usuario;
import util.MD5Util;

public class UsuarioTest {

	UsuarioDAO daoUsuario = new UsuarioDAO();
	AssociadoDAO daoAssociado = new AssociadoDAO();
	
	@Test
	public void populate() {
		Usuario u = new Usuario("admin", MD5Util.gerarHashMD5("admin"));
		u.addPapel("ADMINISTRADOR");

		daoUsuario.salvar(u);

		u = new Usuario("user", MD5Util.gerarHashMD5("user"));
		u.addPapel("USUARIO");

		daoUsuario.salvar(u);
	}
	
	
	@Test
	public void salvarUsuarioAssociado() {
		Usuario usuario = new Usuario("teste99", MD5Util.gerarHashMD5("teste99"));
		usuario.addPapel("USUARIO");
		Associado associado = new Associado("teste99", "67 97646-6461", "rua ddaf");
		associado.setUsuario(usuario);
		daoAssociado.salvar(associado);
	}
	
	@Test
	public void buscarAssociadoPorNomeDeUsuario() {
		String n = daoAssociado.buscarPorNomeDeUsuario("kaiocosta").getNome();
		System.out.println(n);
	}
	
	@Test
	public void criarUser() {
//		Usuario usuario = new Usuario();
//		System.out.println("passou");
//		daoUsuario.salvar(usuario);
		
	}

}
