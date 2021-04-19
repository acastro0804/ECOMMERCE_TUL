package com.ecommerce.Servicios;
import java.util.List;

import com.ecommerce.Modelos.Producto;

public interface ProductoDao {

	public List<Producto> ObtenerListaProductos(String criterioBusqueda);
	public Producto ObtenerProducto(String sku);
	public Producto ObtenerProducto(int idProducto);
	public List<Producto> VerificarProducto(String sku);
	public boolean AgregarProducto(Producto producto);
	public boolean ModificarProducto(Producto producto);
	public boolean EliminarProducto(Producto producto);
	
}