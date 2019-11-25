package clube.dao;

import clube.model.AssociadoVideogame;
import util.dao.GenericDAO;

public class AssociadoVideogameDAO extends GenericDAO<AssociadoVideogame, Long> {

	public AssociadoVideogameDAO() {
		super(AssociadoVideogame.class);
	}
}
