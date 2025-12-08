package com.juanfontes.habittracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juanfontes.habittracker.model.Habito;

public interface HabitoRepository extends JpaRepository<Habito, Integer>{
    
}

