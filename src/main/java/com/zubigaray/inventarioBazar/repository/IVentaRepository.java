package com.zubigaray.inventarioBazar.repository;

import com.zubigaray.inventarioBazar.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository <Venta, Long>{
    
}
