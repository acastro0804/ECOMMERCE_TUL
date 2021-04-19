package com.ecommerce.ServiciosDB;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.Modelos.Producto;
import com.ecommerce.Servicios.ProductoDao;

@Repository(value = "productoDao")
public class JPAProductoDao implements ProductoDao {
	
    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
	public List<Producto> ObtenerListaProductos(String criterioBusqueda){
    	Query query = em.createQuery("select p from Producto p where p.nombre LIKE CONCAT('%',:criterioBusqueda,'%') order by p.nombre");
		query.setParameter("criterioBusqueda", criterioBusqueda);
		return query.getResultList();
	}
	
    
    @Transactional(readOnly = true)
    public Producto ObtenerProducto(String sku){
    	Query query = em.createQuery("select p from Producto p WHERE p.sku = :sku");
		query.setParameter("sku", sku);
    	return (Producto)query.getSingleResult();
	}
    
    
    @Transactional(readOnly = true)
    public List<Producto> VerificarProducto(String sku){
    	Query query = em.createQuery("select p from Producto p WHERE p.sku = :sku");
		query.setParameter("sku", sku);
    	return query.getResultList();
	}
    
    
    @Transactional(readOnly = true)
    public Producto ObtenerProducto(int idProducto){
    	Query query = em.createQuery("select p from Producto p WHERE p.idProducto = :idProducto");
		query.setParameter("idProducto", idProducto);
    	return (Producto)query.getSingleResult();
	}
    
    
    @Transactional(readOnly = false)
	public boolean AgregarProducto(Producto producto) {
		try {
	    	em.persist(producto);
			em.flush();
			em.refresh(producto);
		}catch(Exception error) {
			return false;
		}
		return true;
	}
	
	
    @Transactional(readOnly = false)
	public boolean ModificarProducto(Producto producto) {
		try {
	    	/*Producto pro = em.find(Producto.class, producto.getIdproducto());
	    	pro.setIdproducto(producto.getIdproducto());
			pro.setSku(producto.getSku());
			pro.setNombre(producto.getNombre());
			pro.setDescripcion(producto.getDescripcion());
			pro.setPrecio(producto.getPrecio());
			pro.setTipo(producto.getTipo());
			pro.setStock(producto.getStock());*/
			em.merge(producto);
			em.flush();
		}catch(Exception error) {
			return false;
		}
		return true;
	}
	
	
    @Transactional(readOnly = false)
	public boolean EliminarProducto(Producto producto) {
		try {
			Producto pro = em.find(Producto.class, producto.getIdproducto());
			em.remove(pro);
		}catch(Exception error) {
			return false;
		}
		return true;
	}
	
}
