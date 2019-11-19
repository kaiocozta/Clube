package clube.controller.associado;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import clube.model.Associado;
import clube.service.ClubeService;
import clube.service.IClubeService;

@ManagedBean
@ViewScoped
public class AssociadoListMB implements Serializable {

	private static final long serialVersionUID = 8953220697722151516L;

	private IClubeService service = ClubeService.getInstance();

	private Associado selectedAssociado;
	private String nomeAssociado;
	private List<Associado> associados;

	@PostConstruct
	public void init() {
		associados = new ArrayList<Associado>();
	}

	public String editar(Associado selectedAssociado) {
		Principal name = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		Boolean role = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ADMINISTRADOR");

		if (!role) {
			String message = "Erro: " + name.getName() + " não possui permissão para editar este item!";
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
			FacesContext.getCurrentInstance().addMessage("Erro", fm);
			return null;
		}

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("associado", selectedAssociado);
		
		return "form";
	}

	public String excluir(Associado selectedAssociado) {

		service.removerAssociado(selectedAssociado.getId());
		associados = service.listarAssociadosPorNome(nomeAssociado);

		FacesMessage fm = new FacesMessage("Associado excluído com sucesso!");
		FacesContext.getCurrentInstance().addMessage("Sucesso", fm);

		return null;
	}

	public String buscar() {

		associados = service.listarAssociadosPorNome(nomeAssociado);

		return null;
	}

	public String novo() {
		return "form";
	}

	// get and set
	public Associado getSelectedAssociado() {
		return selectedAssociado;
	}

	public void setSelectedAssociado(Associado selectedAssociado) {
		this.selectedAssociado = selectedAssociado;
	}

	public List<Associado> getAssociados() {
		return associados;
	}

	public void setAssociados(List<Associado> associados) {
		this.associados = associados;
	}

	public String getNomeAssociado() {
		return nomeAssociado;
	}

	public void setNomeAssociado(String nomeAssociado) {
		this.nomeAssociado = nomeAssociado;
	}
}
