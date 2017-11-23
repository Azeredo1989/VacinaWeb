/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.VacinacaoDAO;
import br.edu.ifsul.modelo.Crianca;
import br.edu.ifsul.modelo.Vacina;
import br.edu.ifsul.modelo.Vacinacao;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Anderson
 */
@ManagedBean(name = "controleVacinacao")
@SessionScoped
public class ControleVacinacao implements Serializable {

    
    private VacinacaoDAO<Vacinacao> dao;
    private Vacinacao objeto;
    private Crianca crianca;
    Boolean novaVacinacao;

    public ControleVacinacao() {
        dao = new VacinacaoDAO<>();
    }

    public String listar() {
        return "/privado/vacinacao/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Vacinacao();
    }

    public String salvar() {
        boolean persistiu = false;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }

    public String cancelar() {
        return "listar?faces-redirect=true";
    }

    public void editar(Integer id) {
        objeto = dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remove(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
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

    /**
     * @return the crianca
     */
    public Crianca getCrianca() {
        return crianca;
    }

    /**
     * @param crianca the crianca to set
     */
    public void setCrianca(Crianca crianca) {
        this.crianca = crianca;

    }

    /**
     * @return the daoVacinacao
     */
    

}
