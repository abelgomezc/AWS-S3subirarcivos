/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tendencias.app.Usuarios.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Abel Gomez
 */
@Entity
@Data
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_venta;
    @ManyToOne
    private Usuario usuario;
    @Column
      private double preciototal;
    
      @ManyToMany
    @JoinTable(
        name = "ventaproducto",
        joinColumns = @JoinColumn(name = "id_venta"),
        inverseJoinColumns = @JoinColumn(name = "id_prod")
    )
    private List<Producto> productos;
    private Date fecha;
   
}

