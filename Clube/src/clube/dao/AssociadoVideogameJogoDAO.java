package clube.dao;

import clube.model.AssociadoVideogameJogo;
import util.dao.GenericDAO;

public class AssociadoVideogameJogoDAO extends GenericDAO<AssociadoVideogameJogo, Long> {
	public AssociadoVideogameJogoDAO() {
		super(AssociadoVideogameJogo.class);
	}

	public AssociadoVideogameJogo encontrarPorId_fullList(Long id) {
		return this.getEntityManager()
				.createNamedQuery("AssociadoVideogameJogo.encontrarPorId_fullList", AssociadoVideogameJogo.class)
				.setParameter("id", id).getSingleResult();

	}

}
