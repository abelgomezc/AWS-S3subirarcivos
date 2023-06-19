/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tendencias.app.Usuarios.service;

import com.tendencias.app.Usuarios.model.Ingreso;
import com.tendencias.app.Usuarios.repository.IngresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Abel Gomez
 */
@Service
public class IngresoServiceImpl extends GenericServiceImpl<Ingreso, Integer> implements GenericService<Ingreso, Integer>{
         @Autowired
    IngresoRepository ingresoRepository;

    @Override
    public CrudRepository<Ingreso, Integer> getDao() {
        return ingresoRepository;
    }
}
