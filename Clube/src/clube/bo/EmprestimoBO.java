package clube.bo;

import java.util.List;

import clube.dao.EmprestimoDAO;
import clube.model.Emprestimo;

public class EmprestimoBO {
	final static EmprestimoDAO dao = new EmprestimoDAO();

	public void salvar(Emprestimo emprestimo) {
		dao.salvar(emprestimo);
	}

	public void remover(Long id) {
		dao.remover(id);
	}

	public Emprestimo encontrarPorId_FullList(Long id) {
		return dao.encontrarPorId_fullList(id);
	}

	public List<Emprestimo> listar() {
		return dao.listarTudo();
	}
	
	// FIXME implementar busca bor locatario
	public List<Emprestimo> buscarEmprestimoPoLocatario(String nomeLocatario) {
		return dao.listarTudo();
	}
}
