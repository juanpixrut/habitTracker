package com.juanfontes.habittracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanfontes.habittracker.model.LoginRequest;
import com.juanfontes.habittracker.model.Usuario;
import com.juanfontes.habittracker.service.UsuarioService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/login")
public class LoginController {
    UsuarioService servicio;

    public LoginController(UsuarioService servicio){
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest request){

        //busco usuario
        Usuario user = null;
        int userId = 0;
        user = servicio.buscarPorEmail(request.getEmail());

        //si no existe
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email incorrecto");
        }

        //verifico
        if(!user.getPassword().equalsIgnoreCase(request.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
        }

        //si esta todo bn
        userId = user.getId();
        return ResponseEntity.ok(userId);
    }

}
