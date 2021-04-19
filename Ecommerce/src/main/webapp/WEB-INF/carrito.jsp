<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
  <head>
  	<title>Ecommerce :: Carrito de compras</title>
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
    	body{
			background-image: url(images/bg_general.jpg);
    		background-repeat: repeat;
    		padding:10px;
    	}
    </style>
  </head>
  <body>
    <h1>ECOMMERCE - TUL</h1>
    <br/><br/>
    <table width="100%">
    	<tr>
    	<td><h4>Lista  de Carritos Compras</h4></td>
    	<td align="right"><h4><a href="/springapp/Productos.htm">Mantenimiento de Productos</a></h4></td>
    	</tr>
    </table>
    <br/>
    
    <table class="table table-responsive table-striped" border="1">
    <tr>
    	<th>Id</th>
    	<th>Fecha Creaci&oacute;n</th>
    	<th>Fecha Procesamiento</th>
    	<th>Estado</th>
    	<th>.</th>
    	<th>.</th>
    </tr>
    <c:forEach items="${model.carritos}" var="carrito">
    	<tr>
  			<td><c:out value="${carrito.idCarrito}"/></td>
  			<td><c:out value="${carrito.fechaCreacion}"/></td>
  			<td><c:out value="${carrito.fechaProcesamiento}"/></td>
  			<td><c:out value="${carrito.estado}"/></td>
  			<td><a href="#">Ver Productos</a></td>
  			<td><a href="#">Procesar</a></td>
  		</tr>
    </c:forEach>
    </table>
    
  </body>
</html>