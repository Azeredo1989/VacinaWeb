/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.AcessoUsuario;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author kalif
 */
@ManagedBean(name = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable{
    private UsuarioDAO<Usuario> dao;
    private Usuario usuarioLogado;
    private String usuario;
    private String senha;

    /**
     * @return the dao
     */
    
    public ControleLogin() {
		dao = new UsuarioDAO<>();
                //usuarioLogado = new Usuario();
      }

      public String paginaLogin() {
		return "/login?faces-redirect=true";
      }
      
      public String efetuarLogin() {
	  if (dao.login(usuario, senha)) {
	      usuarioLogado = dao.localizaPorNomeUsuario(usuario);
	      FacesContext context = FacesContext.getCurrentInstance();
	      HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
	      AcessoUsuario acessoUsuario = new AcessoUsuario(request.getRemoteAddr());
	      usuarioLogado.adicionarAcesso(acessoUsuario);
	      try {
		  dao.merge(usuarioLogado);
	      } catch (Exception e) {
		  Util.mensagemErro("Erro ao persistir acesso do usuário: " + e.getMessage());
	      }
	      Util.mensagemInformacao("Login efetuado com sucesso");
	      return "/templates/template";
	  } else {
	      Util.mensagemErro("Usuário ou senha inválidos");
	      return "/login";
	  }
      }
      
      
      public String efetuarLogout() {
		  usuarioLogado = null;
		  Util.mensagemInformacao("Logout efetuado com sucesso");
		  return "/index";
      }

    
    public UsuarioDAO<Usuario> getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(UsuarioDAO<Usuario> dao) {
        this.dao = dao;
    }

    /**
     * @return the usuarioLogado
     */
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * @param usuarioLogado the usuarioLogado to set
     */
    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
