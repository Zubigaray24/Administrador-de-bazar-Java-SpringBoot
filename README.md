API (Application Programming Interface) para registrar las ventas y el stock de los productos de forma manual para un bazar.

**Los EndPoints para CLIENTE son los siguientes:**

*Crear Cliente* ---> 
localhost:8080/clientes/crear

*Traer lista de todos los clientes* ---> 
localhost:8080/clientes

*Traer cliente en especifico, en este caso el cliente con id 1* ---> 
localhost:8080/clientes/encontrar/1

*Eliminar cliente en especifico, en este caso el cliente con id 1* ---> 
localhost:8080/clientes/eliminar/1

*Editar cliente, cambiar los parametros a conveniencia* ---> 
localhost:8080/clientes/editar/2?dni=32632057&nombre=Ernesto&apellido=Gomez

**Los EndPoints para PRODUCTO son los siguientes:**

*Crear Producto* ---> 
localhost:8080/productos/crear

*Traer lista de todos los productos* ---> 
localhost:8080/productos

*Traer producto en especifico, en este caso el producto con id 1* ---> 
localhost:8080/productos/encontrar/1

*Eliminar producto en especifico, en este caso el producto con id 1* ---> 
localhost:8080/productos/eliminar/1

*Editar producto, cambiar los parametros a conveniencia* ---> 
localhost:8080/productos/editar/1?nombre=Chocolate&marca=Golopolis&costo=1.99&cantidad_disponible=18

*Traer productos de los que haya menos de X cantidad en stock, en este caso 6* ---> 
localhost:8080/productos/menor_a/6

**Los EndPoints para VENTA son los siguientes:**

*Crear Venta* ---> 
localhost:8080/ventas/crear

*Traer lista de todos las ventas* ---> 
localhost:8080/ventas/traer-todas

*Traer venta en especifico, en este caso la venta con id 1* ---> 
localhost:8080/ventas/encontrar/1

*Eliminar venta en especifico, en este caso la venta con id 1* ---> 
localhost:8080/ventas/eliminar/1

*Editar venta, cambiar los parametros a conveniencia y poner parametros de productos y cliente en el body de la peticion* ---> 
localhost:8080/ventas/editar/1?fecha_venta=2017-07-22&total=25.35

{
    "lista_productos" : [
                            {"id_producto" : 1}, 
                            {"id_producto" : 2}, 
                            {"id_producto" : 3}
],
    "un_cliente" :{"id_cliente" : 1}
}

*Obtener la lista de productos de una determinada venta* ---> 
localhost:8080/ventas/productos/1

*Obtener la sumatoria del monto y también cantidad total de ventas de un determinado día* ---> 
localhost:8080/ventas/2018-08-21

*Obtener el codigo_venta, el total, la cantidad de productos, el nombre del cliente y el apellido del cliente de la venta con el monto más alto de todas* ---> 
localhost:8080/ventas/mayor_venta
