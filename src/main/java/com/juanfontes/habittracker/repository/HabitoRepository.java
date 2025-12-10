package com.juanfontes.habittracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juanfontes.habittracker.model.Habito;

public interface HabitoRepository extends JpaRepository<Habito, Integer>{
    List<Habito> findByUsuarioId(Integer usuarioId);
}

