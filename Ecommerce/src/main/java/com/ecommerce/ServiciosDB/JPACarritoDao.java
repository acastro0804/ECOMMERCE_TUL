package com.ecommerce.ServiciosDB;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.Modelos.Carrito;
import com.ecommerce.Modelos.Producto;
import com.ecommerce.Servicios.CarritoDao;

@Repository(value = "carritoDao")
public class JPACarritoDao implements CarritoDao {
	
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
	public List<Carrito> ObtenerListaCarritos(){
		return em.createQuery("select c from Carrito c").getResultList();
	}
	
	
	public Carrito ObtenerCarrito(int idCarrito) {
    	Query query = em.createQuery("select c from Carrito c WHERE c.idCarrito = :idCarrito");
		query.setParameter("idCarrito", idCarrito);
    	return (Carrito)query.getSingleResult();
	}
	
	
    @Transactional(readOnly = false)
	public boolean AgregarCarrito(Carrito carrito) {
		try {
	    	em.persist(carrito);
			em.flush();
			em.refresh(carrito);
		}catch(Exception error) {
			return false;
		}
		return true;
	}
	
	
    @Transactional(readOnly = false)
	public void ModificarCarrito(Carrito carrito) {
		Carrito car = em.find(Carrito.class, carrito.getIdCarrito());
		car.setFechaCreacion(carrito.getFechaCreacion());
		car.setFechaProcesamiento(carrito.getFechaProcesamiento());
		car.setEstado(carrito.getEstado());
		em.persist(car);
		em.flush();
		em.refresh(car);
	}
	
	
	@Transactional(readOnly = false)
	public boolean EliminarCarrito(Carrito carrito) {
		try {
			Carrito car = em.find(Carrito.class, carrito.getIdCarrito());
			em.remove(car);
		}catch(Exception error) {
			return false;
		}
		return true;
	}
	
}