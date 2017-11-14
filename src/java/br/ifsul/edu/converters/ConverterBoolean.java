/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.edu.converters;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Idade;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author kalif
 */
@FacesConverter(value = "converterBoolean")
public class ConverterBoolean implements Converter, Serializable{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string.equals("Sim")){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Boolean b = (Boolean) o;
        return b ? "Sim" : "Não";
            }
}
