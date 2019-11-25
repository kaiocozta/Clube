package clube.controller.login;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import util.MD5Util;

@ManagedBean
@RequestScoped
public class LoginMB implements Serializable {

	private static final long serialVersionUID = 1759241769200172991L;

	private String username;
	private String password;

	public String login() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		try {
			request.login(username, MD5Util.gerarHashMD5(password));
//			Boolean role = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ADMINISTRADOR");
//			if (!role)
//				return "/pages/private/associado/associado_form.xhtml?faces-redirect=true";
			return "/pages/private/home?faces-redirect=true";
		} catch (ServletException e) {
			return "/pages/public/erro_login?faces-redirect=true";
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
