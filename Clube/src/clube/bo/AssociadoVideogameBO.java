package clube.bo;

import java.util.List;

import clube.dao.AssociadoVideogameDAO;
import clube.model.AssociadoVideogame;

public class AssociadoVideogameBO {

	final static AssociadoVideogameDAO dao = new AssociadoVideogameDAO();

	public void salvar(AssociadoVideogame associadoVideogame) {
		dao.salvar(associadoVideogame);
	}

	public void remover(Long id) {
		dao.remover(id);
	}

	public List<AssociadoVideogame> listar() {
		return dao.listarTudo();
	}
}
