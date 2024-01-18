API (Application Programming Interface) para registrar las ventas y el stock de los productos de forma manual para un bazar.

**Los EndPoints para CLIENTE son los siguientes:**

*//Crear Cliente*
localhost:8080/clientes/crear

*//Traer lista de todos los clientes*
localhost:8080/clientes

*//Traer cliente en especifico, en este caso el cliente con id 1*
localhost:8080/clientes/encontrar/1

*//Eliminar cliente en especifico, en este caso el cliente con id 1*
localhost:8080/clientes/eliminar/1

*//Editar cliente, cambiar los parametros a conveniencia*
localhost:8080/clientes/editar/2?dni=32632057&nombre=Ernesto&apellido=Gomez

**Los EndPoints para PRODUCTO son los siguientes:**

*//Crear Producto*
localhost:8080/productos/crear

*//Traer lista de todos los productos*
localhost:8080/productos

*//Traer producto en especifico, en este caso el producto con id 1*
localhost:8080/productos/encontrar/1

*//Eliminar producto en especifico, en este caso el producto con id 1*
localhost:8080/productos/eliminar/1

*//Editar producto, cambiar los parametros a conveniencia*
localhost:8080/productos/editar/1?nombre=Chocolate&marca=Golopolis&costo=1.99&cantidad_disponible=18

*//Traer productos de los que haya menos de X cantidad en stock, en este caso 6*
localhost:8080/productos/menor_a/6

**Los EndPoints para VENTA son los siguientes:**

*//Crear Venta*
localhost:8080/ventas/crear

*//Traer lista de todos las ventas*
localhost:8080/ventas/traer-todas

*//Traer venta en especifico, en este caso la venta con id 1*
localhost:8080/ventas/encontrar/1
