package clube.dao;

import javax.persistence.Query;

import clube.model.Associado;
import util.dao.GenericDAO;

public class AssociadoDAO extends GenericDAO<Associado, Long> {

	public AssociadoDAO() {
		super(Associado.class);
	}
	
	public Associado encontrarPorId_fullList(Long id) {
		return this.getEntityManager().createNamedQuery("Associado.encontrarPorId_fullList", Associado.class)
				.setParameter("id", id).getSingleResult();

	}
	
	public Associado buscarPorNomeDeUsuario(String nomeUsuario) {
		
		
		Query query = this.entityManager.createNamedQuery("Associado.buscarPorNomeDeUsuario", Associado.class)
				.setParameter("nomeUsuario", nomeUsuario);	
		return (Associado) query.getSingleResult();
	}
}
