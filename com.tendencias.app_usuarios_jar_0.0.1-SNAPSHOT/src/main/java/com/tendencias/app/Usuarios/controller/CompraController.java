/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tendencias.app.Usuarios.controller;

import com.tendencias.app.Usuarios.model.Compra;
import com.tendencias.app.Usuarios.model.Producto;
import com.tendencias.app.Usuarios.service.CompraServiceImpl;
import com.tendencias.app.Usuarios.service.ProductoServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Abel Gomez
 */
@RestController
@RequestMapping("/api/compras")
public class CompraController {
    
        @Autowired
    private CompraServiceImpl compraService;
    
            @Autowired
    private ProductoServiceImpl productoServiceImpl;
      
    @PostMapping("/realizarCompra")
    public ResponseEntity<Compra> realizarCompra(@RequestBody Compra compra) {
      List<Integer> productoIds = compra.getProductos().stream()
                .map(Producto::getId_prod)
                .collect(Collectors.toList());
             
      
        List<Producto> productos = new ArrayList<>();
        for (Integer id : productoIds) {
            Producto producto = productoServiceImpl.findById(id);
            if (producto != null) {
                productos.add(producto);
            }
        }
        
                double precioTotal = 0;
        for (Producto producto : productos) {
            int cantidad = compra.getProductos().stream()
                    .filter(p -> p.getId_prod() == producto.getId_prod())
                    .findFirst()
                    .map(Producto::getCantidad)
                    .orElse(0);
            double subtotal = producto.getPrecioEmprendedor() * cantidad;
            precioTotal += subtotal;
        }
        compra.setTotalCompra(precioTotal);
  //    Compra nuevaCompra = compraService.save(compra);
       return ResponseEntity.ok( compraService.save(compra));
    }

  
    @GetMapping("/listarCompras")
    public ResponseEntity<List<Compra>> listarCompras() {
        List<Compra> compras = compraService.findByAll();
        return ResponseEntity.ok(compras);
    }


}
