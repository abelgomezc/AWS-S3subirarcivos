/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tendencias.app.Usuarios.controller;

import com.tendencias.app.Usuarios.model.Usuario;
import com.tendencias.app.Usuarios.service.S3Service;
import com.tendencias.app.Usuarios.service.UsuarioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Abel Gomez
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Autowired
    public S3Service service;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Operation(summary = "Se obtiene la lista de Usuarios")
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listaUsuarios() {
        return new ResponseEntity<>(usuarioService.findByAll(), HttpStatus.OK);
    }

    @Operation(summary = "Debe enviar los campos del Usuario")
    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario u) {

        // String hashedPassword = passwordEncoder.encode(u.getContrasena());
        u.setContrasena(passwordEncoder.encode(u.getContrasena()));
        return new ResponseEntity<>(usuarioService.save(u), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario u) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario != null) {
            try {

                usuario.setEstado(u.getEstado());
                usuario.setCorreoElectronico(u.getCorreoElectronico());
                usuario.setContrasena(passwordEncoder.encode(u.getContrasena()));
                usuario.setCedula(u.getCedula());
                usuario.setNombre(u.getNombre());
                usuario.setApellido(u.getApellido());
                usuario.setTelefono(u.getTelefono());
                usuario.setDireccion(u.getDireccion());
                //  usuario.setPersona(u.getPersona());///xx
                usuario.setRol(u.getRol());
                return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminarUsuario/{id}")
    public ResponseEntity<Usuario> elimiarUsuario(@PathVariable Integer id) {
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/subirArchivo")
    public ResponseEntity<String> SubirArchivo(@RequestParam(value = "archivo") MultipartFile archivo) {
        return new ResponseEntity<>(service.subirArchivo(archivo), HttpStatus.OK);
    }

    @GetMapping("/traerArchivo/{claveArchivo}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String claveArchivo) {
        byte[] data = service.traerArchivo(claveArchivo);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + claveArchivo + "\"")
                .body(resource);
    }

    @DeleteMapping("/borrarArchivo/{claveArchivo}")
    public ResponseEntity<String> deleteFile(@PathVariable String claveArchivo) {
        return new ResponseEntity<>(service.borrarArchivo(claveArchivo), HttpStatus.OK);
    }

}
