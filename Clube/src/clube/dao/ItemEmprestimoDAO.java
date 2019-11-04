package clube.dao;

import clube.model.ItemEmprestimo;
import util.dao.GenericDAO;

public class ItemEmprestimoDAO extends GenericDAO<ItemEmprestimo, Long> {

	public ItemEmprestimoDAO() {
		super(ItemEmprestimo.class);
	}

	public ItemEmprestimo encontrarPorId_fullList(Long id) {
		return this.getEntityManager().createNamedQuery("ItemEmprestimo.encontrarPorId_fullList", ItemEmprestimo.class)
				.setParameter("id", id).getSingleResult();
	}

}
