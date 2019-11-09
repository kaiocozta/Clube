package clube.controller.emprestimo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import clube.model.Associado;
import clube.model.AssociadoVideogameJogo;
import clube.model.Emprestimo;
import clube.model.ItemEmprestimo;
import clube.model.JogoStatus;
import clube.service.ClubeService;
import clube.service.IClubeService;
import util.ResourceBundleUtil;

@ManagedBean
@ViewScoped
public class EmprestimoMB implements Serializable {

	private static final long serialVersionUID = 7376834516863092920L;

	private IClubeService service = ClubeService.getInstance();

	private Associado locatarioSelecionado;
	private String nomeLocatario;
	private List<Associado> locatarios;

	private Emprestimo emprestimo;
	private Date dataAtual;

	private ItemEmprestimo itemEmprestimo;
	private String nomeJogo;

	private List<AssociadoVideogameJogo> jogos;
	private Date dataDevolucao;

	@PostConstruct
	public void init() {
		setLocatarios(new ArrayList<Associado>());
		setJogos(new ArrayList<AssociadoVideogameJogo>());
		setEmprestimo(new Emprestimo());
	}
	
	public String emprestar(AssociadoVideogameJogo jogo) {
		jogo.setStatus(JogoStatus.EMPRESTADO);
		itemEmprestimo = new ItemEmprestimo(emprestimo,jogo,dataDevolucao);
		emprestimo.addJogo(itemEmprestimo);
		emprestimo.setLocador(jogo.getAssociadoVideogame().getAssociado());
		
		FacesMessage fm = new FacesMessage("Jogo adicionado para emprestimo!");
		FacesContext.getCurrentInstance().addMessage("Sucesso", fm);
		
		return null;	
	}

	public String selecionarLocatario(Associado locatario) {
		setLocatarioSelecionado(locatario);
		FacesMessage fm = new FacesMessage("Locatário " + locatario.getNome() + " Selecionado!");
		FacesContext.getCurrentInstance().addMessage("Sucesso", fm);
		return null;
	}

	public String buscarJogos() {
		setJogos(service.buscarJogosPorNome(getNomeJogo()));
		return null;
	}
	
	public String buscarLocatarios() {
		setLocatarios(service.buscarLocatariosPorNome(getNomeLocatario()));
		return null;
	}

	public String salvar() {
		setDataEmprestimo();
		service.salvarEmprestimo(getEmprestimo());

		FacesMessage fm = new FacesMessage(ResourceBundleUtil.getKey("emprestimo.save.succes"));
		FacesContext.getCurrentInstance().addMessage("Sucesso", fm);

		return null;
	}

	private void setDataEmprestimo() {
		setDataAtual(new Date());
		getEmprestimo().setData(getDataAtual());
		System.out.println(getEmprestimo().getData());
	}

	public ItemEmprestimo getItemEmprestimo() {
		return itemEmprestimo;
	}

	public void setItemEmprestimo(ItemEmprestimo itemEmprestimo) {
		this.itemEmprestimo = itemEmprestimo;
	}

	public List<AssociadoVideogameJogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<AssociadoVideogameJogo> jogos) {
		this.jogos = jogos;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public Associado getLocatarioSelecionado() {
		return locatarioSelecionado;
	}

	public void setLocatarioSelecionado(Associado locatarioSelecionado) {
		this.locatarioSelecionado = locatarioSelecionado;
	}

	public String getNomeLocatario() {
		return nomeLocatario;
	}

	public void setNomeLocatario(String nomeLocatario) {
		this.nomeLocatario = nomeLocatario;
	}

	public List<Associado> getLocatarios() {
		return locatarios;
	}

	public void setLocatarios(List<Associado> locatarios) {
		this.locatarios = locatarios;
	}

	public String getNomeJogo() {
		return nomeJogo;
	}

	public void setNomeJogo(String nomeJogo) {
		this.nomeJogo = nomeJogo;
	}
}
