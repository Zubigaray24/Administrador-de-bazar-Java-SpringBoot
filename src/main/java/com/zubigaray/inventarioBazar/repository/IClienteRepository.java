package com.zubigaray.inventarioBazar.repository;

import com.zubigaray.inventarioBazar.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long>{
    
}
