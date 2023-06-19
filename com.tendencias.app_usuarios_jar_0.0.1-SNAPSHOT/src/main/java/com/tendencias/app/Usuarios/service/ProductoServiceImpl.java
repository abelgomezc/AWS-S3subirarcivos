/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tendencias.app.Usuarios.service;


import com.tendencias.app.Usuarios.model.Producto;
import com.tendencias.app.Usuarios.repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Abel Gomez
 */
@Service
public class ProductoServiceImpl extends GenericServiceImpl<Producto, Integer> implements GenericService<Producto, Integer>{
        @Autowired
        ProductoRepository productoRepository;

    @Override
    public CrudRepository<Producto, Integer> getDao() {
        return productoRepository;
    }
     
    public List<Producto> obtenerProductosPorIds(List<Integer> ids) {
        return productoRepository.findAllById(ids);
    }
}
