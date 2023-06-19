/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tendencias.app.Usuarios.controller;

import com.tendencias.app.Usuarios.model.Producto;
import com.tendencias.app.Usuarios.model.Venta;
import com.tendencias.app.Usuarios.service.ProductoServiceImpl;
import com.tendencias.app.Usuarios.service.VentaServiceImpl;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaServiceImpl ventaService;

    @Autowired
    private ProductoServiceImpl productoServiceImpl;

    @PostMapping("/realizarVenta")
    @Transactional

    public ResponseEntity<Venta> realizarVenta(@RequestBody Venta venta) {
        List<Integer> productoIds = venta.getProductos().stream()
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
            int cantidad = venta.getProductos().stream()
                    .filter(p -> p.getId_prod() == producto.getId_prod())
                    .findFirst()
                    .map(Producto::getCantidad)
                    .orElse(0);
            double subtotal = producto.getPvp() * cantidad;
            precioTotal += subtotal;
        }

        venta.setPreciototal(precioTotal);
     //   Venta nuevaVenta = ventaService.save(venta);

        return ResponseEntity.ok(ventaService.save(venta));
    }

    @GetMapping("/obtenerVentas")
    public ResponseEntity<List<Venta>> obtenerVentas() {

     //   List<Venta> ventas = ventaService.findByAll();
      //  return ResponseEntity.ok(ventas);
        
           return new ResponseEntity<>(ventaService.findByAll(), HttpStatus.OK);
    }

}





//{
//  "usuario": {
//    "id_usuario": 1
//  },
//  "productos": [
//    {
//      "id_prod": 1,
//      "cantidad": 2
//    },
//    {
//      "id_prod": 2,
//      "cantidad": 1
//    },
//    {
//      "id_prod": 3,
//      "cantidad": 3
//    }
//  ],
//  "fecha": "2023-06-11T05:32:03.054+00:00"
//}