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
//		Usuario usuario = new Usuario("kaiocosta", MD5Util.gerarHashMD5("12345"));
//		usuario.addPapel("USUARIO");
//		daoUsuario.salvar(usuario);
		
		Associado associado = daoAssociado.encontrar(1l);
		Usuario usuario = associado.getUsuario();
		usuario.setSenha("54321");
		daoAssociado.salvar(associado);
	}

}
