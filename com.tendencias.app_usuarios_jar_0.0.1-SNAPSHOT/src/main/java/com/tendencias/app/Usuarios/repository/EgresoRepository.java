/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tendencias.app.Usuarios.repository;


import com.tendencias.app.Usuarios.model.Egreso;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Abel Gomez
 */
public interface EgresoRepository  extends JpaRepository<Egreso, Integer>{
    
}
