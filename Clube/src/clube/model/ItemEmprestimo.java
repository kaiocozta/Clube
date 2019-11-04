package clube.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ItemEmprestimo implements IBaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "emprestimo_id")
	private Emprestimo emprestimo;

	@ManyToOne
	@JoinColumn(name = "jogo_id")
	private AssociadoVideogameJogo jogo;

	@Column
	private Enum<JogoStatus> status = JogoStatus.EMPRESTADO;

	@Column()
	@Temporal(TemporalType.DATE)
	private Date dataDevolucao;

	public ItemEmprestimo() {
		super();
	}

	public ItemEmprestimo(Emprestimo emprestimo, AssociadoVideogameJogo jogo, Date dataDevolucao) {
		super();
		this.emprestimo = emprestimo;
		this.jogo = jogo;
		this.dataDevolucao = dataDevolucao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Enum<JogoStatus> getStatus() {
		return status;
	}

	public void setStatus(Enum<JogoStatus> status) {
		this.status = status;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

}
