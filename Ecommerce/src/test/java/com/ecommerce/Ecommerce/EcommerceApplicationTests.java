package com.ecommerce.Ecommerce;

import java.util.List;

//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecommerce.Modelos.Producto;
import com.ecommerce.Servicios.ProductoDao;


public class EcommerceApplicationTests {

    private static ApplicationContext context;
    private static ProductoDao productoDao;
    
    //@BeforeAll
    public static void setUp() throws Exception {
    	try {
	        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
	        productoDao = (ProductoDao) context.getBean("productoDao");
		}catch(Exception ex) {
			System.out.println("ERROR XXXX: " + ex.getMessage());
		}
    }
    
	//@Test
	void contextLoads() {
		try {
		//System.out.println("Prueba Ecommerce TUL");
		/*
		System.out.println("AGREGAR ###########################################################");
		Producto p = new Producto("skuprueba", "Producto Prueba", "Descripcion producto prueba", 12.0, Producto.TipoProducto.simple, 100);
		productoDao.AgregarProducto(p);
		p = new Producto("skuprueba 2", "Producto Prueba 2", "Descripcion producto prueba 2", 5.0, Producto.TipoProducto.descuento, 50);
		productoDao.AgregarProducto(p);
		
		
		Producto p1 = new Producto();
		try {
			System.out.println("OBTENER ###########################################################");
			p1 = productoDao.ObtenerProducto(57);
			System.out.println("Producto obtenido: " + p1.getNombre() + " - " + p1.getIdproducto());
		}catch(Exception e) {
			System.out.println("Error en Obtener: " + e.getMessage());
		}
		

	
		try {
			p1 = productoDao.ObtenerProducto(58);
			System.out.println("MODIFICAR ###########################################################");
			p1.setPrecio(1.0);
			productoDao.ModificarProducto(p1);
		}catch(Exception ex) {
			System.out.println("Error en Modificar: " + ex.getMessage());
		}
		
		try {
			p1 = productoDao.ObtenerProducto(59);
			System.out.println("ELIMINAR ###########################################################");
			productoDao.EliminarProducto(p1);
		}catch(Exception ex) {
			System.out.println("Error en Eliminar: " + ex.getMessage());
		}*/
		
		/*System.out.println("OBTENER MASIVO #####################################################");
		List<Producto> productos = productoDao.ObtenerListaProductos();
		for (Producto pro : productos) {
			System.out.println("Empleado " + pro.getNombre() + " - Precio: " + pro.getPrecio() + " ("+pro.getTipo()+")");
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		}
		
		System.out.println("Transacción cerrada");*/
		}catch(Exception ex) {
			
		}
	}

}
