package clube.service;

import java.io.Serializable;
import java.util.List;

import clube.model.Associado;
import clube.model.AssociadoVideogameJogo;
import clube.model.Emprestimo;
import clube.model.Videogame;

public interface IClubeService extends Serializable {
	
	// falta complementar as assinaturas, exceptions, etc
	
	public void emprestimo();
	
	public void devolucao();
	
	public void salvarAssociado(Associado associado);
	
	public void salvarEmprestimo(Emprestimo emprestimo);
	
	public void removerAssociado(Long id);
	
	public void salvarVideogame(Videogame videogame);
	
	public void removerVideogame(Long id);
	
	public List<Videogame> listarVideogames();

	public List<Associado> listarAssociadosPorNome(String nomeAssociado);
	
	public Associado encontrarAssociadoPorId_fullList(Long id);
	
	public Videogame encontrarPorId(Long id);

	public List<Associado> buscarLocatariosPorNome(String nomeLocatario);

	public List<Emprestimo> buscarEmprestimoPorLocatario(String nomeLocatario);

	public List<AssociadoVideogameJogo> buscarJogosPorNome(String nomeJogo);
	

}
