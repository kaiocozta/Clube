package clube.dao.test;

import org.junit.Test;

import clube.dao.AssociadoDAO;
import clube.dao.AssociadoVideogameDAO;
import clube.model.Associado;

public class VideogameTest {

	AssociadoVideogameDAO associadoVideogameDAO = new AssociadoVideogameDAO();
	AssociadoDAO associadoDAO = new AssociadoDAO();
	
	@Test
	public void excluirAssociadoVideogame() {
//		associadoVideogameDAO.remover(8l);
		Associado associado = new Associado();
		associado.removeVideogame(associadoVideogameDAO.encontrar(7l));
	}
}
