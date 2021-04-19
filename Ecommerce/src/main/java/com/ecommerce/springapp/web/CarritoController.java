package com.ecommerce.springapp.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.Modelos.Carrito;
import com.ecommerce.Modelos.CarritoProducto;
import com.ecommerce.Servicios.CarritoDao;
import com.ecommerce.Servicios.CarritoProductoDao;
import com.ecommerce.Servicios.ProductoDao;

@Controller
public class CarritoController {

    protected final Log logger = LogFactory.getLog(getClass());
    private static ApplicationContext context;
    private static CarritoDao carritoDao;
    private static ProductoDao productoDao;
    private static CarritoProductoDao carritoproductoDao;
    
    @RequestMapping(value="/Carrito.htm")
    public ModelAndView handleRequest(
    		HttpServletRequest request, 
    		HttpServletResponse response,
    		@RequestParam(name = "pageNumber", defaultValue = "0") final int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") final int pageSize
    )
            throws ServletException, IOException {

        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        carritoDao = (CarritoDao) context.getBean("carritoDao");
        logger.info("Principal de carrito de compra. NumeroPagina: "+pageNumber+" TamañoPagina:"+pageSize);

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("carritos", carritoDao.ObtenerListaCarritos());
        logger.info("Se realizó la consulta de listado de carritos registrados");
        
        return new ModelAndView("carrito.jsp", "model", myModel);
    }
    
    
    
