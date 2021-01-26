package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.ProductoDTO;
import com.ipn.mx.modelo.entidades.Producto;
import com.ipn.mx.modelo.entidades.Categoria;
import com.ipn.mx.utilerias.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author karla
 */
public class ProductoDAO {
    
    public void create(ProductoDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try{
            t.begin();
            s.save(dto.getEntidad());
            t.commit();
        }catch(HibernateException he){
            if(t != null && t.isActive())
                t.rollback();
        }
    }
    
    public void update(ProductoDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try{
            t.begin();
            s.update(dto.getEntidad());
            t.commit();
        }catch(HibernateException he){
            if(t != null && t.isActive())
                t.rollback();
        }
    }
    
    public void delete(ProductoDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try{
            t.begin();
            s.delete(dto.getEntidad());
            t.commit();
        }catch(HibernateException he){
            if(t != null && t.isActive())
                t.rollback();
        }
    }
    
    public ProductoDTO read(ProductoDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try{
            t.begin();
            dto.setEntidad(s.get(dto.getEntidad().getClass(), dto.getEntidad().getId()));
            dto.setEntidad(dto.getEntidad());
            t.commit();
        }catch(HibernateException he){
            if(t != null && t.isActive())
                t.rollback();
        }
        
        return dto;
    }
    
    public List readAll(){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        List lista =  new ArrayList();
        try{
            t.begin();
            //select * from Usuario u order by u.idUsuario
            Query q = s.createQuery("from Producto p order by p.id");
            for(Producto p : (List<Producto>) q.list()){
                ProductoDTO dto = new ProductoDTO();
                dto.setEntidad(p);
                lista.add(dto);
            }
            t.commit();
        }catch(HibernateException he){
            if(t != null && t.isActive())
                t.rollback();
        }
        
        return lista;
    }
    
    public static void main(String[] args) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto =  new ProductoDTO();
        
        dto.getEntidad().setCantidad(10);
        dto.getEntidad().setCategoria(1);
        dto.getEntidad().setDescripcion("dawdaae");
        dto.getEntidad().setImagen("Dawdaw");
        dto.getEntidad().setNombre("adfaerfr");
        
        System.out.println(dao.readAll());
        //System.out.println(dao.read(dto));
        //dao.create(dto);
        //dao.delete(dto);
        //dao.update(dto);
    }
}
