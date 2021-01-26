package com.ipn.mx.controlador.web;

import lombok.Data;

/**
 *
 * @author karla
 */

@Data
public class BaseBean {
    
    protected static final String ACC_CREAR = "CREAR";
    protected static final String ACC_ACTUALIZAR = "ACTUALIZAR";
    protected String accion;
    
    public boolean isModoCrear(){
        
        if(accion != null)
            return accion.equals(ACC_CREAR);
        else
            return false;
    }
    
    public boolean isModoActualizar(){
        
        if(accion != null)
            return accion.equals(ACC_ACTUALIZAR);
        else
            return false;
    }
    
    
}
