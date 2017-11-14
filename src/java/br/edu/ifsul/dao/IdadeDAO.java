/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Idade;
import java.io.Serializable;

/**
 *
 * @author Anderson
 */
public class IdadeDAO<T> extends DAOGenerico<Idade> implements Serializable{
    public IdadeDAO(){
        super();
        classePersistente = Idade.class;
        ordem = "descricao";
    }
    
}
