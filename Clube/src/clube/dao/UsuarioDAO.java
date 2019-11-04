package clube.dao;

import clube.model.security.Usuario;
import util.dao.GenericDAO;

public class UsuarioDAO extends GenericDAO<Usuario, Long> {
	public UsuarioDAO() {
		super(Usuario.class);
	}
}
