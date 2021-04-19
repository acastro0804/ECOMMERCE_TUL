package com.ecommerce.ServiciosDB;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.Modelos.Carrito;
import com.ecommerce.Modelos.CarritoProducto;
import com.ecommerce.Modelos.Producto;
import com.ecommerce.Servicios.CarritoProductoDao;

@Repository(value = "carritoproductoDao")
public class JPACarritoProductoDao implements CarritoProductoDao {
	
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
	public List<CarritoProducto> ObtenerCarritosProductos(){
		return em.createQuery("select c from Carrito c").getResultList();
	}
	
    
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
	public List<Object[]> ObtenerProductosCarritoProducto(int idCarrito){
    	Query query = em.createQuery("select p.idProducto, p.nombre, p.sku, p.precio, cp.cantidad " +
    								 "from Carrito c, CarritoProducto cp, Producto p " +
									 "WHERE c.idCarrito = cp.idCarrito and cp.idProducto = p.idProducto and c.idCarrito = :idCarrito");
		query.setParameter("idCarrito", idCarrito);
    	return query.getResultList();
	}
	
	
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
	public CarritoProducto ObtenerProductoCarritoProducto(int idCarrito, int idProducto){
    	try {
	    	Query query = em.createQuery("select cp From CarritoProducto cp " +
										 "WHERE cp.idProducto = :idProducto and cp.idCarrito = :idCarrito");
			query.setParameter("idCarrito", idCarrito);
			query.setParameter("idProducto", idProducto);
	    	return (CarritoProducto)query.getSingleResult();
		}catch(Exception error) {
			return null;
		}
	}
    
	
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
	public List<CarritoProducto> ObtenerCarritoProducto(int idCarrito){
    	Query query = em.createQuery("select cp from CarritoProducto cp WHERE cp.idCarrito = :idCarrito");
		query.setParameter("idCarrito", idCarrito);
    	return query.getResultList();
	}
	
	
    @Transactional(readOnly = false)
	public int CheckoutCarritoProducto(int idCarrito) {
		try {
			Date objetoDate = new Date();
			DateFormat formatoDiaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
		   	Query query = em.createQuery("update Carrito c " +
		   			"set c.estado = 1, c.fechaProcesamiento = '" + formatoDiaHora.format(objetoDate) + "' " +
		   			"WHERE c.idCarrito = :idCarrito");
			query.setParameter("idCarrito", idCarrito);
			return query.executeUpdate();
		}catch(Exception error) {
			return -1;
		}
	}
	
	
	@Transactional(readOnly = false)
	public boolean AgregarProductoToCarritoProducto(CarritoProducto carpro) {
		try {
	    	em.persist(carpro);
			em.flush();
			em.refresh(carpro);
		}catch(Exception error) {
			return false;
		}
		return true;
	}
	
	
	@Transactional(readOnly = false)
	public boolean ModificarCantidadProductoToCarritoProducto(int idCarrito, int idproducto, int cantidad) {
		try {
			CarritoProducto carpro = this.ObtenerProductoCarritoProducto(idCarrito, idproducto);
			carpro.setCantidad(cantidad);
			em.persist(carpro);
			em.flush();
			em.refresh(carpro);
		}catch(Exception error) {
			return false;
		}
		return true;
	}
	
	
	public boolean EliminarCarritoProducto(CarritoProducto carpro) {
		try {
			CarritoProducto car = em.find(CarritoProducto.class, carpro.getIdCarritoProducto());
			em.remove(car);
		}catch(Exception error) {
			return false;
		}
		return true;
	}
	
	
	@Transactional(readOnly = false)
	public int EliminarCarritoProducto(int idCarrito) {
		try {
		   	Query query = em.createQuery("delete from CarritoProducto cp WHERE cp.idCarrito = :idCarrito");
			query.setParameter("idCarrito", idCarrito);
			return query.executeUpdate();
		}catch(Exception error) {
			return -1;
		}
	}
	
	
	@Transactional(readOnly = false)
	public int EliminarProductoToCarritoProducto(int idCarrito, int idProducto) {
	   	Query query = em.createQuery("delete from CarritoProducto cp " +
									 "WHERE cp.idCarrito = :idCarrito and cp.idProducto = :idProducto");
		query.setParameter("idCarrito", idCarrito);
		query.setParameter("idProducto", idProducto);
		return query.executeUpdate();
	}
	
	
}