package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Producto;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author karla
 */

@Data
@AllArgsConstructor
public class ProductoDTO implements Serializable{
    
    private Producto entidad;
    
    public ProductoDTO(){
        entidad =  new Producto();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(getEntidad().getId()).append("\n");
        sb.append("nombre: ").append(getEntidad().getNombre()).append("\n");
        sb.append("descripcion: ").append(getEntidad().getDescripcion()).append("\n");
        sb.append("categoria: ").append(getEntidad().getCategoria()).append("\n");
        sb.append("cantidad: ").append(getEntidad().getCantidad()).append("\n");
        sb.append("imagen: ").append(getEntidad().getImagen()).append("\n");
        
        return sb.toString();
    }
    
}
