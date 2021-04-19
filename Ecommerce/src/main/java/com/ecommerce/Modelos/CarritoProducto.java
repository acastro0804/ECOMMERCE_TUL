package com.ecommerce.Modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Carritoproducto") 
public class CarritoProducto   implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "idCarritoProducto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCarritoProducto;
    
	private int idCarrito;
	private int idProducto;
	private int cantidad;
	private Double precioProducto;
	
	public int getIdCarritoProducto() {
		return idCarritoProducto;
	}
	public void setIdCarritoProducto(int idCarritoProducto) {
		this.idCarritoProducto = idCarritoProducto;
	}
	public int getIdCarrito() {
		return idCarrito;
	}
	public void setIdCarrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(Double precioProducto) {
		this.precioProducto = precioProducto;
	}
	
	public CarritoProducto() {
		
	}
	
	public CarritoProducto(int idCarritoProducto) {
		this.idCarritoProducto = idCarritoProducto;
	}
	
	public CarritoProducto(int idCarrito, int idProducto, int cantidad, double precioProducto) {
		this.idCarrito = idCarrito;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.precioProducto = precioProducto;
	}
}