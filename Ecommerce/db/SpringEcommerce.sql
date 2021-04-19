CREATE DATABASE Ecommerce;

USE Ecommerce;
	
CREATE TABLE Producto (
  idproducto int PRIMARY KEY,
  sku varchar(50),
  nombre varchar(200),
  descripcion varchar(255),
  precio double(15,2),
  tipo int,
  stock int
);
CREATE INDEX producto_sku ON Producto(sku);
	
	
CREATE TABLE Carrito (
  idCarrito int PRIMARY KEY,
  fechaCreacion DateTime,
  fechaProcesamiento DateTime,
  estado int
);
CREATE INDEX Carrito_idCarrito ON Carrito(idCarrito);


CREATE TABLE CarritoProducto (
  idCarritoProducto int PRIMARY KEY,
  idCarrito int,
  idProducto int,
  precioProducto double(15,2)
);
CREATE INDEX Carrito_idCarrito ON CarritoProducto(idCarrito);

