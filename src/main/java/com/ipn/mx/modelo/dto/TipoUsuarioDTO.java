package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.TipoUsuario;
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
public class TipoUsuarioDTO implements Serializable{
    
    private TipoUsuario entidad;
    
    public TipoUsuarioDTO(){
        entidad =  new TipoUsuario();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getEntidad().getNombre()).append("\n");
        
        sb.append("id: ").append(getEntidad().getId()).append("\n");
        sb.append("nombre: ").append(getEntidad().getNombre()).append("\n");
        
        return sb.toString();
    }
    
}
