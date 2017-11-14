/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Vacinacao;
import java.io.Serializable;

/**
 *
 * @author Anderson
 */
public class VacinacaoDAO<T> extends DAOGenerico<Vacinacao> implements Serializable{
    public VacinacaoDAO(){
        super();
        classePersistente = Vacinacao.class;
        ordem = "nome";
    }
    
}
