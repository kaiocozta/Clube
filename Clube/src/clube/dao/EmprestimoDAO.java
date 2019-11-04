package clube.dao;

import clube.model.Emprestimo;
import util.dao.GenericDAO;

public class EmprestimoDAO extends GenericDAO<Emprestimo, Long> {

	public EmprestimoDAO() {
		super(Emprestimo.class);
	}
	
	public Emprestimo encontrarPorId_fullList(Long id) {
		return this.getEntityManager().createNamedQuery("Emprestimo.encontrarPorId_fullList", Emprestimo.class)
				.setParameter("id", id).getSingleResult();
	}

}
