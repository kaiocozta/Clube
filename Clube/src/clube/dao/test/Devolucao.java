package clube.dao.test;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import clube.dao.AssociadoDAO;
import clube.dao.AssociadoVideogameJogoDAO;
import clube.dao.EmprestimoDAO;
import clube.model.Emprestimo;
import clube.model.ItemEmprestimo;

public class Devolucao {

	AssociadoDAO associadoDAO = new AssociadoDAO();
	EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
	AssociadoVideogameJogoDAO associadoVideogameJogoDAO = new AssociadoVideogameJogoDAO();

	@Test
	public void TesteSalvar() {
		Date dataEmprestimo = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, Calendar.NOVEMBER, 18);
		Date dataDevolucao = calendar.getTime();

		Emprestimo emprestimo = new Emprestimo(associadoDAO.encontrar(1l), dataEmprestimo);
		emprestimoDAO.salvar(emprestimo);

		ItemEmprestimo itemEmprestimo = new ItemEmprestimo(emprestimo, associadoVideogameJogoDAO.encontrar(2l),
				dataDevolucao);

		emprestimo.addJogo(itemEmprestimo);
		emprestimoDAO.salvar(emprestimo);

	}
}
