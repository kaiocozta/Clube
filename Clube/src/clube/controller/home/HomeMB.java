package clube.controller.home;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class HomeMB {

	private boolean administrador;
	private String associado = "Associado";

	@PostConstruct
	public void init() {
		setAdministrador(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ADMINISTRADOR"));
		if (!isAdministrador())
			setAssociado("Meu Dados");
	}

	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();

		return "/pages/private/home?faces-redirect=true";
	}

	public String associado() {
		if (isAdministrador()) {
			return "listaAssociado";
		} else {
			return "associado";
		}
	}

	public String emprestimo() {
		if (isAdministrador()) {
			return "listaEmprestimo";
		} else {
			return "emprestimo";
		}
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public String getAssociado() {
		return associado;
	}

	public void setAssociado(String associado) {
		this.associado = associado;
	}
}
