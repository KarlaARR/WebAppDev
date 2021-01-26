/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web;

import com.ipn.mx.utilerias.EnviarMail;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author karla
 */
/*
@Named(value = "correoMB")
@Dependent*/

@ManagedBean(name = "correoMB")
@SessionScoped
public class CorreoMB implements Serializable{
    
    private String Asunto;
    private String Mensaje;

    /**
     * Creates a new instance of CorreoMB
     */
    public CorreoMB() {
    }
    
    public String enviar(){
        EnviarMail email = new EnviarMail();
        String destinatario = "proyectoWAD@gmail.com";
        email.enviarCorreo(destinatario, Asunto, Mensaje);
        return "principal?faces-redirect=true";
    }
    
    public String back(){
        return "principal?faces-redirect=true";
    }

    public String getAsunto() {
        return Asunto;
    }

    public void setAsunto(String Asunto) {
        this.Asunto = Asunto;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }
    
    
    
}
