<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
  <head>
  	<title>Ecommerce :: Agregar/Modificar Producto</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/EstilosEcommerce.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
	    function validarSoloNumero(e) {
	        tecla = (document.all) ? e.keyCode : e.which;
	        //Tecla de retroceso para borrar, siempre la permite
	        if (tecla == 8) {
	            return true;
	        }
	        // Patron de entrada, en este caso solo acepta numeros
	        patron = /[0-9]/;
	        tecla_final = String.fromCharCode(tecla);
	        return patron.test(tecla_final);
	    }
	    
	    function validarValor(e) {
	        tecla = (document.all) ? e.keyCode : e.which;
	        //Tecla de retroceso para borrar, siempre la permite
	        if (tecla == 8) {
	            return true;
	        }
	        // Patron de entrada, en este caso solo acepta numeros
	        patron = /[0-9].[0-9]/;
	        tecla_final = String.fromCharCode(tecla);
	        return patron.test(tecla_final);
	    }
    </script>
  </head>
  <body>
    <h1>ECOMMERCE - TUL</h1>
    <br/><br/>
    <form action="GuardarProducto.htm" method="post">
	    <table width="500" cellpadding="10" cellspacing="6">
	    	<tr>
	    		<td colspan="2"><h4>Formulario ${model.titulo}</h4></td>
	    	</tr>
	    	<tr>
	    		<td>SKU</td>
	    		<td>
					<c:choose>
						<c:when test="${model.tipoAccion.equals('agregar')}">
							<input type="text" name="skuProducto" id="skuProducto" value=""/>
						</c:when>
						<c:when test="${model.tipoAccion.equals('actualizar')}">
							${model.producto.sku}
							<input type="hidden" name="skuProducto" id="skuProducto" value="${model.producto.sku}"/>
						</c:when>
						<c:otherwise>
							________
						</c:otherwise>
					</c:choose>
    			</td>
	    	</tr>
	    	<tr>
	    		<td>Nombre del producto</td>
	    		<td><input type="text" name="nombreProducto" id="nombreProducto" value="${model.producto.nombre}"/></td>
	    	</tr>
	    	<tr>
	    		<td>Descripci&oacute;n</td>
	    		<td><input type="text" name="descripcionProducto" id="descripcionProducto" value="${model.producto.descripcion}"/></td>
	    	</tr>
	    	<tr>
	    		<td>Precio</td>
	    		<td><input type="text" name="precioProducto" id="precioProducto" 
	    			value="${model.producto.precio}" onkeypress="return validarSoloNumero(event)"/></td>
	    	</tr>
	    	<tr>
	    		<td>Tipo</td>
	    		<td>
	    			<select name="tipoProducto" id="tipoProducto">
	    				<option value="1">Simple</option>
	    				<option value="2">Descuento</option>	
	    			</select>
				</td>
	    	</tr>
	    	<tr>
	    		<td>Stock</td>
	    		<td><input type="text" name="stockProducto" id="stockProducto" 
	    			value="${model.producto.stock}" onkeypress="return validarSoloNumero(event)"/></td>
	    	</tr>
	    	<tr>
	    		<td></td>
	    		<td><br/>
	    			<input type="submit" value="${model.tituloBoton}"/>
	    			<input type="button" onclick="location.href='/springapp/Productos.htm';" value="Atr&aacute;s"/>
    			</td>
	    	</tr>
	    </table>
	    <input type="hidden" name="tipoAccion" id="tipoAccion" value="${model.tipoAccion}"/>
	    <input type="hidden" name="idProducto" id="idProducto" value="${model.producto.idproducto}"/>
	    <br/>
	    ${model.mensaje}
   	</form>
    <br/>    
    
  </body>
</html>