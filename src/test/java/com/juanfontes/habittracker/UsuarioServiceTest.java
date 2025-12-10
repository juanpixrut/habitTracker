package com.juanfontes.habittracker;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.juanfontes.habittracker.model.Usuario;
import com.juanfontes.habittracker.repository.UsuarioRepository;
import com.juanfontes.habittracker.service.UsuarioService;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repo;

    @InjectMocks
    UsuarioService service;

    @Test
    void crearUsuarioBien(){

        //preparo el usuario
        Usuario u = new Usuario();
        u.setEmail("test@gmail.com");

        //le digo al repo falso que devolver
        when(repo.save(any())).thenReturn(u);

        //llamo al service
        Usuario resultado = service.agregarUsuario(u);

        //verifico resultados
        assert resultado.getEmail().equals("test@gmail.com");

    }
}
