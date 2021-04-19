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
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.Modelos.Producto;
import com.ecommerce.Modelos.Producto.TipoProducto;
import com.ecommerce.Servicios.ProductoDao;

@Controller
public class ProductoController {

    protected final Log logger = LogFactory.getLog(getClass());
    private static ApplicationContext context;
    private static ProductoDao productoDao;
    
    
    ///####################################################################################
    ///####################################################################################
    @RequestMapping(value="/Productos.htm")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        productoDao = (ProductoDao) context.getBean("productoDao");

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("productos", productoDao.ObtenerListaProductos(""));

        return new ModelAndView("producto.jsp", "model", myModel);
    }
    
    
    
    ///####################################################################################
    ///####################################################################################
    @RequestMapping(value="/AgregarProductos.htm")
    public ModelAndView handleRequest1(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	Producto producto = new Producto();
    	logger.info("Página de agregar nuevo producto");
    	Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("titulo", "ingresar producto");
        myModel.put("tituloBoton", "Registrar");
        myModel.put("mensaje", "");
        myModel.put("producto", producto);
        myModel.put("tipoAccion", "agregar");
        return new ModelAndView("mantenimientoproducto.jsp", "model", myModel);
    }
    
    
    
    ///####################################################################################
    ///####################################################################################
    @RequestMapping(value="/ActualizarProductos.htm")
    public ModelAndView handleRequest2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	Producto producto = new Producto();
    	logger.info("Página para actualizar producto");
    	context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        productoDao = (ProductoDao) context.getBean("productoDao");
        int idProducto = Integer.parseInt(request.getParameter("idProducto").toString());
        producto = productoDao.ObtenerProducto(idProducto);
        
    	Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("titulo", "actualizar producto");
        myModel.put("tituloBoton", "Actualizar");
        myModel.put("mensaje", "");
        myModel.put("producto", producto);
        myModel.put("tipoAccion", "actualizar");
        return new ModelAndView("mantenimientoproducto.jsp", "model", myModel);
    }
    
    
    
    ///####################################################################################
    ///####################################################################################
    @RequestMapping(value="/GuardarProducto.htm")
    public ModelAndView handleRequest3(
    		HttpServletRequest request, 
    		HttpServletResponse response)
    throws ServletException, IOException {

    	int idProducto = 0;
    	Map<String, Object> myModel = new HashMap<String, Object>();
    	context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        productoDao = (ProductoDao) context.getBean("productoDao");
        
    	String mensajeRegistro = new String();
    	logger.info("Página de guardar nuevo producto");
    	//String idProductoStr = request.getParameter("idProducto");
    	if( request.getParameter("idProducto") != null ) {
    		idProducto = Integer.parseInt(request.getParameter("idProducto"));	
    	}
    	
    	String sku = request.getParameter("skuProducto");
    	String nombre = request.getParameter("nombreProducto");
    	String descripcion = request.getParameter("descripcionProducto");
    	double precio = Double.parseDouble(request.getParameter("precioProducto"));
    	int tipo = Integer.parseInt(request.getParameter("tipoProducto"));
    	int stock = Integer.parseInt(request.getParameter("stockProducto"));
    	String tipoAccion = request.getParameter("tipoAccion");
    	
        Producto producto = new Producto();
        producto.setSku(sku);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        if( tipo == 1 ) { producto.setTipo(TipoProducto.simple); 
        }else if( tipo == 2 ) { producto.setTipo(TipoProducto.descuento); }
        producto.setStock(stock);
        
        if( tipoAccion.equals("agregar")){

        	//Se verifica que el SKU ingresado esté disponible
        	if( productoDao.VerificarProducto(sku).size() > 0 ) {
    	        myModel.put("titulo", "ingresar producto");
    	        myModel.put("tituloBoton", "Registrar");
    	        myModel.put("tipoAccion", "agregar");
                myModel.put("mensaje", "Estimado usuario, el SKU ya esta asignado a otro producto");
                myModel.put("producto", producto);
                return new ModelAndView("mantenimientoproducto.jsp", "model", myModel);
        	}
        	
	        if( productoDao.AgregarProducto(producto) ) {
	        	mensajeRegistro = "Producto registrado Correctamente";
	        	producto = productoDao.ObtenerProducto(sku);
	        }else {
	        	mensajeRegistro = "Estimado usuario, hubo un inconveniente al registrar el producto";
	        }
	        myModel.put("titulo", "ingresar producto");
	        myModel.put("tituloBoton", "Registrar");
	        myModel.put("tipoAccion", "agregar");
	        
        }else  if( tipoAccion.equals("actualizar") ) {
        	
        	producto.setIdproducto(idProducto);
	        if( productoDao.ModificarProducto(producto) ) {
	        	mensajeRegistro = "Producto actualizado Correctamente";
	        	producto = productoDao.ObtenerProducto(sku);
	        }else {
	        	mensajeRegistro = "Estimado usuario, hubo un inconveniente al actualizar el producto";
	        }
	        myModel.put("titulo", "actualizar producto");
	        myModel.put("tituloBoton", "Actualizar");
	        myModel.put("tipoAccion", "actualizar");
        }
        myModel.put("mensaje", mensajeRegistro);
        myModel.put("producto", producto);
        return new ModelAndView("mantenimientoproducto.jsp", "model", myModel);
    }
    
    
    
    ///####################################################################################
    ///####################################################################################
    @RequestMapping(value="/EliminarProducto.htm")
    public ModelAndView handleRequest4(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String mensajeRegistro;
    	Producto producto = new Producto();
    	logger.info("Página para eliminar producto");
    	context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        productoDao = (ProductoDao) context.getBean("productoDao");
        
        int idProducto = Integer.parseInt(request.getParameter("idProducto").toString());
        logger.info("Obtener producto a elimianr -> idproducto: " + idProducto);
        producto = productoDao.ObtenerProducto(idProducto);
        logger.info("Producto obtenido para eliminar sku: " + producto.getNombre());
        if( productoDao.EliminarProducto(producto) ) {
        	mensajeRegistro = "Producto eliminado Correctamente";
            Map<String, Object> myModel = new HashMap<String, Object>();
            myModel.put("productos", productoDao.ObtenerListaProductos(""));

            return new ModelAndView("producto.jsp", "model", myModel);
            
        }else {
        	mensajeRegistro = "Estimado usuario, hubo un inconveniente al eliminar el producto";
        }
        return new ModelAndView("error.jsp", "mensaje", mensajeRegistro);
    }
    
    
}