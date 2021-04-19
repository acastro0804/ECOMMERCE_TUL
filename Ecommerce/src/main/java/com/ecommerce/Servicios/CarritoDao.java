package com.ecommerce.Servicios;
import java.util.List;

import com.ecommerce.Modelos.Carrito;

public interface CarritoDao {

	public List<Carrito> ObtenerListaCarritos();
	public Carrito ObtenerCarrito(int idCarrito);
	public boolean AgregarCarrito(Carrito carrito);
	public void ModificarCarrito(Carrito carrito);
	public boolean EliminarCarrito(Carrito carrito);
	
}