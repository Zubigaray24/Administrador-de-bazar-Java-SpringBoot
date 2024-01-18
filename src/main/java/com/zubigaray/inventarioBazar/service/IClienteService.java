package com.zubigaray.inventarioBazar.service;

import com.zubigaray.inventarioBazar.model.Cliente;
import java.util.List;

public interface IClienteService {
    
    //alta
    public void saveCliente(Cliente cli);
    
    //baja
    public void deleteCliente(Long id_cliente);
    
    //modificacion
    public void editCliente(Long id_service, String nombre, String apellido, String dni);
    
    //leer un elemento
    public Cliente findCliente(Long id_cliente);
    
    //leer muchos elementos
    public List<Cliente> findClientes();
}
