/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tendencias.app.Usuarios.controller;

import com.tendencias.app.Usuarios.model.Rol;

import com.tendencias.app.Usuarios.service.RolServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Abel Gomez
 */
@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    RolServiceImpl rolService;

    @Operation(summary = "Se obtiene la lista de Roles")
    @GetMapping("/listarRoles")
    public ResponseEntity<List<Rol>> listaRoles() {
        return new ResponseEntity<>(rolService.findByAll(), HttpStatus.OK);
    }

    @Operation(summary = "Debe enviar los campos del Roles")
    @PostMapping("/crearRol")
    public ResponseEntity<Rol> crearRol(@RequestBody Rol u) {
        return new ResponseEntity<>(rolService.save(u), HttpStatus.CREATED);
    }

    @PutMapping("/actualizarRol/{id}")
    public ResponseEntity<Rol> actualizarRol(@PathVariable Integer id, @RequestBody Rol u) {
        Rol rol = rolService.findById(id);
        if (rol != null) {
            try {

                rol.setNombre(u.getNombre());
                rol.setDescripcion(u.getDescripcion());
                rol.setEstado(u.getEstado());
                return new ResponseEntity<>(rolService.save(rol), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
@DeleteMapping("/eliminarRol/{id}")
    public ResponseEntity<Rol> elimiarRol(@PathVariable Integer id) {
        rolService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
