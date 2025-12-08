package com.juanfontes.habittracker.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Habito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;

    @ManyToOne
    @JsonIgnore
    private Usuario usuario;

    public Habito(){

    }

    public Habito(String nombre, String descripcion, Usuario usuario){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = LocalDate.now();
    }

    public void setNombre(){

    }
        public void setDescripcion(){
        
    }
        public void setFechaInicio(){
        
    }

        public String getNombre(){
        return nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }
    
        public LocalDate getFechaInicio(){
        return fechaInicio;
    }

        public Usuario getUsuario(){
        return usuario;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    
}
