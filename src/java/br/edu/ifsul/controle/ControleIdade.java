/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.IdadeDAO;
import br.edu.ifsul.dao.VacinaDAO;
import br.edu.ifsul.modelo.Idade;
import br.edu.ifsul.modelo.Vacina;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Anderson
 */
@ManagedBean(name = "controleIdade")
@SessionScoped
public class ControleIdade implements Serializable{
    private IdadeDAO<Idade> dao;
    private Idade objeto;
    private VacinaDAO<Vacina> daoVacina;
    private Vacina vacina;
    private Boolean novaVacina;
    public ControleIdade(){
        dao = new IdadeDAO<>();
        daoVacina = new VacinaDAO<>();
    }
     
    public String listar(){
        return "/privado/idade/listar?faces-redirect=true";
    }
    public void nova(){
        objeto = new Idade();
    }
    public void salvar(){
        boolean persistiu = false;
        if(objeto.getId()== null){
            persistiu = dao.persist(objeto);
        }else{
            persistiu = dao.merge(objeto);
        }
        if(persistiu){
            Util.mensagemInformacao(dao.getMensagem());
        }else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public void editar(Integer id){
        objeto = dao.localizar(id);
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if(dao.remove(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        }else{
            Util.mensagemErro(dao.getMensagem());
        }
    }
    public void novaVacina(){
        vacina = new Vacina();
        novaVacina = true;
    }
    public void alterarVacina(int index){
        vacina = objeto.getVacinas().get(index);
        novaVacina = false;
    }
    
    public void salvarVacina(){
            objeto.adicionarVacina(vacina);
        Util.mensagemInformacao("Vacina persistida com sucesso!");
    }
    
    public void removerVacina(int index){
        objeto.removerVacina(index);
        Util.mensagemInformacao("Vacina removida com sucesso!");
    }

    /**
     * @return the dao
     */
    public IdadeDAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(IdadeDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Idade getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Idade objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the daoVacina
     */
    public VacinaDAO<Vacina> getDaoVacina() {
        return daoVacina;
    }

    /**
     * @param daoVacina the daoVacina to set
     */
    public void setDaoVacina(VacinaDAO<Vacina> daoVacina) {
        this.daoVacina = daoVacina;
    }

    /**
     * @return the vacina
     */
    public Vacina getVacina() {
        return vacina;
    }

    /**
     * @param vacina the vacina to set
     */
    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    /**
     * @return the novaVacina
     */
    public Boolean getNovaVacina() {
        return novaVacina;
    }

    /**
     * @param novaVacina the novaVacina to set
     */
    public void setNovaVacina(Boolean novaVacina) {
        this.novaVacina = novaVacina;
    }

    /**
     * @return the daoVacina
     */
    
}
