<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
  <head>
  	<title>Ecommerce :: Carrito de compras</title>
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/EstilosEcommerce.css">
    <script type="text/javascript">
	    $('#campo_login').blur(
	        function() {
	            $('#mensaje').load('loginDisponible.do', "login="+$('#campo_login').val())
	        }   
	    )
   	    
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
    		<td>
    			<input type="button" onclick="location.href='/springapp/LlenarCarrito.htm';" value="Crear Carrito"/>
    		</td>
    		<td></td>
    	</tr>
    	<tr>
    		<td><h4>Lista  de Carritos Compras</h4></td>
    		<td align="right">
    			<input type="button" onclick="location.href='/springapp/Productos.htm';" value="Mantenimiento de Productos"/>
    		</td>
    	</tr>
    </table>    
    <table class="table table-responsive table-striped" border="1">
    <tr>
    	<th>Id</th>
    	<th>Fecha Creaci&oacute;n</th>
    	<th>Fecha Procesamiento</th>
    	<th>Estado</th>
    	<th></th>
    	<th></th>
    </tr>
    <c:forEach items="${model.carritos}" var="carrito">
    	<tr>
  			<td><c:out value="${carrito.idCarrito}"/></td>
  			<td><c:out value="${carrito.fechaCreacion}"/></td>
  			<td><c:out value="${carrito.fechaProcesamiento}"/></td>
  			<td>
				<c:if test = "${carrito.estado == 'pendiente'}">   
					<input type="button" onclick="location.href='/springapp/ModificarCarrito.htm?idCarrito=${carrito.idCarrito}';" value="Procesar"/>
				</c:if>
      			<c:if test = "${carrito.estado == 'completado'}">   
					Completado
				</c:if>
			</td>
  			<td>
				<c:if test = "${carrito.estado == 'pendiente'}">   
					<input type="button" onclick="location.href='/springapp/ModificarCarrito.htm?idCarrito=${carrito.idCarrito}';" value="Actualizar"/>
				</c:if>
      			<c:if test = "${carrito.estado == 'completado'}">   
					Actualizar
				</c:if>  			  				
			</td>
  			<td><input type="button" onclick="location.href='/springapp/EliminarCarrito.htm?idCarrito=${carrito.idCarrito}';" value="Eliminar"/></td>
  		</tr>
    </c:forEach>
    </table>
    
  </body>
</html>