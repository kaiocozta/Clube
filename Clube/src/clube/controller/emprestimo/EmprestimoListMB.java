package clube.controller.emprestimo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import clube.model.Emprestimo;
import clube.service.ClubeService;
import clube.service.IClubeService;

@ManagedBean
@ViewScoped
public class EmprestimoListMB implements Serializable {

	private static final long serialVersionUID = 8953220697722151516L;

	private IClubeService service = ClubeService.getInstance();
	
	private String nomeLocatario;
	private List<Emprestimo> emprestimos;

	@PostConstruct
	public void init() {
		this.emprestimos = new ArrayList<Emprestimo>();
	}

	public String buscar() {
		this.emprestimos = service.buscarEmprestimoPorLocatario("");
		return null;
	}

	public String novo() {
		return "form";
	}
	
	public String editar() {
		return "form";
	}

	public String getNomeLocatario() {
		return nomeLocatario;
	}

	public void setNomeLocatario(String nomeLocatario) {
		this.nomeLocatario = nomeLocatario;
	}

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}
}
