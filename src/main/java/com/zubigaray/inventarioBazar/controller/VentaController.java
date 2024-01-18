package com.zubigaray.inventarioBazar.controller;

import com.zubigaray.inventarioBazar.dto.ClienteListaDTO;
import com.zubigaray.inventarioBazar.dto.ClienteVentaDTO;
import com.zubigaray.inventarioBazar.model.Cliente;
import com.zubigaray.inventarioBazar.model.Producto;
import com.zubigaray.inventarioBazar.model.Venta;
import com.zubigaray.inventarioBazar.service.IVentaService;
import java.time.LocalDate;
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
public class VentaController {
    
    @Autowired
    private IVentaService ventServ;
    
    @PostMapping("/ventas/crear")
    public ResponseEntity<String> saveVenta(@RequestBody Venta vent){
            try {
                if(vent != null){
                    ventServ.saveVenta(vent);
                    return new ResponseEntity<>("La venta ha sido generada con exito!", HttpStatus.CREATED);
                }
                return new ResponseEntity<>("La venta NO ha sido generada", HttpStatus.BAD_REQUEST);
            } 
            catch (Exception e) {
                return new ResponseEntity<>("Error al generar la venta: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    
    //Lista completa de ventas
    @GetMapping("/ventas/traer-todas")
    @ResponseBody
    public List<Venta> findVentas(){
        try{
            return ventServ.findVentas();
        }
        catch(Exception e){
            System.out.println("Se produjo un error: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    //Traer una venta en particular
    @GetMapping("/ventas/encontrar/{id_venta}")
    @ResponseBody
    public Venta findVenta(@PathVariable Long id_venta){
        try{
            if(id_venta != null){
                return ventServ.findVenta(id_venta);
            }
            return new Venta();
        }
        catch(Exception e){
            System.out.println("Se produjo un error: " + e.getMessage());
            return new Venta();
        }
    }  
    
    //Eliminación
    @DeleteMapping("/ventas/eliminar/{id_venta}")
    public ResponseEntity<String> deleteVenta(@PathVariable Long id_venta){
        try{
            if(id_venta != null){
                ventServ.deleteVenta(id_venta);
                return new ResponseEntity<>("La venta ha sido eliminada con exito!", HttpStatus.OK);
            }
            return new ResponseEntity<>("La venta NO ha sido eliminado", HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){
            return new ResponseEntity<>("Error al eliminar la venta: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/ventas/editar/{id_venta}")
    public ResponseEntity<String> editVenta(@PathVariable Long id_venta, 
                            @RequestParam(required=false, name="fecha_venta") LocalDate fecha_venta, 
                            @RequestParam(required=false, name="total") double total,
                            @RequestBody ClienteListaDTO ventaRequest){

        try{
            Venta vent = this.findVenta(id_venta);
            ventServ.editVenta(id_venta, fecha_venta, total, ventaRequest.getLista_productos(), ventaRequest.getCliente());
            this.saveVenta(vent);
            return new ResponseEntity("La venta ha sido editada con exito!", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("Error al eliminar la venta: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Obtener la lista de productos de una determinada venta
    @GetMapping("/ventas/productos/{id_venta}")
    @ResponseBody
    public List<Producto> findProductosVenta(@PathVariable Long id_venta){
        try{
            Venta vent = this.findVenta(id_venta);
            return vent.getLista_productos();   
        }
        catch(Exception e){
            System.out.println("Se produjo un error: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    //Obtener sumatoria del monto de una venta
    private double findMontoDeVenta(Long id_venta){
        double contador = 0;
        
        for(Producto prod : ventServ.findVenta(id_venta).getLista_productos()){
            contador += prod.getCosto();
        }
        
        return contador;
    }
    //Obtener las ventas del dia
    private List<Venta> findVentasDelDia(LocalDate fecha_venta){
        List<Venta> todasLasVentas = this.findVentas();
        List<Venta> resultado = new ArrayList<>();
        
        for(Venta venta : todasLasVentas){
            if(venta.getFecha_venta().equals(fecha_venta)){
                resultado.add(venta);
            }
        }
        return resultado;
    }
    
    //Obtener la sumatoria del monto y también cantidad total de ventas de un determinado día
    @GetMapping("/ventas/{fecha_venta}")
    public ResponseEntity<String> findMontoYCantidadTotalDeVentasEnElDia(@PathVariable LocalDate fecha_venta){
        try{
            if(fecha_venta != null){
                List<Venta> ventasHoy = this.findVentasDelDia(fecha_venta);

                double montoTotal = 0;

                for(Venta venta : ventasHoy){
                    montoTotal += this.findMontoDeVenta(venta.getId_venta());
                }

                return new ResponseEntity("El monto total es " + montoTotal + " y el total de ventas es de " + ventasHoy.size(), HttpStatus.OK);
            }
            return new ResponseEntity<>("La venta NO ha sido eliminado", HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){
            return new ResponseEntity<>("Error al eliminar la venta: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Obtener el cliente con el monto mas alto de venta
    private Long findIdMontoMayor(){
        Long resultado = 0L;
        double montoMayor = 0;
        
        List<Venta> listaVentas = this.findVentas();
        
        for (Venta vent : listaVentas){
            if(vent.getTotal() > montoMayor){
                montoMayor = vent.getTotal();
                resultado = vent.getUn_cliente().getId_cliente();
            }
        }
        
        return resultado;
    }
    
    //Obtener el codigo_venta, el total, la cantidad de productos, el nombre del cliente y el
    //apellido del cliente de la venta con el monto más alto de todas.
    @GetMapping("/ventas/mayor_venta")
    public ClienteVentaDTO findClienteVentaDTO(){
        try{
            ClienteVentaDTO cliVenDTO = new ClienteVentaDTO();
            Long idMontoMayor = this.findIdMontoMayor();
            Venta venta = ventServ.findVenta(idMontoMayor);
            
            if (venta != null) {
                Cliente cli = venta.getUn_cliente();

                cliVenDTO.setId_venta(idMontoMayor);
                cliVenDTO.setTotal_venta(venta.getTotal());
                cliVenDTO.setCantidad_de_productos_venta(venta.getLista_productos().size());
                cliVenDTO.setNombre_cliente(cli.getNombre());
                cliVenDTO.setApellido_cliente(cli.getApellido());

                return cliVenDTO;
            }
            return new ClienteVentaDTO();
        }
        catch(Exception e){
            System.out.println("Se produjo un error: " + e.getMessage());
            return new ClienteVentaDTO();
        }

    }
}
