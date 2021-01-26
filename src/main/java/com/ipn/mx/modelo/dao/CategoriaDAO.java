package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.CategoriaDTO;
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
public class CategoriaDAO {
    
    public void create(CategoriaDTO dto){
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
    
    public void update(CategoriaDTO dto){
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
    
    public void delete(CategoriaDTO dto){
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
    
    public CategoriaDTO read(CategoriaDTO dto){
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
    
    public String read2(Integer id){
        System.out.println(id);
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setId(id);
        
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
        
        return dto.getEntidad().getNombre();
    }

    
    public List readAll(){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        List lista =  new ArrayList();
        try{
            t.begin();
            //select * from Usuario u order by u.idUsuario
            Query q = s.createQuery("from Categoria c order by c.id");
            System.out.println(q.list());
            for(Categoria c : (List<Categoria>) q.list()){
                CategoriaDTO dto = new CategoriaDTO();
                dto.setEntidad(c);
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
        CategoriaDAO dao =  new CategoriaDAO();
        CategoriaDTO dto =  new CategoriaDTO();
        
        dto.getEntidad().setId(2);
        
        System.out.println(dao.readAll());
        //System.out.println(dao.read(dto));
        //dao.create(dto);
        //dao.delete(dto);
        //dao.update(dto);
        //System.out.println(dao.read2(2));
        
    }
    
}
