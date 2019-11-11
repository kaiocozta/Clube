package clube.model.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "user_role")
public class Papel implements Serializable {

	private static final long serialVersionUID = -2723156936389403372L;

	@Id
	@Column(nullable = false, name = "role_name")
	private String nome;

	@Id
	@ManyToOne
	@JoinColumn(name = "user_name", referencedColumnName = "user_name")
	Usuario usuario;

	public Papel(Usuario usuario, String papel) {
		this.usuario = usuario;
		nome = papel;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
