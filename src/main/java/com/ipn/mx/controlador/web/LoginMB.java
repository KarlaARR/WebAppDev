package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.dto.UsuarioDTO;
import java.io.Serializable;
import javax.ejb.Remove;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author karla
 */
/*
@Named(value = "loginMB")
@Dependent*/

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB implements Serializable{
    
    private final UsuarioDAO dao = new UsuarioDAO();
    private UsuarioDTO dto = new UsuarioDTO();

    /**
     * Creates a new instance of LoginMB
     */
    public LoginMB() {
    }
    
    
    public String inicio(){
        return "index?faces-redirect=true";
    }

    
    public String login(){
        Integer v = dao.validarUsuario(dto);
        
        if(v==1)
            return "principal?faces-redirect=true";
        else
            return "error?faces-redirect=true";
    }
    
    
    public UsuarioDTO getDto() {
        return dto;
    }

    public void setDto(UsuarioDTO dto) {
        this.dto = dto;
    }
    
    
    
}
