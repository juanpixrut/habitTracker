package com.juanfontes.habittracker.controller;

import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanfontes.habittracker.model.RegisterRequest;
import com.juanfontes.habittracker.model.Usuario;
import com.juanfontes.habittracker.service.UsuarioService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class RegistroController {
    private UsuarioService service;

    public RegistroController(UsuarioService service){
        this.service = service;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody RegisterRequest request){

        //verifico que no exista.
        if(service.buscarPorEmail(request.getEmail()) != null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario ya existe");
        }else{
            Usuario user = Usuario.RequestToUsuario(request);
            service.agregarUsuario(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado con exito");
        }

    }

}
