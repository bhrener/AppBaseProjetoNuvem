package br.com.appnuvem.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.appnuvem.conexoes.ConexaoWebLogin;
import br.com.appnuvem.model.Usuario;

@ManagedBean(name="loginBean")
public class LoginBean {
	private String nome;
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	private String email;
	private String senha;
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LoginBean() {
		super();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}

	public String login() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		ConexaoWebLogin cwl = new ConexaoWebLogin();
		if (cwl.confirmarUser(senha, email)) {
			if (session == null) {
				session = (HttpSession) FacesContext.getCurrentInstance()
						.getExternalContext().getSession(true);
				session.setAttribute("nome", email);
				return "/index?faces-redirect=true";
			} else {
				if (session != null) {
					session.invalidate();
				}
			}
		}
		return "/login";
	}
	public String cadastrar(){
		ConexaoWebLogin cwl = new ConexaoWebLogin();
		cwl.cadastratUser(new Usuario(nome,email,senha));
		return "/login";
	} 
}
