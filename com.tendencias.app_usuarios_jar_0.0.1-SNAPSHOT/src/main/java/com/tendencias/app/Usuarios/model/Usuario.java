/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tendencias.app.Usuarios.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Abel Gomez
 */
@Data
@Entity
public class Usuario {

    //many unuasrios
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int id_usuario;
    private String nombre;
    private String apellido;
    private String cedula;
    private String direccion;
    private String telefono;

    private String correoElectronico;
    private String contrasena;

    @Column(name = "estado")
    private int estado;

    @Transient
    private String fotoURL;

    private String imagenPath;

// @ManyToOne
// @JoinColumn(name ="id_persona",referencedColumnName = "id_persona")
// private Persona persona;
    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    private Rol rol;
}
