package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dto.ProductoDTO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.entidades.Categoria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author karla
 */
/*
@Named(value = "productoMB")
@Dependent
*/

@ManagedBean(name = "productoMB")
@SessionScoped
public class ProductoMB extends BaseBean implements Serializable{
    
    private final ProductoDAO dao = new ProductoDAO();
    private ProductoDTO dto = new ProductoDTO();
    private List<ProductoDTO> listaProductos;
    
    private List<SelectItem> listaCategorias;
    private String categoria;

    /**
     * Creates a new instance of ProductoMB
     */
    public ProductoMB() {
    }
    
    @PostConstruct
    public void iniciar() {
        listaProductos = new ArrayList<>();
        listaProductos = dao.readAll();
    }
    
    public String prepareAdd() {
        dto = new ProductoDTO();
        setAccion(ACC_CREAR);
        return "/producto/productoForm?faces-redirect=true";
    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);
        return "/producto/productoForm?faces-redirect=true";
    }
    
    public String prepareIndex() {
        iniciar();
        return "/producto/listaProductos?faces-redirect=true";
    }

    public String back() {
        return prepareIndex();
    }
    
    public boolean validate() {
        boolean valido = true;
        return valido;
    }

    public String add() {
        boolean valido = validate();
        if (valido) {
            dao.create(dto);
            /*
            if(valido)
                return prepareIndex();
            else
                return prepareAdd();
             */
        }

        return prepareIndex();
    }

    public String update() {
        boolean valido = validate();
        if (valido) {
            dao.update(dto);
        }

        return prepareIndex();
    }
    
    public String select(){
        dao.read(dto);
        return "/producto/verProducto?faces-redirect=true";
    }
    
    public String delete() {
        dao.delete(dto);

        return prepareIndex();
    }
    
    public void selectCategoria(ActionEvent event) {
        String claveSel = (String) FacesContext.getCurrentInstance().getExternalContext().
                getRequestParameterMap().get("claveSel");
        dto = new ProductoDTO();
        dto.getEntidad().setId(Integer.parseInt(claveSel));
        try {
            dto = dao.read(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProductoDTO getDto() {
        return dto;
    }

    public void setDto(ProductoDTO dto) {
        this.dto = dto;
    }

    public List<ProductoDTO> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<ProductoDTO> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<SelectItem> getListaCategorias() {
        
        this.listaCategorias = new ArrayList<>();
        CategoriaDAO catDao = new CategoriaDAO();
        List<CategoriaDTO> c = catDao.readAll();
        
        for (CategoriaDTO cat : c) {
            SelectItem catItem = new SelectItem(cat.getEntidad().getId(),cat.getEntidad().getNombre());
            this.listaCategorias.add(catItem);
        }
        
        return listaCategorias;
    }

    public String getCategoria() {
        
        CategoriaDAO catDAO = new CategoriaDAO();
        this.categoria = catDAO.read2((Integer)dto.getEntidad().getId());
        return categoria;
    }    
    
}
