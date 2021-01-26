package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.dto.GraficaDTO;
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
public class GraficaDAO {

    public List graficaCategoria() {
        
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        List lista =  new ArrayList();
        
        try{
            t.begin();
            Query q = s.createQuery("from Categoria");
            for (Categoria c : (List<Categoria>) q.list()) {
                String query = "select count(*) from Producto p where p.categoria="+c.getId();
                Query qq = s.createQuery(query);
                GraficaDTO dto = new GraficaDTO();
                dto.setNombre(c.getNombre());
                int i = Integer.parseInt(qq.getSingleResult().toString());
                dto.setCantidad(i);
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
        GraficaDAO dao =  new GraficaDAO();
        
        System.out.println(dao.graficaCategoria());
        //System.out.println(dao.read(dto));
        //dao.create(dto);
        //dao.delete(dto);
        //dao.update(dto);
        //System.out.println(dao.read2(2));
        
    }
    
}
