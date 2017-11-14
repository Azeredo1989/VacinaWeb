/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Vacina;
import java.io.Serializable;

public class VacinaDAO<T> extends DAOGenerico<Vacina> implements Serializable{
    public VacinaDAO(){
        super();
        classePersistente = Vacina.class;
        ordem = "id";
    }
}
