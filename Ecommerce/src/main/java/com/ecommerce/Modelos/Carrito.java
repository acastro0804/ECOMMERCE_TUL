package com.ecommerce.Modelos;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Carrito") 
public class Carrito  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	enum EstadoCarrito{
		pendiente,
		completado
	}
	
    @Id
    @Column(name = "idCarrito")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCarrito;
    
	private String fechaCreacion;
	private String fechaProcesamiento;
	private EstadoCarrito estado;
	
	public int getIdCarrito() {
		return idCarrito;
	}
	public void setIdCarrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaProcesamiento() {
		return fechaProcesamiento;
	}
	public void setFechaProcesamiento(String fechaProcesamiento) {
		this.fechaProcesamiento = fechaProcesamiento;
	}
	public EstadoCarrito getEstado() {
		return estado;
	}
	public void setEstado(EstadoCarrito estado) {
		this.estado = estado;
	}
	
	public Carrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}
	
	public Carrito() {
		
		Date objetoDate = new Date();
		DateFormat formatoDiaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
		this.fechaCreacion = formatoDiaHora.format(objetoDate);
		this.fechaProcesamiento = null;
		this.estado = EstadoCarrito.pendiente;
	}
	
	public Carrito(String fechaCreacion, String fechaProcesamiento, EstadoCarrito estado) {
		this.fechaCreacion = fechaCreacion;
		this.fechaProcesamiento = fechaProcesamiento;
		this.estado = estado;
	}
	
}