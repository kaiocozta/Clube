package clube.controller.associado;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import clube.model.Associado;
import clube.model.AssociadoVideogame;
import clube.model.AssociadoVideogameJogo;
import clube.model.Videogame;
import clube.model.security.Usuario;
import clube.service.ClubeService;
import clube.service.IClubeService;
import util.MD5Util;
import util.ResourceBundleUtil;

@ManagedBean
@ViewScoped
public class AssociadoMB implements Serializable {

	private static final long serialVersionUID = 7376834516863092920L;

	private IClubeService service = ClubeService.getInstance();

	private Associado associado;

	private String login = "";
	private String senha = "";

	private boolean existeUsuarioVinculado;
	private boolean administrador;

	private List<Videogame> videogames;

	private Videogame selectedVideogame = new Videogame();
	private AssociadoVideogame currentAssociadoVideogame;
	private String novoJogo;

	private boolean enableVideogameAdd;
	private boolean enableVideogameDel;

	@PostConstruct
	public void init() {
		videogames = service.listarVideogames();

		Principal usePrincipal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		administrador = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ADMINISTRADOR");

		if (administrador) {
			associado = (Associado) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("associado");
		} else {
			associado = service.buscarAssociadoPorNomeDeUsuario(usePrincipal.getName());

		}

		if (associado == null) {
			associado = new Associado();
			login = senha = "";
			existeUsuarioVinculado = false;
		} else {
			associado = service.encontrarAssociadoPorId_fullList(associado.getId());
			if (associado.getUsuario() != null) {
				login = associado.getUsuario().getLogin();
				existeUsuarioVinculado = true;
			}else
				existeUsuarioVinculado = false;
		}
	}

	public void videogameSelecionado(ValueChangeEvent e) {

		enableVideogameAdd = enableVideogameDel = false;

		if ("".equals(e.getNewValue()) || e.getNewValue() == null) {
			selectedVideogame = new Videogame();
			return;
		}

		Long videogameId = (Long) e.getNewValue();
		selectedVideogame.setId(videogameId);

		currentAssociadoVideogame = null;

		for (AssociadoVideogame associadoVideogame : associado.getVideogames()) {
			if (associadoVideogame.getVideogame().getId().equals(videogameId)) {
				enableVideogameDel = true;
				currentAssociadoVideogame = associadoVideogame;
			}
		}

		if (currentAssociadoVideogame == null) {
			enableVideogameAdd = true;
		}
	}

	public String adicionarVideogame() {

		selectedVideogame = service.encontrarPorId(selectedVideogame.getId());

		currentAssociadoVideogame = associado.addVideogame(selectedVideogame);

		enableVideogameDel = true;
		enableVideogameAdd = false;

		FacesMessage fm = new FacesMessage("Videogame adicionado com sucesso!");
		FacesContext.getCurrentInstance().addMessage("Sucesso", fm);

		return null;
	}

	public String removerVideogame() {

		for (AssociadoVideogame associadoVideogame : associado.getVideogames()) {
			if (associadoVideogame.getVideogame().getId().equals(selectedVideogame.getId())) {
				associado.getVideogames().remove(associadoVideogame);
				currentAssociadoVideogame = null;
				enableVideogameDel = false;
				enableVideogameAdd = true;
				selectedVideogame = new Videogame();

				FacesMessage fm = new FacesMessage("Videogame removido com sucesso!");
				FacesContext.getCurrentInstance().addMessage("Sucesso", fm);

				break;
			}
		}

		return null;
	}

	public String adicionarJogo() {

		if ("".equals(novoJogo)) {
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe o título do jogo",
					"Informe o título do jogo");
			FacesContext.getCurrentInstance().addMessage("Erro", fm);
			return null;
		}

		currentAssociadoVideogame.addJogo(novoJogo);

		novoJogo = "";

		FacesMessage fm = new FacesMessage("Jogo adicionado com sucesso!");
		FacesContext.getCurrentInstance().addMessage("Sucesso", fm);

