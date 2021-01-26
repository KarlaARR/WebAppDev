package com.ipn.mx.modelo.dto;

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
public class UsuarioDTO implements Serializable{
    
    private Usuario entidad;
    
    public UsuarioDTO(){
        entidad = new Usuario();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(getEntidad().getId()).append("\n");
        sb.append("nombres: ").append(getEntidad().getNombres()).append("\n");
        sb.append("paterno: ").append(getEntidad().getPaterno()).append("\n");
        sb.append("materno: ").append(getEntidad().getMaterno()).append("\n");
        sb.append("email: ").append(getEntidad().getEmail()).append("\n");
        sb.append("tipo: ").append(getEntidad().getTipo()).append("\n");
        sb.append("username: ").append(getEntidad().getUsername()).append("\n");
        sb.append("password: ").append(getEntidad().getPassword()).append("\n");
        sb.append("foto: ").append(getEntidad().getFoto()).append("\n");
        sb.append("estado: ").append(getEntidad().getEstado()).append("\n");
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        UsuarioDTO dto =  new UsuarioDTO();
        dto.getEntidad().setId(1);
    }
    
}
