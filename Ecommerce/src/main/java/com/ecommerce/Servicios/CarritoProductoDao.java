package com.ecommerce.Servicios;
import java.util.List;

import com.ecommerce.Modelos.CarritoProducto;
import com.ecommerce.Modelos.Producto;

public interface CarritoProductoDao {

	public List<CarritoProducto> ObtenerCarritosProductos();
	public List<Object[]> ObtenerProductosCarritoProducto(int idCarrito);
	public CarritoProducto ObtenerProductoCarritoProducto(int idCarrito, int idProducto);
	public List<CarritoProducto> ObtenerCarritoProducto(int idCarrito);
	public int CheckoutCarritoProducto(int idCarrito);
	public boolean AgregarProductoToCarritoProducto(CarritoProducto carpro);
	public boolean ModificarCantidadProductoToCarritoProducto(int idCarrito, int idproducto, int cantidad);
	public boolean EliminarCarritoProducto(CarritoProducto carpro);
	public int EliminarCarritoProducto(int idCarrito);
	public int EliminarProductoToCarritoProducto(int idCarrito, int idProducto);
	
}