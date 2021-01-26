package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
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

/**
 *
 * @author karla
 */
/*
@Named(value = "categoriaMB")
@Dependent*/

@ManagedBean(name = "categoriaMB")
@SessionScoped
public class CategoriaMB extends BaseBean implements Serializable{

    private final CategoriaDAO dao = new CategoriaDAO();
    private CategoriaDTO dto = new CategoriaDTO();
    private List<CategoriaDTO> listaCategorias;

    /**
     * Creates a new instance of CategoriaMB
     */
    public CategoriaMB() {
    }

    @PostConstruct
    public void iniciar() {
        listaCategorias = new ArrayList<>();
        listaCategorias = dao.readAll();
    }
    
    

    public String prepareAdd() {
        dto = new CategoriaDTO();
        setAccion(ACC_CREAR);
        return "/categoria/categoriaForm?faces-redirect=true";

    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);
        return "/categoria/categoriaForm?faces-redirect=true";

    }

    public String prepareIndex() {
        iniciar();
        return "/categoria/listaCategorias?faces-redirect=true";
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
        
        return "/categoria/verCategoria?faces-redirect=true";
    }

    public String delete() {
        dao.delete(dto);

        return prepareIndex();
    }

    public void selectCategoria(ActionEvent event) {
        String claveSel = (String) FacesContext.getCurrentInstance().getExternalContext().
                getRequestParameterMap().get("claveSel");
        dto = new CategoriaDTO();
        dto.getEntidad().setId(Integer.parseInt(claveSel));
        try {
            dto = dao.read(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String grafica(){
        return "/categoria/graficaCategoria?faces-redirect=true";
    }

    public CategoriaDTO getDto() {
        return dto;
    }

    public void setDto(CategoriaDTO dto) {
        this.dto = dto;
    }

    public List<CategoriaDTO> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<CategoriaDTO> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

}
