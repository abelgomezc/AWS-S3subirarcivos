/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tendencias.app.Usuarios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Abel Gomez
 */
@Entity
@Data
public class Producto {
    
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_prod;
    private String nombre;
    private String descripcion;
    private String categoria;
    private double precioEmprendedor;
     private double pvp;




    @ManyToOne
    private Usuario usuarioP;
    
    
    
    @ManyToOne
@JoinColumn(name = "id_compra")
private Compra compra;

    private int cantidad;
   @ManyToMany(mappedBy = "productos")
    private List<Venta> ventas;
    
}
