package com.zubigaray.inventarioBazar.service;

import com.zubigaray.inventarioBazar.model.Cliente;
import com.zubigaray.inventarioBazar.model.Producto;
import com.zubigaray.inventarioBazar.model.Venta;
import java.time.LocalDate;
import java.util.List;

public interface IVentaService {
        
    //alta
    public void saveVenta(Venta vent);
    
    //baja
    public void deleteVenta(Long id_venta);
    
    //modificacion
    public void editVenta(Long id_venta, LocalDate fecha_venta, double total, List<Producto> lista_productos, Cliente cliente);
    
    //leer un elemento
    public Venta findVenta(Long id_venta);
    
    //leer muchos elementos
    public List<Venta> findVentas();

}
