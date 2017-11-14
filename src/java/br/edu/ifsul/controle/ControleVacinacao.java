/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.VacinacaoDAO;
import br.edu.ifsul.modelo.Vacinacao;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Anderson
 */
@ManagedBean(name = "controleVacinacao")
@SessionScoped
public class ControleVacinacao implements Serializable{
    private VacinacaoDAO<Vacinacao> dao;
    private Vacinacao objeto;
    
    public ControleVacinacao(){
        dao = new VacinacaoDAO<>();
    }
    
    public String listar(){
        return "/privado/vacinacao/listar?faces-redirect=true";
    }
    public String novo(){
        objeto = new Vacinacao();
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
    public VacinacaoDAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(VacinacaoDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Vacinacao getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Vacinacao objeto) {
        this.objeto = objeto;
    }
    
}