		return null;
	}

	public String excluirJogo(AssociadoVideogameJogo jogo) {
		for (AssociadoVideogameJogo avj : currentAssociadoVideogame.getJogos()) {
			if (avj.equals(jogo)) {
				currentAssociadoVideogame.getJogos().remove(avj);

				FacesMessage fm = new FacesMessage("Jogo excluído com sucesso!");
				FacesContext.getCurrentInstance().addMessage("Sucesso", fm);

				break;
			}

		}

		return null;
	}

	public String salvar() {
		String aviso = "Sucesso";
		FacesMessage fm = null;

		if (!existeUsuarioVinculado) {

			if (verificaPreeximentoCamposLoginSenha()) {
				criarEVinculaUsuario();
			}
			service.salvarAssociado(getAssociado());
			fm = new FacesMessage(ResourceBundleUtil.getKey("associado.save.succes"));
		} else {

			if (verificaPreeximentoCamposLoginSenha()) {
				if (verificaAlteracaoNaSenha())
					atualizarSenha();
				if (administrador)
					atualizarLogin();
			} else {
				aviso = "Erro";
				fm = new FacesMessage("Preenxa campo senha!");
				FacesContext.getCurrentInstance().addMessage(aviso, fm);
				return null;
			}
			service.salvarAssociado(associado);
			fm = new FacesMessage(ResourceBundleUtil.getKey("associado.save.succes"));
		}

		FacesContext.getCurrentInstance().addMessage(aviso, fm);

		return null;
	}

	private void atualizarLogin() {
		getAssociado().getUsuario().setLogin(login);
	}

	private void atualizarSenha() {
		getAssociado().getUsuario().setSenha(MD5Util.gerarHashMD5(senha));
	}

	private boolean verificaAlteracaoNaSenha() {
		if (MD5Util.gerarHashMD5(senha) != getAssociado().getUsuario().getSenha())
			return true;
		else
			return false;
	}

	private void criarEVinculaUsuario() {
		Usuario u = new Usuario(login, MD5Util.gerarHashMD5(senha));
		u.addPapel("USUARIO");
		getAssociado().setUsuario(u);
		existeUsuarioVinculado = true;
	}

	private boolean verificaPreeximentoCamposLoginSenha() {
		if ("".equals(login) || "".equals(senha))
			return false;
		return true;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	public List<Videogame> getVideogames() {
		return videogames;
	}

	public void setVideogames(List<Videogame> videogames) {
		this.videogames = videogames;
	}

	public Videogame getSelectedVideogame() {
		return selectedVideogame;
	}

	public void setSelectedVideogame(Videogame selectedVideogame) {
		this.selectedVideogame = selectedVideogame;
	}

	public boolean isEnableVideogameAdd() {
		return enableVideogameAdd;
	}

	public void setEnableVideogameAdd(boolean enableVideogameAdd) {
		this.enableVideogameAdd = enableVideogameAdd;
	}

	public boolean isEnableVideogameDel() {
		return enableVideogameDel;
	}

	public void setEnableVideogameDel(boolean enableVideogameDel) {
		this.enableVideogameDel = enableVideogameDel;
	}

	public AssociadoVideogame getCurrentAssociadoVideogame() {
		return currentAssociadoVideogame;
	}

	public void setCurrentAssociadoVideogame(AssociadoVideogame currentAssociadoVideogame) {
		this.currentAssociadoVideogame = currentAssociadoVideogame;
	}

	public String getNovoJogo() {
		return novoJogo;
	}

	public void setNovoJogo(String novoJogo) {
		this.novoJogo = novoJogo;
	}

	public boolean isExisteUsuarioVinculado() {
		return existeUsuarioVinculado;
	}

	public void setExisteUsuarioVinculado(boolean existeUsuarioVinculado) {
		this.existeUsuarioVinculado = existeUsuarioVinculado;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
