/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tendencias.app.Usuarios.controller;


import com.tendencias.app.Usuarios.model.Egreso;
import com.tendencias.app.Usuarios.model.Ingreso;
import com.tendencias.app.Usuarios.model.Venta;
import com.tendencias.app.Usuarios.service.EgresoServiceImpl;
import com.tendencias.app.Usuarios.service.IngresoServiceImpl;
import com.tendencias.app.Usuarios.service.VentaServiceImpl;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Abel Gomez
 */
@RestController
@RequestMapping("/api/finanzas")
public class FinanzasController {

    @Autowired
    private VentaServiceImpl ventaService;

    @Autowired
    private IngresoServiceImpl ingresoService;

    @Autowired
    private EgresoServiceImpl egresoService;

    @PostMapping("/calcularIngresosAllVentas")
    public ResponseEntity<Ingreso> calcularIngresos() {
        Ingreso ingreso = new Ingreso();
    //    List<Venta> ventas = ventaService.findByAll();

        double totalIngresos = ventaService.sumarTotalVentas();
//        for (Venta venta : ventas) {
//            totalIngresos += venta.getPreciototal();
//        }

        ingreso.setCantidad(totalIngresos);
        ingreso.setFecha(new Date());

        Ingreso nuevoIngreso = ingresoService.save(ingreso);

        return ResponseEntity.ok(nuevoIngreso);
    }
    
    
//(@RequestBody Ingreso ingreso) utilizar para guardar ingreso de emprendedor
//    @PostMapping("/calcularIngresos")
//    public ResponseEntity<Ingreso> calcularIngresos() {
//        // Obtener todas las ventas de productos
//        List<Venta> ventas = ventaService.findByAll();
//
//        // Calcular el total de ingresos
//        double totalIngresos = 0;
//        for (Venta venta : ventas) {
//            totalIngresos += venta.getPreciototal();
//        }
//
//        // Crear un objeto Ingreso con la cantidad total y fecha actual
//        Ingreso ingreso = new Ingreso();
//        ingreso.setCantidad(totalIngresos);
//        ingreso.setFecha(new Date());
//
//        // Guardar el ingreso en la base de datos
//        Ingreso nuevoIngreso = ingresoService.save(ingreso);
//
//        return ResponseEntity.ok(nuevoIngreso);
//    }
    @PostMapping("/registrarEgreso")
    public ResponseEntity<Egreso> registrarEgreso(@RequestBody Egreso egreso) {
        Egreso registrado = egresoService.save(egreso);
        return ResponseEntity.ok(registrado);
    }

}
