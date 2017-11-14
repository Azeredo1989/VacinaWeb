/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import javax.persistence.Query;

/**
 *
 * @author Anderson
 */
public class UsuarioDAO<T> extends DAOGenerico<Usuario> implements Serializable {

    public UsuarioDAO() {
        super();
        super.setClassePersistente(Usuario.class);
        super.setOrdem("login");
    }

    public boolean login(String usuario, String senha) {
        Query query = super.getEm().createQuery(
                        "from Usuario where upper(login) = :usuario and "
                        + "upper(senha) = :senha and ativo = true");
        query.setParameter("usuario", usuario.toUpperCase());
        query.setParameter("senha", senha.toUpperCase());
        if (!query.getResultList().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Usuario localizaPorNomeUsuario(String usuario) {
        Usuario obj = (Usuario) super.getEm().createQuery(" from Usuario where upper(login) = :usuario").
                setParameter("usuario", usuario.toUpperCase()).getSingleResult();
        obj.getTelefone();
        obj.getCriancas().size();
        obj.getAcessos().size();
        return obj;
    }
}
