package com.zubigaray.inventarioBazar.service;

import com.zubigaray.inventarioBazar.model.Cliente;
import com.zubigaray.inventarioBazar.model.Producto;
import com.zubigaray.inventarioBazar.model.Venta;
import com.zubigaray.inventarioBazar.repository.IProductoRepository;
import com.zubigaray.inventarioBazar.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{
    
    @Autowired
    private IVentaRepository ventRepo;
    
    @Autowired
    private IProductoRepository prodRepo;

    private void ventaProductoNull(Venta vent){
        
        //Compruebo si la venta es nula o la lista de productos es nula o esta vacia, si alguna se cumple lanzo una excepcion
        if (vent == null || vent.getLista_productos() == null || vent.getLista_productos().isEmpty()) {
            throw new IllegalArgumentException("Venta no puede ser nulo y lista de productos no puede ser nulo o estar vacia");
        }
        
        // Reduzco la cantidad disponible de cada producto en la venta
        for (Producto producto : vent.getLista_productos()) {
            if(producto.getCantidad_disponible() > 0){
                reducirCantidadDisponible(producto.getId_producto(), 1);
            }
            else{
                //Si no hay stock de un producto, mando un mensaje para que el usuario revise su lista
                throw new IllegalArgumentException("Revise su lista, hay productos sin stock");
            }
        }
    }
    
    @Override
    public void saveVenta(Venta vent) {
        ventaProductoNull(vent);

        // Guardo la venta en la base de datos
        ventRepo.save(vent);
    }
    
    // Método para reducir la cantidad disponible de un producto en la base de datos
    private void reducirCantidadDisponible(Long idProducto, double cantidad) {
        Producto producto = prodRepo.findById(idProducto).orElse(null);

        if (producto != null) {
            double cantidadDisponibleActualizada = producto.getCantidad_disponible() - cantidad;          
            producto.setCantidad_disponible(cantidadDisponibleActualizada);
            prodRepo.save(producto);
        } else {
            throw new IllegalArgumentException("Ese producto no esta en existencia");
        }
    }

    @Override
    public void deleteVenta(Long id_venta) {
        ventRepo.deleteById(id_venta);
    }

    @Override
    public void editVenta(Long id_venta, LocalDate fecha_venta, double total, List<Producto> lista_productos, Cliente cliente) {
        Venta vent = this.findVenta(id_venta);
        
        vent.setId_venta(id_venta);
        vent.setFecha_venta(fecha_venta);
        vent.setTotal(total);
        vent.setLista_productos(lista_productos);
        vent.setUn_cliente(cliente);
        
        ventaProductoNull(vent);
        
        this.saveVenta(vent);
    }

    @Override
    public Venta findVenta(Long id_venta) {
        return ventRepo.findById(id_venta).orElse(null);
    }

    @Override
    public List<Venta> findVentas() {
        return ventRepo.findAll();
    } 
}
