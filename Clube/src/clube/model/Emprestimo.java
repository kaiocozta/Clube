package clube.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Emprestimo implements IBaseEntity {

	private static final long serialVersionUID = -2664894933372487877L;;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "associado_locador_id")
	private Associado locador;

	@Temporal(TemporalType.DATE)
	@Column
	private Date data;

	@OneToMany(mappedBy = "emprestimo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ItemEmprestimo> itensEmprestimo;

	public Emprestimo() {
		setItensEmprestimo(new HashSet<ItemEmprestimo>());
	}

	public Emprestimo(Associado locador, Date data) {
		this();
		this.locador = locador;
		this.data = data;
	}

	public ItemEmprestimo addJogo(ItemEmprestimo itemEmprestimo) {
		itensEmprestimo.add(itemEmprestimo);
		return itemEmprestimo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Associado getLocador() {
		return locador;
	}

	public void setLocador(Associado locador) {
		this.locador = locador;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Set<ItemEmprestimo> getItensEmprestimo() {
		return itensEmprestimo;
	}

	public void setItensEmprestimo(Set<ItemEmprestimo> itensEmprestimo) {
		this.itensEmprestimo = itensEmprestimo;
	}

}
