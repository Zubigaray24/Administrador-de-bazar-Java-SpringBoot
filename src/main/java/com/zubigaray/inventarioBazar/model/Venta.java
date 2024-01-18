package com.zubigaray.inventarioBazar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Venta {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id_venta;
    
    @Temporal(TemporalType.DATE)
    private LocalDate fecha_venta;
    
    private double total;
    
    @ManyToMany
    @JoinTable(
            name = "relacion_venta_producto",
            joinColumns = @JoinColumn (name = "FK_VENTA", nullable = false),
            inverseJoinColumns = @JoinColumn (name = "FK_PRODUCTO", nullable = false)
    )
    private List<Producto> lista_productos;
    
    @OneToOne   //Columna de la tabla de ventas                        Columna que es PK en tabla cliente
    @JoinColumn (name = "un_cliente_id_cliente", referencedColumnName="id_cliente")
    private Cliente un_cliente;
    
}
