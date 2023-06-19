/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tendencias.app.Usuarios.repository;


import com.tendencias.app.Usuarios.model.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Abel Gomez
 */
public interface IngresoRepository  extends JpaRepository<Ingreso, Integer> {
    
}
