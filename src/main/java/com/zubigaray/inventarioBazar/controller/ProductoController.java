package com.zubigaray.inventarioBazar.controller;

import com.zubigaray.inventarioBazar.model.Producto;
import com.zubigaray.inventarioBazar.service.IProductoService;
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
public class ProductoController {
    
    @Autowired
    private IProductoService prodServ;
    
    // Creación
    @PostMapping("/productos/crear")
    public ResponseEntity<String> saveProducto(@RequestBody Producto prod){
            try {
                if(prod != null){
                    prodServ.saveProducto(prod);
                    return new ResponseEntity<>("El producto ha sido generado con exito!", HttpStatus.CREATED);
                }
                return new ResponseEntity<>("El producto NO ha sido generado", HttpStatus.BAD_REQUEST);
            }
            catch (Exception e) {
                return new ResponseEntity<>("Error al generar el producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    
    //Lista completa de productos
    @GetMapping("/productos")
    @ResponseBody
    public List<Producto> findProductos(){
        try {
            List<Producto> productos = prodServ.findProductos();
            return productos;
        } 
        catch (Exception e) {
            System.out.println("Se produjo un error: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    //Traer un producto en particular
    @GetMapping("/productos/encontrar/{id_producto}")
    @ResponseBody
    public Producto findProducto(@PathVariable Long id_producto){
        try{
            if(id_producto != null){
                return prodServ.findProducto(id_producto);
            }
            return new Producto();
        } 
        catch (Exception e){
            System.out.println("Se produjo un error: " + e.getMessage());
            return new Producto();
        }
    }
    
    //Eliminación
    @DeleteMapping("/productos/eliminar/{id_producto}")
    public ResponseEntity<String> deleteProducto(@PathVariable Long id_producto){
            try{
                if(id_producto != null){
                    prodServ.deleteProducto(id_producto);
                    return new ResponseEntity<>("El producto ha sido eliminado con exito!", HttpStatus.OK);
                }
                return new ResponseEntity<>("El producto NO ha sido eliminado", HttpStatus.BAD_REQUEST);
            } 
            catch (Exception e) {
                return new ResponseEntity<>("Error al eliminar el producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    
    //Edición
    @PutMapping("/productos/editar/{id_producto}")
    public ResponseEntity<String> editProducto( @PathVariable Long id_producto, 
                                @RequestParam(required=false, name="nombre") String nombre, 
                                @RequestParam(required=false, name="marca") String marca, 
                                @RequestParam(required=false, name="costo") double costo, 
                                @RequestParam(required=false, name="cantidad_disponible") double cantidad_disponible){
        try {
            Producto prod = this.findProducto(id_producto);
            prodServ.editProducto(id_producto, nombre, marca, costo, cantidad_disponible);
            this.saveProducto(prod);
            return new ResponseEntity<>("El producto ha sido editado con exito!", HttpStatus.OK);
        } 
        catch (Exception e) {
            System.out.println("Se produjo un error: " + e.getMessage());
            return new ResponseEntity<>("Hubo un error al editar el producto.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Cantidad disponible menor a una cierta cantidad
    @GetMapping("/productos/menor_a/{cantidad}")
    @ResponseBody
    public List<Producto> findProductosMenorA(@PathVariable int cantidad){
        try{
            List<Producto> listaProductos = this.findProductos();

            List<Producto> resultado = new ArrayList<>();

            for(Producto prod : listaProductos){
                if(prod.getCantidad_disponible()<cantidad){
                    resultado.add(prod);
                }
            }

            return resultado;
        } catch (Exception e) {
            System.out.println("Se produjo un error: " + e.getMessage());
            return new ArrayList<>(); // Devuelve una lista vacía en caso de error
        }
    }
    
}   

