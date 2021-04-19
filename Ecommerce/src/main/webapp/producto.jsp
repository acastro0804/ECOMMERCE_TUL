<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
  <head>
  	<title>Ecommerce :: Productos</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/EstilosEcommerce.css">
  </head>
  <body>
    <h1>ECOMMERCE - TUL</h1>
    <table width="100%">
       	<tr>
    		<td>
    			<input type="button" onclick="location.href='/springapp/AgregarProductos.htm';" value="Agregar Producto"/>
    		</td>
    		<td></td>
    	</tr>
    	<tr>
    		<td><h4>Listado de productos registrados</h4></td>
    		<td align="right">
    			<input type="button" onclick="location.href='/springapp/Carrito.htm';" value="Listado de Carritos de Compra"/>
    		</td>
    	</tr>
    </table>
    <table class="table table-responsive table-striped" border="1">
    <tr>
    	<th>sku</th>
    	<th>Nombre</th>
    	<th>Descripci&oacute;n</th>
    	<th>Precio</th>
    	<th>Tipo</th>
    	<th>Stock</th>
    	<th></th>
    	<th></th>
    </tr>
    <c:forEach items="${model.productos}" var="producto">
    	<tr>
  			<td><c:out value="${producto.sku}"/></td>
  			<td><c:out value="${producto.nombre}"/></td>
  			<td><c:out value="${producto.descripcion}"/></td>
  			<td><c:out value="${producto.precio}"/></td>
  			<td><c:out value="${producto.tipo}"/></td>
  			<td><c:out value="${producto.stock}"/></td>
  			<td><input type="button" onclick="location.href='/springapp/ActualizarProductos.htm?idProducto=${producto.idproducto}';" value="Actualizar"/></td>
  			<td><input type="button" onclick="location.href='/springapp/EliminarProducto.htm?idProducto=${producto.idproducto}';" value="Eliminar"/></td>
  		</tr>
    </c:forEach>
    </table>
    
  </body>
</html>