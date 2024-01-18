package com.zubigaray.inventarioBazar.service;

import com.zubigaray.inventarioBazar.model.Producto;
import java.util.List;

public interface IProductoService {
    
    //alta
    public void saveProducto(Producto prod);
    
    //baja
    public void deleteProducto(Long id_producto);
    
    //modificacion
    public void editProducto(Long id_producto, String nombre, String marca, double costo, double cantidad_disponible);
    
    //leer un elemento
    public Producto findProducto(Long id_producto);
    
    //leer muchos elementos
    public List<Producto> findProductos();
    
    //Actualizar cantidad del producto
    //public void updateCantidadProducto(Producto prod);

}
