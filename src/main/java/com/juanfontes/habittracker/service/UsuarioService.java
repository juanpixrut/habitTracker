package com.juanfontes.habittracker.service;

import com.juanfontes.habittracker.repository.HabitoRepository;
import com.juanfontes.habittracker.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.juanfontes.habittracker.model.Habito;
import com.juanfontes.habittracker.model.Usuario;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;
    private final HabitoRepository habitoRepo;

    public UsuarioService(UsuarioRepository repo, HabitoRepository habitoRepo) {
        this.repo = repo;
        this.habitoRepo = habitoRepo;
    }

    // LOGIN
    public Usuario buscarPorEmail(String email) {
        return repo.findByEmail(email);
    }

    // REGISTRO
    public Usuario agregarUsuario(Usuario usuario) {

        // 1. Guardo el usuario primero y genera su ID
        Usuario savedUser = repo.save(usuario);

        // 2. Si vienen habitos les asigno el usuario
        if (usuario.getHabitos() != null) {

            for (Habito h : usuario.getHabitos()) {
                h.setUsuario(savedUser);
                h.setFechaInicio(LocalDate.now());
            }

            // 3. Guardar todos los habitos juntos
            habitoRepo.saveAll(usuario.getHabitos());
        }

        return savedUser;
    }

    // AGREGAR HABITO
    public boolean agregarHabito(Integer userId, Habito habito) {
        Usuario usuario = repo.findById(userId).orElse(null);
        if (usuario == null)
            return false;

        habito.setUsuario(usuario);
        habito.setFechaInicio(LocalDate.now());
        habitoRepo.save(habito);

        return true;
    }

    // OBTENER HABITOS POR USUARIO
    public List<Habito> obtenerHabitosDeUsuario(Integer userId) {
        return habitoRepo.findByUsuarioId(userId);
    }

    // MARCAR COMPLETADO
    public boolean toggleHabito(Integer habitoId) {
        Habito h = habitoRepo.findById(habitoId).orElse(null);
        if (h == null)
            return false;

        h.setCompletado(!h.isCompletado());
        habitoRepo.save(h);
        return true;
    }
}
