/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.controle.ControleLogin;
import br.edu.ifsul.modelo.Crianca;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anderson
 */
public class CriancaDAO<T> extends DAOGenerico<Crianca> implements Serializable{
    @ManagedProperty(value = "#{controleLogin}")
    ControleLogin controleLogin;
    public CriancaDAO(){
        super();
        classePersistente = Crianca.class;
        ordem = "nome";        
        if (controleLogin == null){
            ExternalContext contextoExterno = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session = (HttpSession) contextoExterno.getSession(true);
            controleLogin = (ControleLogin) session.getAttribute("controleLogin");
        }
    }
    
    @Override
    public List<Crianca> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        String where = "";
        jpql += " where usuario.id = '" + controleLogin.getUsuarioLogado().getId() + "' ";
        filtro = filtro.replaceAll("[';-]", "");
        if (filtro.length() > 0){
            if (ordem.equals("id")){
                try {
                    Integer.parseInt(filtro);
                    where += " and " + ordem + " = '" + filtro + "' ";
                } catch (Exception e){}
            } else {
                where += " and upper(" + ordem + ") like '" + filtro.toUpperCase() + "%' ";
            }            
        }
        jpql += where;
        jpql += " order by " + ordem;
        totalObjetos = em.createQuery(jpql).getResultList().size();        
        return em.createQuery(jpql).setFirstResult(posicaoAtual).setMaxResults(maximoObjetos).getResultList();
    }    
    
}
