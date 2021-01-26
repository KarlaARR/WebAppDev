package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.EstadoDTO;
import com.ipn.mx.modelo.entidades.Estado;
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
public class EstadoDAO {
    
    public EstadoDTO read(EstadoDTO dto){
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
    
    public String readString(Integer id){
        EstadoDTO dto = new EstadoDTO();
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
            Query q = s.createQuery("from Estado e order by e.id");
            for(Estado e : (List<Estado>) q.list()){
                EstadoDTO dto = new EstadoDTO();
                dto.setEntidad(e);
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
        EstadoDAO dao =  new EstadoDAO();
        EstadoDTO dto = new EstadoDTO();
        
        dto.getEntidad().setId(2);
        
        
        System.out.println(dao.readAll());
        //System.out.println(dao.read(dto));
        
    }
    
}
