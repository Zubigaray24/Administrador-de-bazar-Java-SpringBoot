package com.zubigaray.inventarioBazar.repository;

import com.zubigaray.inventarioBazar.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long>{
    
}
