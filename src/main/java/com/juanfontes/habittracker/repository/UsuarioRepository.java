package com.juanfontes.habittracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juanfontes.habittracker.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