    @RequestMapping(value="/LlenarCarrito.htm")
    public ModelAndView handleRequest2(
    		HttpServletRequest request, 
    		HttpServletResponse response
    )
            throws ServletException, IOException {

    	String mensajeRegistro = new String();
    	Carrito carrito = new Carrito();
        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        carritoDao = (CarritoDao) context.getBean("carritoDao");
        if( !carritoDao.AgregarCarrito(carrito) ) {
        	mensajeRegistro = "Estimado usuario, hubo un inconveniente al registrar el producto";
        }
        
    	//Se realiza la consulta de productos siempre para mantener al día precios, stock
        productoDao = (ProductoDao) context.getBean("productoDao");
        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("productos", productoDao.ObtenerListaProductos(""));
        myModel.put("carrito", carrito);

        return new ModelAndView("llenacarrito.jsp", "model", myModel);
    }
    
    
    @RequestMapping(value="/ModificarCarrito.htm")
    public ModelAndView handleRequest3(
    		HttpServletRequest request, 
    		HttpServletResponse response
    )
            throws ServletException, IOException {

    	String criterioBusqueda = new String();
    	Carrito carrito = new Carrito();
    	Map<String, Object> myModel = new HashMap<String, Object>();
    	String mensajeInformativo = new String();
    	int idCarrito = Integer.parseInt(request.getParameter("idCarrito"));
    	try{ 
    		criterioBusqueda = request.getParameter("criterioBusqueda");
    		if( criterioBusqueda == null ) { criterioBusqueda = "";}
    	}catch(Exception ex) { criterioBusqueda = ""; }
    	
        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        
        //Se consulta los productos que tiene asignada el carrito actualmente
        carritoproductoDao = (CarritoProductoDao) context.getBean("carritoproductoDao");
        List<Object[]> listaProductosCarrito = carritoproductoDao.ObtenerProductosCarritoProducto(idCarrito);
        
        //Se consulta datos del carrito
        carritoDao = (CarritoDao) context.getBean("carritoDao");
        carrito = carritoDao.ObtenerCarrito(idCarrito);
        
    	//Se realiza la consulta de productos siempre para mantener al día precios, stock
        productoDao = (ProductoDao) context.getBean("productoDao");
        
        myModel.put("productos", productoDao.ObtenerListaProductos(criterioBusqueda));
        myModel.put("carrito", carrito);
        myModel.put("mensajeInformativo", mensajeInformativo);
        myModel.put("productosCarrito", listaProductosCarrito);
        myModel.put("criterioBusqueda", criterioBusqueda);

        return new ModelAndView("llenacarrito.jsp", "model", myModel);
    }
    
    
    @RequestMapping(value="/AgregarCarrito.htm")
    public ModelAndView handleRequest4(
    		HttpServletRequest request, 
    		HttpServletResponse response
    )
            throws ServletException, IOException {

    	String accion = new String();
    	int cantidad = 0;
    	Carrito carrito = new Carrito();
    	Map<String, Object> myModel = new HashMap<String, Object>();
    	String mensajeInformativo = new String();
    	accion = request.getParameter("accion");
    	int idCarrito = Integer.parseInt(request.getParameter("idCarrito"));
    	int idProducto = Integer.parseInt(request.getParameter("idProducto"));
    	if( accion.equals("actualizar") ) {
    		try{ cantidad = Integer.parseInt(request.getParameter("cantidad")); }catch(Exception ex){ }
        }
    	double precioProducto = Double.parseDouble(request.getParameter("precioProducto"));
    	
        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        carritoproductoDao = (CarritoProductoDao) context.getBean("carritoproductoDao");
        CarritoProducto carritoProducto;

        if( accion.equals("ingreso") ) {
        	
        	//preguntamos si el carrito ya tiene el producto a ingresar
        	carritoProducto = carritoproductoDao.ObtenerProductoCarritoProducto(idCarrito, idProducto);
        	if( carritoProducto != null) {
        		
        		mensajeInformativo = "Estimado usuario, producto ya está ingresado al carrito";
        		
        	}else {
        		carritoProducto = new CarritoProducto();
                carritoProducto.setIdCarrito(idCarrito);
                carritoProducto.setIdProducto(idProducto);
                carritoProducto.setCantidad(cantidad);
                carritoProducto.setPrecioProducto(precioProducto);
            	//Agregamos el producto al carrito
    	        if( !carritoproductoDao.AgregarProductoToCarritoProducto(carritoProducto) ) {
    	        	mensajeInformativo = "Estimado usuario, hubo un inconveniente al colocar producto del carrito";
    	        }
        	}
        	

        }else if( accion.equals("actualizar") ) {
        	if( !carritoproductoDao.ModificarCantidadProductoToCarritoProducto(idCarrito, idProducto, cantidad) ) {
	        	mensajeInformativo = "Estimado usuario, hubo un inconveniente al colocar producto del carrito";
	        }
        }

        //Se consulta los productos que tiene asignada el carrito actualmente
        List<Object[]> listaProductosCarrito = carritoproductoDao.ObtenerProductosCarritoProducto(idCarrito);
        
        carrito = carritoDao.ObtenerCarrito(idCarrito);
    	//Se realiza la consulta de productos siempre para mantener al día precios, stock
        productoDao = (ProductoDao) context.getBean("productoDao");
        myModel.put("productos", productoDao.ObtenerListaProductos(""));
        myModel.put("carrito", carrito);
        myModel.put("mensajeInformativo", mensajeInformativo);
        myModel.put("productosCarrito", listaProductosCarrito);

        return new ModelAndView("llenacarrito.jsp", "model", myModel);
    }
    
    
    @RequestMapping(value="/QuitarProductoCarrito.htm")
    public ModelAndView handleRequest5(
    		HttpServletRequest request, 
    		HttpServletResponse response
    )
            throws ServletException, IOException {

    	Carrito carrito = new Carrito();
    	Map<String, Object> myModel = new HashMap<String, Object>();
    	String mensajeInformativo = new String();
    	int idCarrito = Integer.parseInt(request.getParameter("idCarrito"));
    	int idProducto = Integer.parseInt(request.getParameter("idProducto"));
    	
        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        
        //Se consulta los productos que tiene asignada el carrito actualmente
        carritoproductoDao = (CarritoProductoDao) context.getBean("carritoproductoDao");
        if( carritoproductoDao.EliminarProductoToCarritoProducto(idCarrito, idProducto) == 0 ) {
        	mensajeInformativo = "Estimado usuario, hubo un inconveniente al eliminar el producto del carrito";
        }
        
        carrito = carritoDao.ObtenerCarrito(idCarrito);
        
        //Se consulta los productos que tiene asignada el carrito actualmente
        carritoproductoDao = (CarritoProductoDao) context.getBean("carritoproductoDao");
        List<Object[]> listaProductosCarrito = carritoproductoDao.ObtenerProductosCarritoProducto(idCarrito);
        
    	//Se realiza la consulta de productos siempre para mantener al día precios, stock
        productoDao = (ProductoDao) context.getBean("productoDao");
        
        myModel.put("productos", productoDao.ObtenerListaProductos(""));
        myModel.put("carrito", carrito);
        myModel.put("mensajeInformativo", mensajeInformativo);
        myModel.put("productosCarrito", listaProductosCarrito);

        return new ModelAndView("llenacarrito.jsp", "model", myModel);
    }
    
    
    
