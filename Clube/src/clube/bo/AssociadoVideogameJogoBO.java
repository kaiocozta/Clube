package clube.bo;

import java.util.List;

import clube.dao.AssociadoVideogameJogoDAO;
import clube.model.AssociadoVideogameJogo;

public class AssociadoVideogameJogoBO {

	final static AssociadoVideogameJogoDAO dao = new AssociadoVideogameJogoDAO();

	public void salvar(AssociadoVideogameJogo associado) {
		dao.salvar(associado);
	}

	public void remover(Long id) {
		dao.remover(id);
	}

	public AssociadoVideogameJogo encontrarPorId_FullList(Long id) {
		return dao.encontrarPorId_fullList(id);
	}

	// FIXME implementar busca por nome
	public List<AssociadoVideogameJogo> listarPorNome(String nome) {
		return dao.listarTudo();
	}

	public List<AssociadoVideogameJogo> listar() {
		return dao.listarTudo();
	}
}
