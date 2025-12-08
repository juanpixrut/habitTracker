package com.juanfontes.habittracker.service;

import com.juanfontes.habittracker.repository.HabitoRepository;
import com.juanfontes.habittracker.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.juanfontes.habittracker.model.Habito;
import com.juanfontes.habittracker.model.Usuario;

@Service
public class UsuarioService {
    private UsuarioRepository repo;
    private HabitoRepository repoHabito;

    public UsuarioService(UsuarioRepository repo, HabitoRepository repoHabito) {
        this.repo = repo;
        this.repoHabito = repoHabito;
    }

    public Usuario buscoUsuario(String email) {
        List<Usuario> usuarios = repo.findAll();
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    public void AgregoUsuario(Usuario usuario) {
        List<Habito> habitos = usuario.getHabitos();
        repo.save(usuario);
        for (Habito h : habitos) {
            h.setUsuario(usuario);
            repoHabito.save(h);
        }
    }

    public void agregoHabito(Usuario usuario, Habito habito) {
        usuario.setHabito(habito);
        repo.save(usuario);
    }

    public List<Usuario> buscoUsuariosGMAIL(){
        List<Usuario> usuarios = repo.findAll();
        List<Usuario> usuariosGMAIL = new ArrayList<>();
        for(Usuario u : usuarios){
            if(u.getEmail().contains("gmail")){
                usuariosGMAIL.add(u);
            }
        }
        return usuariosGMAIL;
    }

    public List<Habito> buscoHabitos(){
        return repoHabito.findAll();
    }

}