    @RequestMapping(value="/EliminarCarrito.htm")
    public ModelAndView handleRequest6(
    		HttpServletRequest request, 
    		HttpServletResponse response
    )
            throws ServletException, IOException {

    	Carrito carrito = new Carrito();
    	CarritoProducto carpro = new CarritoProducto();
    	Map<String, Object> myModel = new HashMap<String, Object>();
    	String mensajeInformativo = new String();
    	int idCarrito = Integer.parseInt(request.getParameter("idCarrito"));
    	
        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        
        //Eliminar carrito producto
        carritoproductoDao = (CarritoProductoDao) context.getBean("carritoproductoDao");
        if( carritoproductoDao.EliminarCarritoProducto(idCarrito) != -1 ) {
        	
	        //Eliminar carrito
        	carritoDao = (CarritoDao) context.getBean("carritoDao");
        	carrito = carritoDao.ObtenerCarrito(idCarrito);
	        if( !carritoDao.EliminarCarrito(carrito) ) {
	        	mensajeInformativo = "Estimado usuario, hubo un inconveniente al eliminar el producto del carrito";
	        }
	        
        }else {
        	mensajeInformativo = "Estimado usuario, hubo un inconveniente al eliminar el producto del carrito.";
        }
        
        myModel.put("carritos", carritoDao.ObtenerListaCarritos());
        myModel.put("mensajeInformativo", mensajeInformativo);
        return new ModelAndView("carrito.jsp", "model", myModel);
    }
    
 
    
    @RequestMapping(value="/Checkout.htm")
    public ModelAndView handleRequest7(
    		HttpServletRequest request, 
    		HttpServletResponse response
    )
            throws ServletException, IOException {

    	Carrito carrito = new Carrito();
    	Map<String, Object> myModel = new HashMap<String, Object>();
    	String mensajeInformativo = new String();
    	int idCarrito = Integer.parseInt(request.getParameter("idCarrito"));
    	
        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        //Se consulta los productos que tiene asignada el carrito actualmente
        carritoproductoDao = (CarritoProductoDao) context.getBean("carritoproductoDao");
        List<Object[]> listaProductosCarrito = carritoproductoDao.ObtenerProductosCarritoProducto(idCarrito);
        
        //Se consulta datos del carrito
        carritoDao = (CarritoDao) context.getBean("carritoDao");
        carrito = carritoDao.ObtenerCarrito(idCarrito);
        
        myModel.put("carrito", carrito);
        myModel.put("mensajeInformativo", mensajeInformativo);
        myModel.put("productosCarrito", listaProductosCarrito);

        return new ModelAndView("Checkout.jsp", "model", myModel);
    }
    
    
    
    @RequestMapping(value="/CheckoutProcesar.htm")
    public ModelAndView handleRequest8(
    		HttpServletRequest request, 
    		HttpServletResponse response
    )
            throws ServletException, IOException {

    	Carrito carrito = new Carrito();
    	Map<String, Object> myModel = new HashMap<String, Object>();
    	String mensajeInformativo = new String();
    	int idCarrito = Integer.parseInt(request.getParameter("idCarrito"));
    	
        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        //Se consulta los productos que tiene asignada el carrito actualmente
        carritoproductoDao = (CarritoProductoDao) context.getBean("carritoproductoDao");
        if( carritoproductoDao.CheckoutCarritoProducto(idCarrito) > 0 ) {
        	mensajeInformativo = "Checkout realizado correctamente";
        }
        
        //Se consulta los productos que tiene asignada el carrito actualmente
        List<Object[]> listaProductosCarrito = carritoproductoDao.ObtenerProductosCarritoProducto(idCarrito);
        
        //Se consulta datos del carrito
        carritoDao = (CarritoDao) context.getBean("carritoDao");
        carrito = carritoDao.ObtenerCarrito(idCarrito);
        
        myModel.put("carrito", carrito);
        myModel.put("mensajeInformativo", mensajeInformativo);
        myModel.put("productosCarrito", listaProductosCarrito);
        
        return new ModelAndView("Checkout.jsp", "model", myModel);
    }
    
    
    
}