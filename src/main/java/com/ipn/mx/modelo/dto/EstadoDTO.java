package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Estado;
import com.ipn.mx.modelo.entidades.Usuario;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author karla
 */

@Data
@AllArgsConstructor
public class EstadoDTO implements Serializable{
    
    private Estado entidad;
    
    public EstadoDTO(){
        entidad =  new Estado();
    }
    
    @Override
    public String toString(){      
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(getEntidad().getId()).append("\n");
        sb.append("nombre: ").append(getEntidad().getNombre()).append("\n");
        sb.append("clave: ").append(getEntidad().getClave()).append("\n");
        sb.append("abreviacion: ").append(getEntidad().getAbreviacion()).append("\n");
        
        return sb.toString();
    }
    
}
