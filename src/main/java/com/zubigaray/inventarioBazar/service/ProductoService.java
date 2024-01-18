package com.zubigaray.inventarioBazar.service;

import com.zubigaray.inventarioBazar.model.Producto;
import com.zubigaray.inventarioBazar.repository.IProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository prodRepo;
    
    private void productoNull(Producto prod){
        // Compruebo si el producto es nulo, si se cumple lanzo una excepcion
        if (prod == null) {
            throw new IllegalArgumentException("Producto no puede ser nulo");
        }
    }
    
    @Override
    public void saveProducto(Producto prod) {
        productoNull(prod);
        prodRepo.save(prod);
    }

    @Override
    public void deleteProducto(Long id_producto) {
        prodRepo.deleteById(id_producto);
    }

    @Override
    public void editProducto(Long id_producto, String nombre, String marca, double costo, double cantidad_disponible) {
        Producto prod = this.findProducto(id_producto);
        
        prod.setNombre(nombre);
        prod.setMarca(marca);
        prod.setCosto(costo);
        prod.setCantidad_disponible(cantidad_disponible);
        
        productoNull(prod);
        this.saveProducto(prod);
    }

    @Override
    public Producto findProducto(Long id_producto) {
        return prodRepo.findById(id_producto).orElse(null);
    }

    @Override
    public List<Producto> findProductos() {
        return prodRepo.findAll();
    }
}
