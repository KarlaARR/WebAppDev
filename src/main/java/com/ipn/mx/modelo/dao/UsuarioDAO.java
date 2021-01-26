package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.UsuarioDTO;
import com.ipn.mx.modelo.entidades.Usuario;
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
public class UsuarioDAO {
    
    public void create(UsuarioDTO dto){
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
    
    public void update(UsuarioDTO dto){
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
    
    public void delete(UsuarioDTO dto){
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
    
    public UsuarioDTO read(UsuarioDTO dto){
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
            Query q = s.createQuery("from Usuario u order by u.id");
            for(Usuario u : (List<Usuario>) q.list()){
                UsuarioDTO dto = new UsuarioDTO();
                dto.setEntidad(u);
                lista.add(dto);
            }
            t.commit();
        }catch(HibernateException he){
            if(t != null && t.isActive())
                t.rollback();
        }
        
        return lista;
    }
    
    public Integer validarUsuario(UsuarioDTO dto){
        
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        Integer v=0;
        String query = "from Usuario where username= '"+dto.getEntidad().getUsername()+
                "' and password='"+dto.getEntidad().getPassword()+"'";
        try {
            t.begin();
            Query q = s.createQuery(query);
            if(q.list().isEmpty())
                v= 0;
            else 
                v= 1;
            t.commit();
        } catch (HibernateException he) {
            if(t != null && t.isActive())
                t.rollback();
        }
        
        return v;
    }
    
    public static void main(String[] args) {
        UsuarioDAO dao =  new UsuarioDAO();
        UsuarioDTO dto =  new UsuarioDTO();
        
        dto.getEntidad().setId(2);
        /*dto.getEntidad().setNombres("Ernesto");
        dto.getEntidad().setPaterno("Hernandez");
        dto.getEntidad().setMaterno("Ruiz");
        dto.getEntidad().setEmail("erneto@gmail.com");
        dto.getEntidad().setTipo(3);
        dto.getEntidad().setUsername("ernestoH");
        dto.getEntidad().setPassword("123456");
        dto.getEntidad().setEstado(2);
        dto.getEntidad().setFoto("Ernesto.png");*/
        
        System.out.println(dao.readAll());
        //System.out.println(dao.read(dto));
        //dao.create(dto);
        //dao.delete(dto);
        //dao.update(dto);
    }
}
