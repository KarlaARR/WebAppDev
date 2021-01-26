package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dao.EstadoDAO;
import com.ipn.mx.modelo.dto.EstadoDTO;
import com.ipn.mx.modelo.dao.TipoUsuarioDAO;
import com.ipn.mx.modelo.dto.TipoUsuarioDTO;
import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.dto.UsuarioDTO;
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
@Named(value = "usuarioMB")
@Dependent*/

@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioMB extends BaseBean implements Serializable{
    
    private final UsuarioDAO dao = new UsuarioDAO();
    private UsuarioDTO dto = new UsuarioDTO();
    private List<UsuarioDTO> listaUsuarios;
    
    private List<SelectItem> listaTipos;
    private List<SelectItem> listaEstados;
    
    private String tipoDTO;
    private String Estado;
    
    /**
     * Creates a new instance of UsuarioMB
     */
    public UsuarioMB() {
    }
    
    @PostConstruct
    public void iniciar() {
        listaUsuarios = new ArrayList<>();
        listaUsuarios = dao.readAll();
    }
    
    public String prepareAdd() {
        dto = new UsuarioDTO();
        setAccion(ACC_CREAR);
        return "/usuario/usuarioForm?faces-redirect=true";
    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);
        return "/usuario/usuarioForm?faces-redirect=true";
    }
    
    public String prepareIndex() {
        iniciar();
        return "/usuario/listaUsuarios?faces-redirect=true";
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
        
        return "/usuario/verUsuario?faces-redirect=true";
    }

    public String delete() {
        dao.delete(dto);

        return prepareIndex();
    }
    
    public void selectCategoria(ActionEvent event) {
        String claveSel = (String) FacesContext.getCurrentInstance().getExternalContext().
                getRequestParameterMap().get("claveSel");
        dto = new UsuarioDTO();
        dto.getEntidad().setId(Integer.parseInt(claveSel));
        try {
            dto = dao.read(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UsuarioDTO getDto() {
        return dto;
    }

    public void setDto(UsuarioDTO dto) {
        this.dto = dto;
    }

    public List<UsuarioDTO> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<UsuarioDTO> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<SelectItem> getListaTipos() {
        
        this.listaTipos = new ArrayList<>();
        TipoUsuarioDAO tipoDao = new TipoUsuarioDAO();
        List<TipoUsuarioDTO> t = tipoDao.readAll();
        
        for(TipoUsuarioDTO tipo:t){
            SelectItem tipoItem = new SelectItem(tipo.getEntidad().getId(),tipo.getEntidad().getNombre());
            this.listaTipos.add(tipoItem);
        }
        return listaTipos;
    }

    public List<SelectItem> getListaEstados() {
        
        this.listaEstados = new ArrayList<>();
        EstadoDAO estadoDao = new EstadoDAO();
        List<EstadoDTO> e = estadoDao.readAll();
        
        for(EstadoDTO estado : e){
            SelectItem estadoItem = new SelectItem(estado.getEntidad().getId(),estado.getEntidad().getNombre());
            this.listaEstados.add(estadoItem);
        }
        return listaEstados;
    }

    public String getTipoDTO() {
        TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
        
        this.tipoDTO = tipoUsuarioDAO.readString(dto.getEntidad().getTipo());
        return tipoDTO;
    }

    public String getEstado() {
        EstadoDAO estadoDAO = new EstadoDAO();
        this.Estado = estadoDAO.readString(dto.getEntidad().getEstado());
        return Estado;
    }
    
    
    
    
}
