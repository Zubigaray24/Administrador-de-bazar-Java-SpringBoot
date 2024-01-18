
package com.zubigaray.inventarioBazar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Producto {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id_producto;
    
    private String nombre;
    private String marca;
    private double costo;
    private double cantidad_disponible;
    
    @ManyToMany(mappedBy="lista_productos")
    private List<Venta> ventas;
}
