package com.zubigaray.inventarioBazar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteVentaDTO {
    
    private Long id_venta;
    private double total_venta;
    private int cantidad_de_productos_venta;
    private String nombre_cliente;
    private String apellido_cliente;
}
