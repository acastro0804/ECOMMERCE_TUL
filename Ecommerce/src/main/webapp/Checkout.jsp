<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
  <head>
  	<title>Ecommerce :: Carrito de compras Checkout</title>
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/EstilosEcommerce.css">
    <script type="text/javascript">	    
	    function MostrarMensaje(mensajeInformativo){
	    	if(mensajeInformativo!=""){
	    		alert(mensajeInformativo);	
	    	}            
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
    	<td><h4>Carrito de Compra - Checkout</h4></td>
    </tr>
    <tr>
	    <td valign="top">
	    	<table class="table table-responsive table-striped" border="1">
	    		<tr>
	    			<th colspan="4">Datos del Carrito</th>
	    		</tr>
	    		<tr>
	    			<td>ID Carrito</td>
	    			<td colspan="3">${model.carrito.idCarrito}</td>
	    		</tr>
    			<tr>
	    			<td>Fecha de Creaci&oacute;n</td>
	    			<td colspan="4">${model.carrito.fechaCreacion}</td>
	    		</tr>
	    			<tr>
	    			<td>Fecha Procesamiento</td>
	    			<td colspan="3">${model.carrito.fechaProcesamiento}</td>
	    		</tr>
    			<tr>
	    			<td>Estado</td>
	    			<td colspan="3">${model.carrito.estado}</td>
	    		</tr>
	    		<tr>
	    			<td colspan="4"><strong>Productos</strong></td>
	    		</tr>
	    		<tr>
	    			<td>Producto</td>
	    			<td>Precio</td>
	    			<td>Cantidad</td>
	    			<td>Total Producto</td>
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
    					<c:out value="${procarrito[4]}"/>
   					</td>
   					<td>
   						$ ${procarrito[3] * procarrito[4]}
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
	    		</tr>
	    		<tr>
	    			<td align="right" colspan="4">
						<input 	type="button" 
  								onclick="location.href='/springapp/CheckoutProcesar.htm?idCarrito=${model.carrito.idCarrito}';" 
	  							value="Procesar"/>
	    			</td>
	    		</tr>
	    	</table>
	    </td>
   </tr>
   </table>
  </body>
</html>