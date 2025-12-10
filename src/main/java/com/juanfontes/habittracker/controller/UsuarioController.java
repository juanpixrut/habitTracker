package com.juanfontes.habittracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanfontes.habittracker.model.Habito;
import com.juanfontes.habittracker.model.Usuario;
import com.juanfontes.habittracker.service.UsuarioService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuario/{email}")
    public Usuario buscoUsuario(@PathVariable String email) {
        return usuarioService.buscarPorEmail(email);
    }

    @PostMapping
    public Usuario AgregoUsuario(@RequestBody Usuario usuario) {
        return usuarioService.agregarUsuario(usuario);
    }

    @PostMapping("/usuario/habito/{id}")
    public void agregoHabito(@PathVariable Integer id, @RequestBody Habito habito) {
        usuarioService.agregarHabito(id, habito);
    }

    @GetMapping("/usuario/habito/{id}")
    public List<Habito> getHabitos(@PathVariable Integer id) {
        return usuarioService.obtenerHabitosDeUsuario(id);
    }

}