<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
  <head>
  	<title>Ecommerce :: Carrito de compras</title>
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/EstilosEcommerce.css">
    <script type="text/javascript">	    
	    function MostrarMensaje(mensajeInformativo){
	    	if(mensajeInformativo!=""){
	    		alert(mensajeInformativo);	
	    	}
	    	var criterio = document.getElementById("criterioBusqueda"); //.focus();
	    	
	    	var len = criterio.value.length;
            if (criterio.setSelectionRange) {
            	criterio.focus();
            	criterio.setSelectionRange(len, len);
            } else if (criterio.createTextRange) {
                var t = criterio.createTextRange();
                t.collapse(true);
                t.moveEnd('character', len);
                t.moveStart('character', len);
                t.select();
            }
            
	    }
	    
	    function ActualizarProductoCarrito(idCarrito, idProducto, precioProducto){
	    	var cantidad = document.getElementById("cantidad"+idProducto).value;
	    	location.href="/springapp/AgregarCarrito.htm?accion=actualizar&idCarrito="+idCarrito+"&idProducto="+idProducto+"&precioProducto="+precioProducto+"&cantidad="+cantidad;
	    }
	    
	    function BuscarProducto(idCarrito){
	    	var criterioBusqueda = document.getElementById("criterioBusqueda").value;
	    	location.href=location.href='/springapp/ModificarCarrito.htm?idCarrito='+idCarrito+"&criterioBusqueda="+criterioBusqueda;
	    }
	</script>
  </head>
  <body onload="MostrarMensaje('${model.mensajeInformativo}')">
    <h1>ECOMMERCE - TUL</h1>
    <table width="100%">
       	<tr>
    		<td></td>
    		<td></td>
    	</tr>
    	<tr>
    		<td></td>
    		<td align="right">
    			<input type="button" onclick="location.href='/springapp/Carrito.htm';" value="Atr&aacute;s"/>
    		</td>
    	</tr>
    </table>    
    
    <table>
    <tr>
    	<td><h4>Productos del Inventario</h4></td>
    	<td></td>
    	<td><h4>Carrito de Compra</h4></td>
    </tr>
    <tr>
    	<td valign="top">
    		Buscar producto:  
    		<p>
    			<input 	type="text" 
    					id="criterioBusqueda" name="criterioBusqueda" 
    					value="${model.criterioBusqueda}"
    					onkeyup="BuscarProducto(${model.carrito.idCarrito})"/>
    		</p>
		    <table class="table table-responsive table-striped" border="1">
		    <tr>
		    	<th>Producto</th>
		    	<th>Precio</th>
		    	<th>Tipo</th>
		    	<th>Stock</th>
		    	<th></th>
		    </tr>
		    <c:forEach items="${model.productos}" var="producto">
		    	<tr>
		  			<td><c:out value="${producto.sku} - ${producto.nombre}"/></td>
		  			<td align="right"><c:out value="$ ${producto.precio}"/></td>
		  			<td><c:out value="${producto.tipo}"/></td>
		  			<td><c:out value="${producto.stock}"/></td>
		  			<td>
		  				<a href="#">
		  					<input type="button" 
		  					onclick="location.href='/springapp/AgregarCarrito.htm?accion=ingreso&idCarrito=${model.carrito.idCarrito}&idProducto=${producto.idproducto}&precioProducto=${producto.precio}';" 
		  					value="Agregar a Carrito"/>
	  					</a>
	  				</td>
		  		</tr>
		    </c:forEach>
		    </table>
	    </td>
	    <td width="30"></td>
	    <td valign="top">
	    	<table class="table table-responsive table-striped" border="1">
	    		<tr>
	    			<th colspan="5">Datos del Carrito</th>
	    		</tr>
	    		<tr>
	    			<td>ID Carrito</td>
	    			<td colspan="4">${model.carrito.idCarrito}</td>
	    		</tr>
    			<tr>
	    			<td>Fecha de Creaci&oacute;n</td>
	    			<td colspan="4">${model.carrito.fechaCreacion}</td>
	    		</tr>
	    			<tr>
	    			<td>Fecha Procesamiento</td>
	    			<td colspan="4">${model.carrito.fechaProcesamiento}</td>
	    		</tr>
    			<tr>
	    			<td>Estado</td>
	    			<td colspan="4">${model.carrito.estado}</td>
	    		</tr>
	    		<tr>
	    			<td colspan="5"><strong>Productos</strong></td>
	    		</tr>
	    		<tr>
	    			<td>Producto</td>
	    			<td>Precio</td>
	    			<td>Cantidad</td>
	    			<td>Total Producto</td>
	    			<td></td>
	    		</tr>
	    		
	    		<c:set var="totalCarrito" scope="page" value="0"/>
	    		<c:forEach items="${model.productosCarrito}" var="procarrito">
	    		<tr>
    				<td>
    					<c:out value="${procarrito[2]} - ${procarrito[1]}"/>
   					</td>
    				<td align="right">
    					$ <c:out value="${procarrito[3]}"/>
   					</td>
    				<td>
    					<input type="text" 
    						name="cantidad<c:out value="${procarrito[0]}"/>" 
    						id="cantidad<c:out value="${procarrito[0]}"/>" 
    						value="<c:out value="${procarrito[4]}"/>" 
    						size="4"
    						onkeyup="ActualizarProductoCarrito(${model.carrito.idCarrito}, ${procarrito[0]}, ${procarrito[3]})"/>
   					</td>
   					<td>
   						$ <input type="text" 
    						name="totalProducto<c:out value="${procarrito[0]}"/>" 
    						id="totalProducto<c:out value="${procarrito[0]}"/>" 
    						value="${procarrito[3] * procarrito[4]}" 
    						size="7"/>
   					</td>
   					<td>
  						<input type="button" 
  							onclick="location.href='/springapp/QuitarProductoCarrito.htm?idCarrito=${model.carrito.idCarrito}&idProducto=${procarrito[0]}';" 
	  						value="Quitar"/>
   					</td>
	    		</tr>
	    		<c:set var="totalCarrito" value="${totalCarrito + (procarrito[3] * procarrito[4])}" scope="page"/>
	    		</c:forEach>
	    		<tr>
	    			<td></td>
	    			<td></td>
	    			<td>Total: </td>
	    			<td>
	    				$ 
	    				<c:out value="${totalCarrito}"/>
    				</td>
	    			<td></td>
	    		</tr>
	    		<tr>
	    			<td align="right" colspan="5">
						<input 	type="button" 
  								onclick="location.href='/springapp/Checkout.htm?idCarrito=${model.carrito.idCarrito}';" 
	  							value="Checkout"/>
	    			</td>
	    		</tr>
	    	</table>
	    </td>
   </tr>
   </table>
  </body>
</html>