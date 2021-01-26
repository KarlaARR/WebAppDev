package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author karla
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Estado")
public class Estado implements Serializable{
    
    @Id
    private int id;
    private String nombre;
    private String clave;
    private String abreviacion;
    
}
