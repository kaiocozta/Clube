package clube.dao.test;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import clube.dao.AssociadoDAO;
import clube.dao.AssociadoVideogameJogoDAO;
import clube.dao.EmprestimoDAO;
import clube.dao.ItemEmprestimoDAO;
import clube.model.AssociadoVideogameJogo;
import clube.model.Emprestimo;
import clube.model.ItemEmprestimo;

public class EmprestimoTest {

	ItemEmprestimoDAO itemEmprestimoDAO = new ItemEmprestimoDAO();
	AssociadoDAO associadoDAO = new AssociadoDAO();
	EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
	AssociadoVideogameJogoDAO associadoVideogameJogoDAO = new AssociadoVideogameJogoDAO();

	@Test
	public void TesteSalvar() {
		Date dataEmprestimo = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, Calendar.NOVEMBER, 10);
		Date dataDevolucao = calendar.getTime();
		
		Emprestimo emprestimo = new Emprestimo(associadoDAO.encontrar(2l), dataEmprestimo);
		emprestimoDAO.salvar(emprestimo);

		ItemEmprestimo itemEmprestimo = new ItemEmprestimo(emprestimo, associadoVideogameJogoDAO.encontrar(1l),
				dataDevolucao);

		emprestimo.addJogo(itemEmprestimo);
		emprestimoDAO.salvar(emprestimo);

	}
}
