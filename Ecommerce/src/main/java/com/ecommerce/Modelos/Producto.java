package com.ecommerce.Modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Producto") 
public class Producto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum TipoProducto{
		simple,
		descuento
	}
	
    @Id
    @Column(name = "idProducto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducto;
    
	private String sku;
	private String nombre;
	private String descripcion;
	private Double precio;
	private TipoProducto tipo;
	private int stock;
	
	public int getIdproducto() {
		return idProducto;
	}
	public void setIdproducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public TipoProducto getTipo() {
		return tipo;
	}
	public void setTipo(TipoProducto tipo) {
		this.tipo = tipo;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public Producto(int idProducto) {
		this.idProducto = idProducto;
	}
	
	public Producto() {
		this.idProducto = 0;
		this.sku = "";
		this.nombre = "";
        this.descripcion = "";
        this.precio = 0.0;
        this.stock = 0;
	}
	
	public Producto(int idProducto, String sku, String nombre, 
					String descripcion, Double precio, int stock, 
					TipoProducto tipo) {
		this.idProducto = idProducto;
		this.sku = sku;
		this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.tipo = tipo;
    }
	
	public Producto(int idProducto, String sku, String nombre, 
					String descripcion, Double precio) {
		this.idProducto = idProducto;
		this.sku = sku;
		this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
	
	public Producto(String sku, String nombre, String descripcion, 
					Double precio, TipoProducto tipo, int stock) {
		this.sku = sku;
		this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipo = tipo;
        this.stock = stock;
    }
}
