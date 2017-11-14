/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CriancaDAO;
import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.dao.VacinaDAO;
import br.edu.ifsul.modelo.Crianca;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.modelo.Vacina;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anderson
 */
@ManagedBean(name = "controleCrianca")
@SessionScoped
public class ControleCrianca implements Serializable{
    private CriancaDAO<Crianca> dao;
    private Crianca objeto;
    private VacinaDAO<Vacina> daoVacina;
    private Vacina vacina;
    private UsuarioDAO<Usuario> daoUsuario;
    private Usuario usuario;
    @ManagedProperty(value = "#{controleLogin}")
    private ControleLogin controleLogin;
    
    public ControleCrianca(){
        dao = new CriancaDAO<>();
        daoVacina = new VacinaDAO<>();
        daoUsuario = new UsuarioDAO<>();
        if (controleLogin == null){
            ExternalContext contextoExterno = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session = (HttpSession) contextoExterno.getSession(true);
            controleLogin = (ControleLogin) session.getAttribute("controleLogin");
        }
    }
    
    
//    public void adicionarVacinacao(){
//        if(vacina != null){
//            if(!objeto.getVacinacao().contains(vacina)){
//                objeto.getVacinacao().add(vacina);
//            }else{
//                Util.mensagemErro("Esta vacina já existe na lista");
//            }
//        }
//    }
    
    public String listar(){
        return "/privado/crianca/listar?faces-redirect=true";
    }
    public String novo(){
        objeto = new Crianca();
        return "formulario?faces-redirect=true";
    }
    public String salvar(){
        boolean persistiu = false;
        objeto.setUsuario(controleLogin.getUsuarioLogado());
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
    public CriancaDAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(CriancaDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Crianca getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Crianca objeto) {
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
     * @return the controleLogin
     */
    public ControleLogin getControleLogin() {
        return controleLogin;
    }

    /**
     * @param controleLogin the controleLogin to set
     */
    public void setControleLogin(ControleLogin controleLogin) {
        this.controleLogin = controleLogin;
    }

    /**
     * @return the daoUsuario
     */
    public UsuarioDAO<Usuario> getDaoUsuario() {
        return daoUsuario;
    }

    /**
     * @param daoUsuario the daoUsuario to set
     */
    public void setDaoUsuario(UsuarioDAO<Usuario> daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
