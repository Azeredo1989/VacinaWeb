/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Anderson
 */
@ManagedBean(name = "controleUsuario")
@SessionScoped
public class ControleUsuario implements Serializable{
    private UsuarioDAO<Usuario> dao;
    private Usuario objeto;
    
    public ControleUsuario(){
        dao = new UsuarioDAO<>();
    }
    
    public String listar(){
        return "/privado/usuario/listar?faces-redirect=true";
    }
    public String novo(){
        objeto = new Usuario();
        return "formulario?faces-redirect=true";
    }
    public String salvar(){
        boolean persistiu = false;
        if(objeto.getId()== null){
            persistiu = dao.persist(objeto);
        }else{
            persistiu = dao.merge(objeto);
        }
        if(persistiu){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        }else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    public String editar(Integer id){
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if(dao.remove(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        }else{
            Util.mensagemErro(dao.getMensagem());
        }
    }

    /**
     * @return the dao
     */
    public UsuarioDAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(UsuarioDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Usuario getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Usuario objeto) {
        this.objeto = objeto;
    }
    
}
