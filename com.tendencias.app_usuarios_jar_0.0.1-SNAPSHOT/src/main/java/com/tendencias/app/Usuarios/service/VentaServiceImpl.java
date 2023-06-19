/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tendencias.app.Usuarios.service;


import com.tendencias.app.Usuarios.model.Venta;
import com.tendencias.app.Usuarios.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Abel Gomez
 */
@Service
public class VentaServiceImpl  extends GenericServiceImpl<Venta, Integer> implements GenericService<Venta, Integer>{
           @Autowired
    VentaRepository ventaRepository;

    @Override
    public CrudRepository<Venta, Integer> getDao() {
        return ventaRepository;
    }
    
     public double sumarTotalVentas() {
        return ventaRepository.sumarTotalVentas();
    }
}
