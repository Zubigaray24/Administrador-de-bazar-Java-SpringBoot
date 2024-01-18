package com.zubigaray.inventarioBazar.dto;

import com.zubigaray.inventarioBazar.model.Cliente;
import com.zubigaray.inventarioBazar.model.Producto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteListaDTO {
    private Cliente cliente;
    private List<Producto> lista_productos;
}
