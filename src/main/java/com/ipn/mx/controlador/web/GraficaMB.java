/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dao.GraficaDAO;
import com.ipn.mx.modelo.dto.GraficaDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.BarChartModel;

/**
 *
 * @author karla
 */
/*
@Named(value = "graficaMB")
@Dependent*/

@ManagedBean(name = "graficaMB")
@SessionScoped
public class GraficaMB implements Serializable{

    private BarChartModel productosCategoria;
    /**
     * Creates a new instance of GraficaMB
     */
    public GraficaMB(){
    }
    
    @PostConstruct
    public void init(){
        productosCategoria = new BarChartModel();
        final ChartSeries productos = new ChartSeries("Productos");
        GraficaDAO dao = new GraficaDAO();
        List<GraficaDTO> lista = new ArrayList<>();
        lista = dao.graficaCategoria();
        
        for (GraficaDTO g : lista) {
            productos.set(g.getNombre(),g.getCantidad() );
        }
        
        productosCategoria.addSeries(productos);
        
    }

    public BarChartModel getProductosCategoria() {
        return productosCategoria;
    }

    public void setProductosCategoria(BarChartModel productosCategoria) {
        this.productosCategoria = productosCategoria;
    }

    
    
    
}
