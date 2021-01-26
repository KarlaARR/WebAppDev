package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.TipoUsuarioDTO;
import com.ipn.mx.modelo.entidades.TipoUsuario;
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
public class TipoUsuarioDAO {
    
    public TipoUsuarioDTO read(TipoUsuarioDTO dto){
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
        TipoUsuarioDTO dto = new TipoUsuarioDTO();
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
            Query q = s.createQuery("from TipoUsuario t order by t.id");
            for(TipoUsuario tu : (List<TipoUsuario>) q.list()){
                TipoUsuarioDTO dto = new TipoUsuarioDTO();
                dto.setEntidad(tu);
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
        TipoUsuarioDAO dao =  new TipoUsuarioDAO();
        TipoUsuarioDTO dto = new TipoUsuarioDTO();
        
        dto.getEntidad().setId(2);
        
        
        //System.out.println(dao.readAll());
        //System.out.println(dao.read(dto));
        System.out.println(dao.readString(1));
        
    }
    
}
