package com.zubigaray.inventarioBazar.service;

import com.zubigaray.inventarioBazar.model.Cliente;
import com.zubigaray.inventarioBazar.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    private IClienteRepository cliRepo;
    
    private void clienteNull(Cliente cli){
        // Compruebo si el producto es nulo, si se cumple lanzo una excepcion
        if (cli == null) {
            throw new IllegalArgumentException("Cliente no puede ser nulo");
        }
    }
    
    @Override
    public void saveCliente(Cliente cli) {
        clienteNull(cli);
        cliRepo.save(cli);
    }

    @Override
    public void deleteCliente(Long id_cliente) {
        cliRepo.deleteById(id_cliente);
    }

    @Override
    public void editCliente(Long id_cliente, String nombre, String apellido, String dni) {
        
        Cliente cli = this.findCliente(id_cliente);
        
        cli.setNombre(nombre);
        cli.setApellido(apellido);
        cli.setDni(dni);
        
        clienteNull(cli);
        this.saveCliente(cli);
    }

    @Override
    public Cliente findCliente(Long id_cliente) {
        return cliRepo.findById(id_cliente).orElse(null);
    }

    @Override
    public List<Cliente> findClientes() {
        return cliRepo.findAll();
    }
    
}
