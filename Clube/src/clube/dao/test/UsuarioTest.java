package clube.dao.test;

import org.junit.Test;

import clube.dao.UsuarioDAO;
import clube.model.security.Usuario;
import util.MD5Util;

public class UsuarioTest {

	UsuarioDAO dao = new UsuarioDAO();

	@Test
	public void populate() {dfgfdhshg

		Usuario u = new Usuario("admin", MD5Util.gerarHashMD5("admin"));
		u.addPapel("ADMINISTRADOR");

		dao.salvar(u);

		u = new Usuario("user", MD5Util.gerarHashMD5("user"));
		u.addPapel("USUARIO");

		dao.salvar(u);
	}
}
