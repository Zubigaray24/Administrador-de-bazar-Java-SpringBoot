package com.zubigaray.inventarioBazar.controller;

import com.zubigaray.inventarioBazar.model.Cliente;
import com.zubigaray.inventarioBazar.service.IClienteService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    
    @Autowired
    private IClienteService cliServ;
    
    // Creación
    @PostMapping("/clientes/crear")
    public ResponseEntity<String> saveCliente(@RequestBody Cliente cli){
        
            try {
                if(cli != null){
                    cliServ.saveCliente(cli);
                    return new ResponseEntity<>("El cliente ha sido generado con exito!", HttpStatus.CREATED);
                }
                return new ResponseEntity<>("El cliente NO ha sido generado", HttpStatus.BAD_REQUEST);
            } 
            catch (Exception e) {
                return new ResponseEntity<>("Error al generar el cliente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }   
    }
    
    //Lista completa de clientes
    @GetMapping("/clientes")
    @ResponseBody
    public List<Cliente> findClientes(){
        try{
            return cliServ.findClientes();
        }
        catch(Exception e){
            System.out.println("Se produjo un error: " + e.getMessage());
            return new ArrayList<>();
        }
    }
        
    //Traer un cliente en particular
    @GetMapping("/clientes/encontrar/{id_cliente}")
    @ResponseBody
    public Cliente findCliente(@PathVariable Long id_cliente){
        try{
            if(id_cliente != null){
                return cliServ.findCliente(id_cliente);
            }
            return new Cliente();
        }
        catch(Exception e){
            System.out.println("Se produjo un error: " + e.getMessage());
            return new Cliente();
        }
    }
    
    //Eliminación
    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id_cliente){
        try{
            if(id_cliente != null){
                    cliServ.deleteCliente(id_cliente);
                    return new ResponseEntity<>("El cliente ha sido eliminado con exito!", HttpStatus.OK);
            }
            return new ResponseEntity<>("El cliente NO ha sido eliminado", HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){
            return new ResponseEntity<>("Error al eliminar el cliente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Edición
    @PutMapping("/clientes/editar/{id_cliente}")
    public Cliente editCliente( @PathVariable Long id_cliente, 
                                @RequestParam(required=false, name="dni") String dni, 
                                @RequestParam(required=false, name="nombre") String nombre, 
                                @RequestParam(required=false, name="apellido") String apellido){
        try{
            if(id_cliente != null){
                cliServ.editCliente(id_cliente, nombre, apellido, dni);
                Cliente cli = this.findCliente(id_cliente);
                return cli;
            }
        }
        catch(Exception e){
            System.out.println("Se produjo un error: " + e.getMessage());
            return new Cliente();
        }
        return new Cliente();
    }

}
